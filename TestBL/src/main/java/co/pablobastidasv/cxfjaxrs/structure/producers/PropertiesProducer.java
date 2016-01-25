package co.pablobastidasv.cxfjaxrs.structure.producers;

import co.pablobastidasv.cxfjaxrs.structure.annotations.Configuration;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Producer to generate configuration values
 *
 * @author pbastidas
 */
public class PropertiesProducer {

    @Produces
    @Configuration
    public String getStringPropertyValue(InjectionPoint injectionPoint){
        final Configuration configuration = injectionPoint.getAnnotated().getAnnotation(Configuration.class);

        if(configuration.value().isEmpty()){
            throw new IllegalArgumentException("The configuration annotation must have a value");
        }

        String value = configuration.value();
        String type = configuration.type();

        if(type.equals("System")){
            return getSystemProperty(value);
        }

        
        return "";
    }

    private String getSystemProperty(String value) {
        return System.getenv(value);
    }

}
