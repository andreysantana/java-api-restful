package br.com.jaxrs.texoit.resources;

import br.com.jaxrs.texoit.repositories.ProducerRepository;
import br.com.jaxrs.texoit.resources.core.AbstractWS;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("producers")
public class ProducerResource extends AbstractWS {
	
	@Inject ProducerRepository _producerRepository;

	 @GET 
	 //@Path("getIntervals")
	 @Produces(MediaType.APPLICATION_JSON) 
	 public Response getIntervals() throws Exception { 
		 return Response.ok(convertToJson(_producerRepository.getIntervals())).build(); 
	 } 
}
