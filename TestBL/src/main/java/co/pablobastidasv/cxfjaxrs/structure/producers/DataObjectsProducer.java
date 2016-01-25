package co.pablobastidasv.cxfjaxrs.structure.producers;

import co.pablobastidasv.cxfjaxrs.structure.annotations.Configuration;
import co.pablobastidasv.cxfjaxrs.structure.converters.BigDecimalConverter;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Producer for data object.
 *
 * @author pbastidas
 */
public class DataObjectsProducer {

    @Inject
    @Configuration(value = "MONGODB_PORT_27017_TCP_PORT", type = "System")
    private String mongoServerPort;

    @Inject
    @Configuration(value = "MONGODB_PORT_27017_TCP_ADDR", type = "System")
    private String mongoServerAddr;

    @Produces
    public Datastore datastoreProducer(){
        MongoClient mongo = new MongoClient(mongoServerAddr, Integer.parseInt(mongoServerPort));

        Morphia morphia = new Morphia();
        morphia.mapPackage("co.pablobastidasv.cxfjaxrs.model")
                .getMapper()
                .getConverters()
                .addConverter(BigDecimalConverter.class);

        final Datastore ds = morphia.createDatastore(mongo, "accounting");
        ds.ensureIndexes();
        return ds;
    }

}
