package application;

import core.Endpoint;
import core.models.Request;
import core.models.Response;

public class EchoBodyEndpoint extends Endpoint {
    @Override
    public String getUri() {
        return "/echo_body";
    }

    @Override
    public Response getResponse(Request request) {
        return Response.ok()
                .setBody(request.getBody());
    }
}
