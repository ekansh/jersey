package rest.examples.jersey.caching;

import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Running the example: Call http://localhost:8080/myapp/last-modified with the
 * header If-Modified-Since: Sat, 29 Jul 2017 02:36:54 GMT from the response
 * copy value of Last-Modified and update the request's If-Modified-Since
 * headers value. You should ideally get 304 response but we are not getting it
 * . SO far this seems to be the correct way of caching Warning: The example is
 * not thread safe. you can remove singleton and make date as static to achive
 * the same functionality
 * 
 * @author Ekansh
 *
 */

@Singleton
@Path("last-modified")
public class MaxAgeExample {

	private String data = "Some Data";
	private Date lastModified = new Date();

	@GET
	@Produces("text/plain")
	public Response get(@Context Request request) {
		System.out.println(lastModified);
		ResponseBuilder builder = request.evaluatePreconditions(lastModified);
		if (builder != null) {
			System.out.println("MaxAgeExample.get() builder not null");
			Response build = builder.build();
			System.out.println(build);
			return build;
		}
		return Response.ok(data).lastModified(lastModified).build();
	}

	@POST
	@Consumes("text/plain")
	public Response post(String data) {
		this.data = data;
		lastModified = new Date();
		return Response.noContent().build();
	}
}