package br.com.jaxrs.texoit;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import junit.framework.TestCase;

public class ProducerResourceTest extends TestCase {
	private HttpServer server;
    private WebTarget target;
 
    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
 
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }
 
    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }
        
    @Test
    public void testGetIntervals() {
        String response = target.path("producers").request().get(String.class);
        assertEquals("{\"min\":null,\"max\":null}", response);
    }
}
