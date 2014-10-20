/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.util.regex.Pattern;

/**
 * Represents a Dydra.com identifier.
 *
 * @see http://docs.dydra.com/sdk/java
 */
public class Identifier implements Identifiable, Comparable<Identifier> {
  public static final String     ALGORITHM = "SHA-1";
  public static final byte       SIZE      = 20;       /* bytes */
  public static final byte       LENGTH    = SIZE * 2; /* characters */
  public static final Pattern    PATTERN   = Pattern.compile("^[0-9A-Fa-f]{40}$");
  public static final Charset    CHARSET   = Charset.forName("UTF-8");
  public static final Identifier EMPTY     = Identifier.forBytes(new byte[0]);

  /**
   * The identifier data as a byte array.
   */
  public final byte[] data;

  /**
   * Returns an instance of the algorithm used for computing identifiers.
   *
   * @return a message digest algorithm instance
   */
  @NotNull
  public static MessageDigest getAlgorithm() {
    try {
      return MessageDigest.getInstance(ALGORITHM);
    }
    catch (NoSuchAlgorithmException e) {
      throw new ProviderException(e);
    }
  }

  /**
   * Computes the identifier for the contents of the given file.
   *
   * @param  file the file to read
   * @return an identifier
   */
  @NotNull
  public static Identifier forFile(@NotNull final File file) throws IOException {
    if (file == null)
      throw new NullPointerException("file cannot be null");

    return forStream(new FileInputStream(file));
  }

  /**
   * Computes the identifier for the data in the given input stream.
   *
   * @param  stream the input stream to read
   * @return an identifier
   */
  @NotNull
  public static Identifier forStream(@NotNull final InputStream stream) throws IOException {
    if (stream == null)
      throw new NullPointerException("stream cannot be null");

    return forChannel(Channels.newChannel(stream));
  }

  /**
   * Computes the identifier for the data in the given byte channel.
   *
   * @param  channel the byte channel to read
   * @return an identifier
   */
  @NotNull
  public static Identifier forChannel(@NotNull final ReadableByteChannel channel) throws IOException {
    if (channel == null)
      throw new NullPointerException("channel cannot be null");

    MessageDigest algorithm = getAlgorithm();
    ByteBuffer buffer = ByteBuffer.allocate(4096); // one page at a time
    while (channel.read(buffer) != -1) {
      buffer.flip();
      algorithm.update(buffer);
      buffer.clear();
    }
    return new Identifier(algorithm.digest());
  }

  /**
   * Computes the identifier for the given byte buffer.
   *
   * @param  data the byte buffer to read
   * @return an identifier
   */
  @NotNull
  public static Identifier forBuffer(@NotNull final ByteBuffer data) {
    if (data == null)
      throw new NullPointerException("data cannot be null");

    MessageDigest algorithm = getAlgorithm();
    algorithm.update(data);
    return new Identifier(algorithm.digest());
  }

  /**
   * Computes the identifier for the given byte array.
   *
   * @param  data the byte array to read
   * @return an identifier
   */
  @NotNull
  public static Identifier forBytes(@NotNull final byte[] data) {
    if (data == null)
      throw new NullPointerException("data cannot be null");

    MessageDigest algorithm = getAlgorithm();
    algorithm.update(data);
    return new Identifier(algorithm.digest());
  }

  /**
   * Computes the identifier for the given text string.
   *
   * @param  text the text string to read
   * @return an identifier
   */
  @NotNull
  public static Identifier forString(@NotNull final String text) {
    if (text == null)
      throw new NullPointerException("text cannot be null");

    return forBytes(text.getBytes(CHARSET)); // encoded as UTF-8
  }

  /**
   * Constructs an identifier from the given byte array.
   *
   * @param  id a byte array of <code>Identifier.SIZE</code> bytes
   * @return an identifier
   */
  public Identifier(@NotNull final byte[] id) {
    if (id == null)
      throw new NullPointerException("id cannot be null");

    if (id.length != SIZE) {
      throw new IllegalArgumentException(
        "expected a byte[" + SIZE + "] array, " +
        "but got a byte[" + id.length + "] array");
    }

    this.data = id;
  }

  /**
   * Constructs an identifier from the given hexadecimal string.
   *
   * @param  id a hexadecimal string of length <code>Identifier.LENGTH</code>
   * @return an identifier
   */
  public Identifier(@NotNull final String id) {
    if (id == null)
      throw new NullPointerException("id cannot be null");

    if (id.length() != LENGTH) {
      throw new IllegalArgumentException(
        "expected a hexadecimal string of length " + (LENGTH) +
        ", but got length " + id.length());
    }

    if (!PATTERN.matcher(id).matches()) {
      throw new IllegalArgumentException(
        "expected a hexadecimal string of length " + (LENGTH) +
        ", but got \"" + id.replace("\"", "\\\"") + "\"");
    }

    this.data = decodeHexString(id);
  }

  /**
   * Constructs an identifier from the given integer.
   *
   * @param  id a non-negative integer
   * @return an identifier
   */
  public Identifier(@NotNull final BigInteger id) {
    if (id == null)
      throw new NullPointerException("id cannot be null");

    if (id.compareTo(BigInteger.ZERO) == -1) {
      throw new IllegalArgumentException(
        "expected a non-negative integer, but got " + id.toString());
    }

    this.data = decodeHexString(String.format("%040x", id));
  }

  /**
   * @private
   */
  @NotNull
  private byte[] decodeHexString(@NotNull final String id) {
    byte[] data = new byte[SIZE];
    for (int i = 0; i < LENGTH; i += 2) {
      data[i / 2] = (byte)((Character.digit(id.charAt(i), 16) << 4) +
        Character.digit(id.charAt(i + 1), 16));
    }
    return data;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param  other the object to compare this identifier against
   * @return <code>true</code> if the given object is equivalent to this
   *         identifier, <code>false</code> otherwise
   */
  @Override
  public boolean equals(@Nullable final Object other) {
    return (other instanceof Identifier) && equals((Identifier)other);
  }

  /**
   * Indicates whether another identifier is equal to this one.
   *
   * @param  other the identifier to compare this identifier against
   * @return <code>true</code> if the given identifier is equivalent to this
   *         identifier, <code>false</code> otherwise
   */
  public boolean equals(@Nullable final Identifier other) {
    return MessageDigest.isEqual(this.data, other.toByteArray());
  }

  /**
   * Returns the hash code for this identifier.
   *
   * @return a hash code value
   */
  @Override
  public int hashCode() {
    return toByteBuffer().getInt();
  }

  /**
   * Returns the hexadecimal string representation of this identifier.
   *
   * @return a hexadecimal string of length <code>Identifier.LENGTH</code>
   */
  @Override @NotNull
  public String toString() {
    StringBuilder buffer = new StringBuilder(LENGTH);
    for (byte b : this.data) {
      buffer.append(Integer.toHexString((b & 0xff) + 0x100).substring(1));
    }
    return buffer.toString();
  }

  /**
   * Returns this identifier itself.
   *
   * @return a Dydra.com identifier
   */
  @Override @NotNull
  public Identifier toIdentifier() {
    return this;
  }

  /**
   * Returns the byte data for this identifier.
   *
   * @return a byte array of <code>Identifier.SIZE</code> bytes
   */
  @NotNull
  public byte[] toByteArray() {
    return this.data;
  }

  /**
   * Returns a byte buffer for this identifier.
   *
   * @return a byte buffer with <code>Identifier.SIZE</code> bytes
   */
  @NotNull
  public ByteBuffer toByteBuffer() {
    return ByteBuffer.wrap(this.data);
  }

  /**
   * Returns the integer representation of this identifier.
   *
   * @return a non-negative integer
   */
  @NotNull
  public BigInteger toBigInteger() {
    return new BigInteger(1, this.data); // zero or a positive integer
  }

  /**
   * Compares this identifier to another identifier.
   *
   * @param  other the identifier to compare this identifier against
   * @return a negative integer, zero, or a positive integer as this
   *         identifier is less than, equal to, or greater than the given
   *         identifier
   * @throws NullPointerException if <code>other</code> is null
   */
  public int compareTo(@NotNull final Identifier other) {
    if (other == null)
      throw new NullPointerException("other cannot be null");

    return toByteBuffer().compareTo(other.toByteBuffer());
  }
}
