package co.pablobastidasv.cxfjaxrs;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author pbastidas
 */
@Provider
public class EgressMapperException implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception ex) {
        return Response.serverError().header("cause", ex.toString()).build();
    }
}
