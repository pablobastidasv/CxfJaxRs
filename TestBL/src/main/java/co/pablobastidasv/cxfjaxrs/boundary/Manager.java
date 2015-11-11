package co.pablobastidasv.cxfjaxrs.boundary;

import co.pablobastidasv.cxfjaxrs.model.Egress;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pbastidas
 */
public class Manager {

    public List<Egress> findAll(){
        List<Egress> allEgresses = new ArrayList<>();

        allEgresses.add(new Egress("Laptop", new BigDecimal("2999999")));

        return allEgresses;
    }

}
