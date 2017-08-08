package rest.examples.jersey.customdatatype;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import rest.examples.jersey.model.CustomResource1;
import rest.examples.jersey.model.CustomResource2;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("customdatatyperesource")
public class CustomDataTypeResource {
	private static int k = 0;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_XML)
	public List<String> getAppJson(@Context UriInfo ui) {
		System.out.println("CustomResource1 get");
		List<String> arrayList = new ArrayList<>();
		arrayList .add("hello");
		arrayList.add("world");
		return arrayList;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public CustomResource2 getxml(@Context UriInfo ui) {
		return new CustomResource2();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CustomResource2> get(@Context UriInfo ui) {
		System.out.println("CustomResource1 get");
		List<CustomResource2> arrayList = new ArrayList<>();
		arrayList .add(new CustomResource2("hello","world",1));
		arrayList.add(new CustomResource2("MY","word",2));
		return arrayList;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_XML)
	public String putResourceJSON1() {
		System.out.println("accepts application/json and consumes xml");
		return "accepts application/json and consumes xml";
	}

}
