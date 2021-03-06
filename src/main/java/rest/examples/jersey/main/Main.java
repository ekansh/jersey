package rest.examples.jersey.main;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;



import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8080/myapp/";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in rest.examples.jersey package
		final ResourceConfig rc = new ResourceConfig()
				.packages("rest.examples.jersey.main")
				.packages("rest.examples.jersey.caching")
				.packages("rest.examples.jersey.customdatatype")
				.packages("rest.examples.jersey.subresource_locator")
				.packages("rest.example.jersey.map_incoming_req_to_bean").register(MultiPartFeature.class);
		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		HttpServer createHttpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
		return createHttpServer ;
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		init();
		 final HttpServer server = startServer();
		 System.out.println(String.format("Jersey app started with WADL	 available at "
		 + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
		 System.in.read();
		 server.stop();
	}
	private static void init(){
		
		
	}
}
