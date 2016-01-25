package co.pablobastidasv.cxfjaxrs.structure.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * Custom serializer to show the Mongo ObjectId object on json response
 *
 * @author pbastidas
 */
public class CustomObjectIdSerializer extends JsonSerializer<ObjectId> {
    @Override
    public void serialize(ObjectId value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.toString());
    }


}
