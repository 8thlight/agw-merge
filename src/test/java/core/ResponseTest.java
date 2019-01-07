package core;

import core.models.Response;
import core.models.ResponseHeader;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ResponseTest {
    @Test
    public void hasValidStatusLineInformation() {
        Response res = Response.ok();

        assertEquals("HTTP/1.1", res.getHttpVersion());
        assertEquals(200, res.getStatusCode());
        assertEquals("OK", res.getReason());
    }

    @Test
    public void buildsResponseBasicResponse() {
        Response res = Response.ok();

        assertEquals("HTTP/1.1 200 OK\n", res.toString());
    }

    @Test
    public void buildsResponseWithBody() {
        Response res = Response.ok()
                .withBody("some body");

        assertEquals("HTTP/1.1 200 OK\n\nsome body", res.toString());
    }

    @Test
    public void buildsResponseWithHeaders() {
        Map<ResponseHeader, String> headers = new LinkedHashMap<>();
        headers.put(ResponseHeader.LOCATION, "value1");
        headers.put(ResponseHeader.ALLOW, "value2");
        Response res = Response.ok()
                .withHeaders(headers)
                .withBody("some body");

        assertEquals("HTTP/1.1 200 OK\nLocation: value1\nAllow: value2\n\nsome body", res.toString());
    }
}