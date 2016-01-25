package co.pablobastidasv.cxfjaxrs.structure.producers;

import co.pablobastidasv.cxfjaxrs.boundary.EgressManager;
import org.mongodb.morphia.Datastore;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Producer to generate the managers used on the system.
 *
 * @author pbastidas
 */
public class ManagersProducer {

    @Inject
    private Datastore ds;

    @Produces
    public EgressManager egressManagerProducer(){
        return new EgressManager(ds);
    }

}
