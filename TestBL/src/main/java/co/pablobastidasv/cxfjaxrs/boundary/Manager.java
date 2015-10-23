package co.pablobastidasv.cxfjaxrs.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author pbastidas
 */
@Path( "/" )
public class Manager {

    @GET
    public String get(){
        return "Hola Mundo!!!";
    }

}
