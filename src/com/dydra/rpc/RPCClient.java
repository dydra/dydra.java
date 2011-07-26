package com.dydra.rpc;

import com.dydra.*;
import com.dydra.annotation.*;
import com.ning.http.client.*;
import com.ning.http.client.consumers.AppendableBodyConsumer;
import com.ning.http.client.generators.InputStreamBodyGenerator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Represents a Dydra.com API session.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class RPCClient {
  public static final String  VERSION      = "2.0";
  public static final Charset CHARSET      = Charset.forName("UTF-8");
  public static final String  CONTENT_TYPE = "application/json; charset=UTF-8";
  public static final String  USER_AGENT   = "Dydra";

  /**
   * The session.
   */
  public final Session session;

  /**
   * The HTTP client.
   */
  public final SimpleAsyncHttpClient client;

  /**
   * Constructs an RPC client using the given session.
   *
   * @param  session a valid session
   */
  public RPCClient(@NotNull Session session) {
    if (session == null)
      throw new NullPointerException("session cannot be null");

    this.session = session;
    this.client  = new SimpleAsyncHttpClient.Builder()
      .setUrl(session.url)
      .setHeader("Content-Type", CONTENT_TYPE)
      .setHeader("User-Agent", USER_AGENT)
      .build();
  }

  /**
   * @param  method
   * @param  args
   * @return an instance of RPCResponse
   * @throws IOException
   */
  @NotNull
  public RPCResponse invoke(@NotNull final String method, final Object... args) {
    return invoke(new RPCRequest(method, args));
  }

  /**
   * @param  request
   * @return an instance of RPCResponse
   * @throws IOException
   */
  @NotNull
  public RPCResponse invoke(@NotNull final RPCRequest request) {
    String requestBody = request.toJSON();
    StringBuilder responseBuffer = new StringBuilder();
    Response response;

    try {
      Future<Response> future = this.client.post(
        new InputStreamBodyGenerator(
          new ByteArrayInputStream(requestBody.getBytes(CHARSET))),
        new AppendableBodyConsumer(responseBuffer));
      response = future.get();
    }
    catch (IOException e) {
      throw new RPCException(e);
    }
    catch (InterruptedException e) {
      throw new RPCException(e);
    }
    catch (ExecutionException e) {
      throw new RPCException(e);
    }

    if (response.getStatusCode() != 200) {
      String errorMessage = String.valueOf(response.getStatusCode()) +
        " " + response.getStatusText();
      throw new RPCException(errorMessage); // FIXME
    }

    // TODO: parse the response body in a streaming manner.
    String responseBody = responseBuffer.toString();
    Map<?, ?> responseMap;
    try {
      responseMap = (new ObjectMapper()).readValue(responseBody, Map.class);
    }
    catch (IOException e) {
      throw new RPCException(e);
    }

    if (responseMap.containsKey("error")) {
      throw new RPCException(RPCError.parse(responseBody));
    }

    return RPCResponse.parse(responseBody);
  }
}
