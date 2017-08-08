package rest.examples.jersey.subresource_locator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import rest.examples.jersey.model.CustomResource1;

@Path("/subresourcelocator")
public class ItemResource {

	@Path("content")
	public ItemContentResource getItemContentResource() {
		return new ItemContentResource();
	}

	@GET
	@Produces("text/xml")
	public CustomResource1 get() {
		return new CustomResource1();
	}
}
