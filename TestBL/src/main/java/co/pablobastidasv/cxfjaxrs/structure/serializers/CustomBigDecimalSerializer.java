package co.pablobastidasv.cxfjaxrs.structure.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Custom serializer to soy the bigdecimal values in the json response
 *
 * @author pbastidas
 */
public class CustomBigDecimalSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.toPlainString());
    }
}
