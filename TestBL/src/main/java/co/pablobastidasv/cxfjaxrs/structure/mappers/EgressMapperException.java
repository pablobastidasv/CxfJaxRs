package co.pablobastidasv.cxfjaxrs.structure.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapper to handle all exception and put a response with:
 *  status 500
 *  header value named "cause" with a short description of the fail.
 *
 * @author pbastidas
 */
@Provider
public class EgressMapperException implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception ex) {
        ex.printStackTrace();
        return Response.serverError().header("cause", ex.toString()).build();
    }
}
