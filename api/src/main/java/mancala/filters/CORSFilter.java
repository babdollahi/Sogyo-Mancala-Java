package mancala.filters;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {

    public static final String REQUEST_HEADER_ORIGIN = "Origin";
    public static final String RESPONSE_HEADER_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String RESPONSE_HEADER_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String RESPONSE_HEADER_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String RESPONSE_HEADER_ALLOW_METHODS = "Access-Control-Allow-Methods";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (isCorsRequest(requestContext)) {
            requestContext.abortWith(Response.ok().build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        if (requestContext.getHeaderString(REQUEST_HEADER_ORIGIN) == null) {
            return;
        }

        appendCorsHeaders(responseContext);
    }

    private void appendCorsHeaders(ContainerResponseContext response) {
        MultivaluedMap<String, Object> headers = response.getHeaders();

        headers.add(RESPONSE_HEADER_ALLOW_ORIGIN, "http://localhost:4200");
        headers.add(RESPONSE_HEADER_ALLOW_HEADERS, "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        headers.add(RESPONSE_HEADER_ALLOW_CREDENTIALS, "true");
        headers.add(RESPONSE_HEADER_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

    private boolean isCorsRequest(ContainerRequestContext request) {
        return request.getHeaderString(REQUEST_HEADER_ORIGIN) != null && request.getMethod().equals("OPTIONS");
    }
}
