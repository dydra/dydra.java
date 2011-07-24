/* This is free and unencumbered software released into the public domain. */

package com.dydra;

/**
 * Represents a statement stored in a Dydra.com repository.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://en.wikipedia.org/wiki/Resource_Description_Framework#Overview
 */
public class Statement implements Edge {
  /**
   * The statement identifier.
   */
  public final Identifier id;

  /**
   * The statement's subject term.
   */
  public Identifiable subject;

  /**
   * The statement's predicate term.
   */
  public Identifiable predicate;

  /**
   * The statement's object term.
   */
  public Identifiable object;

  /**
   * Constructs a statement from the given identifier.
   *
   * @param  id a statement identifier
   */
  public Statement(final Identifier id) {
    this.id = id;
  }

  /**
   * Constructs a statement from the given identifier and terms.
   *
   * @param  id a statement identifier
   * @param  s  the subject term
   * @param  p  the predicate term
   * @param  o  the object term
   */
  public Statement(final Identifier id,
                   final Identifiable s, final Identifiable p, final Identifiable o) {
    this.id        = id;
    this.subject   = s;
    this.predicate = p;
    this.object    = o;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param  other the object to compare this statement against
   * @return <code>true</code> if the given object is equivalent to this
   *         statement, <code>false</code> otherwise
   */
  @Override
  public boolean equals(final Object other) {
    return (other instanceof Statement) && equals((Statement)other);
  }

  /**
   * Indicates whether another statement is equal to this one.
   *
   * @param  other the statement to compare this statement against
   * @return <code>true</code> if the given statement is equivalent to this
   *         statement, <code>false</code> otherwise
   */
  public boolean equals(final Statement other) {
    return this.toIdentifier().equals(other.toIdentifier());
  }

  /**
   * Returns the hash code for this statement.
   *
   * @return a hash code value
   */
  @Override
  public int hashCode() {
    return this.toIdentifier().hashCode();
  }

  /**
   * Returns a string representation of this statement.
   *
   * @return a hexadecimal string of length <code>Identifier.LENGTH</code>
   */
  @Override
  public String toString() {
    return this.toIdentifier().toString(); // TODO
  }

  /**
   * Returns the identifier for this statement.
   *
   * @return a Dydra.com identifier
   */
  public Identifier toIdentifier() {
    return this.id;
  }

  /**
   * Compares this statement to another statement.
   *
   * @param  other the statement to compare this statement against
   * @return a negative integer, zero, or a positive integer as this
   *         statement is less than, equal to, or greater than the given
   *         statement
   */
  public int compareTo(final Edge other) {
    return toIdentifier().compareTo(other.toIdentifier());
  }
}
