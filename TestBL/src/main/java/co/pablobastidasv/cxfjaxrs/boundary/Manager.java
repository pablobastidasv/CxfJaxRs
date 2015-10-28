package co.pablobastidasv.cxfjaxrs.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author pbastidas
 */
@Path( "/" )
public class Manager {

    @GET
    @Produces("application/json")
    public String get(){
        return "{\"message\":\"Hello world!!!\"}";
    }

}
