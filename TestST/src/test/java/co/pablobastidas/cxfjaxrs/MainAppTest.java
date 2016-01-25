package co.pablobastidas.cxfjaxrs;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author pbastidas
 */
public class MainAppTest {

    final String END_POINT = "http://docker.me:8080/service/api/egress";
    private JsonObject initialEgressInfo;
    private JsonObject newEgressInfo;

    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(END_POINT);

    @Before
    public void setUp() throws Exception {
        initialEgressInfo = Json.createObjectBuilder()
                .add("name", "Correct egress")
                .add("egressDate", "2016-01-16")
                .add("paymentMethod", "CHECK")
                .add("totalValue", new BigDecimal("8763876328")).build();
        newEgressInfo = Json.createObjectBuilder()
                .add("name", "Modified egress")
                .add("egressDate", "2016-01-17")
                .add("paymentMethod", "DEDIT_CARD")
                .add("totalValue", new BigDecimal("2500000")).build();

    }

    @Test
    public void egressCreation() throws Exception {

        // Read all
        final Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus()).isEqualTo(200);

        // Create
        final Response postResponse = provider.target().request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(initialEgressInfo));
        assertThat(postResponse.getStatus()).isEqualTo(201);

        final String location = postResponse.getHeaderString("Location");
        assertThat(location).isNotEmpty();

        final JsonObject egressCreated = this.provider.client()
                .target(location)
                .request(MediaType.APPLICATION_JSON)
                .get(JsonObject.class);

        assertThat(egressCreated.getString("name")).isEqualTo(initialEgressInfo.getString("name"));
        assertThat(egressCreated.getString("egressDate")).isEqualTo(initialEgressInfo.getString("egressDate"));
        assertThat(egressCreated.getString("paymentMethod")).isEqualTo(initialEgressInfo.getString("paymentMethod"));
        assertThat(egressCreated.getJsonNumber("totalValue").bigDecimalValue().compareTo(initialEgressInfo.getJsonNumber("totalValue").bigDecimalValue())).isZero();

        // Update
        final Response putResponse = provider.target(location).request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(newEgressInfo));
        assertThat(putResponse.getStatus()).isEqualTo(204);

        final JsonObject egressModified = this.provider.client()
                .target(location)
                .request(MediaType.APPLICATION_JSON)
                .get(JsonObject.class);

        assertThat(egressModified.getString("name")).isEqualTo(newEgressInfo.getString("name"));
        assertThat(egressModified.getString("egressDate")).isEqualTo(newEgressInfo.getString("egressDate"));
        assertThat(egressModified.getString("paymentMethod")).isEqualTo(newEgressInfo.getString("paymentMethod"));
        assertThat(egressModified.getJsonNumber("totalValue").bigDecimalValue().compareTo(newEgressInfo.getJsonNumber("totalValue").bigDecimalValue())).isZero();

        // Delete
        final Response deleteResponse = provider.target().path(egressCreated.getString("id")).request().delete();
        assertThat(deleteResponse.getStatus()).isEqualTo(204);

    }

}
