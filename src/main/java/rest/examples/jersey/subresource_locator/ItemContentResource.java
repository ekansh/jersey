package rest.examples.jersey.subresource_locator;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class ItemContentResource {
@GET
public String get() { 
	return "OK";
}

@PUT
@Path("{version}")
public String put(@PathParam("version") int version){
	return ""+version;
}
}