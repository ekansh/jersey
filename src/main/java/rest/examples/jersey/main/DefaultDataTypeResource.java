package rest.examples.jersey.main;


import java.io.File;
import java.net.URI;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 * QUERY PARAMETER
 * PATH PARAMETER
 * Confirming Client resource creation when POST
 */
@Path("myresource/{username: [a-zA-Z][a-zA-Z_0-9]*}")
public class DefaultDataTypeResource {
	private static int k =0;
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("username") String username) {
        return username+" Got sit! "+ ++k;
    }
    
    @GET
	@Produces("image/*")
	public Response getImage() {
		System.out.println("----");
		String image = "scroll0015.jpg";
		File f = new File(image);
		if (!f.exists()) {
			throw new WebApplicationException(404);
		}
		String mt = new MimetypesFileTypeMap().getContentType(f);
		return Response.ok(f, mt).build();
	}
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getIt( @DefaultValue("2") @QueryParam("step") int step,
    	    @DefaultValue("true") @QueryParam("min-m") boolean hasMin , @Context UriInfo ui){
    	System.out.println("DefaultDataTypeResource.getIt():: @GET    @Produces(MediaType.TEXT_XML)");
    	URI requestUri = ui.getRequestUri();
    	System.out.println("URI "+requestUri);
    	System.out.println("MyResource.getIt() "+step);
    	System.out.println("MyResource.getIt() "+hasMin);
        return "<a>GOT : "+step+" ::"+hasMin+"</a>";
    }
    
    @POST
   	@Produces("image/*")
   	public Response postImage() {
   		System.out.println("postImage");
   		URI createdURI= URI.create("scroll0015.jpg");
   		return Response.created(createdURI).build();
   		
    }
    
    
 
   
    
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String putResourceJSON(){
    	 System.out.println("accepts application/json");
    	    return "accepts application/json";
    }
 
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public String putResourceJSON1(){
    	 System.out.println("accepts application/json and consumes xml");
    	    return "accepts application/json and consumes xml";
    }
    
    @PUT
    public String putResource(){
    	 System.out.println("default PUT");
    	    return "default PUT";
    }
}
