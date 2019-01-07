package core;

import core.models.Request;
import org.junit.Test;

import java.util.LinkedHashMap;

import static junit.framework.TestCase.assertEquals;

public class ParserTest {

    @Test
    public void parseRequestWithoutHeaders() {
        String requestString = "GET /helloworld HTTP/1.1\n";

        Request request = new Request("GET", "/helloworld", "HTTP/1.1");
        Request parsedRequest = Parser.parse(requestString);

        assertEquals(request.getMethod(), parsedRequest.getMethod());
        assertEquals(request.getHttpVersion(), parsedRequest.getHttpVersion());
        assertEquals(request.getUri(), parsedRequest.getUri());
    }

    @Test
    public void parseRequestWithHeaders() {
        String requestString =
                "GET /helloworld HTTP/1.1\n" +
                        "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n" +
                        "Host: localhost\n" +
                        "Connection: Keep-Alive";

        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("User-Agent", "Mozilla/4.0 (compatible; MSIE5.01; Windows NT)");
        headers.put("Host", "localhost");
        headers.put("Connection", "Keep-Alive");
        Request request = new Request("GET", "/helloworld", "HTTP/1.1")
                .withHeaders(headers);

        Request parsedRequest = Parser.parse(requestString);

        assertEquals(request.getMethod(), parsedRequest.getMethod());
        assertEquals(request.getHttpVersion(), parsedRequest.getHttpVersion());
        assertEquals(request.getUri(), parsedRequest.getUri());
        assertEquals(request.getHeaders().get("Host"), parsedRequest.getHeaders().get("Host"));
        assertEquals(request.getHeaders().get("Connection"), parsedRequest.getHeaders().get("Connection"));
        assertEquals(request.getHeaders().get("User-Agent"), parsedRequest.getHeaders().get("User-Agent"));
    }

    @Test
    public void parseRequestWithHeadersAndBody() {
        String requestString =
                "POST localhost HTTP/1.1\n" +
                        "Content-Type: application/text\n" +
                        "Content-Length: 456\n" +
                        "\r\n\r\n" +
                        "Some body";

        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/text");
        headers.put("Content-Length", "456");
        Request request = new Request("POST", "localhost", "HTTP/1.1")
                .withHeaders(headers)
                .withBody("Some body");

        Request parsedRequest = Parser.parse(requestString);

        assertEquals(request.getMethod(), parsedRequest.getMethod());
        assertEquals(request.getHttpVersion(), parsedRequest.getHttpVersion());
        assertEquals(request.getUri(), parsedRequest.getUri());
        assertEquals(request.getHeaders().get("Content-Type"), parsedRequest.getHeaders().get("Content-Type"));
        assertEquals(request.getHeaders().get("Content-Length"), parsedRequest.getHeaders().get("Content-Length"));
        assertEquals(request.getBody(), parsedRequest.getBody());
    }
}