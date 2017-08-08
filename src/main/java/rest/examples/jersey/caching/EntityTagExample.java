package rest.examples.jersey.caching;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.DatatypeConverter;


/**
 * use the following request header 
 * accept: text/plain
 * accept-encoding: gzip, deflate
 * accept-language: en-US,en;q=0.8
 * content-type: text/plain
 * user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36
 * If-None-Match: "30057E5031BCF44D47B005A1F1700F7B"
 * @author Ekansh
 *
 */

@Singleton
@Path("etag")
public class EntityTagExample {
    
    private String data = "Some Data";
    
    @GET
    @Produces("text/plain")
    public Response get(@Context Request request) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        String hex = DatatypeConverter.printHexBinary(hash);
        EntityTag etag = new EntityTag(hex);
        
        ResponseBuilder builder = request.evaluatePreconditions(etag);
        System.out.println("builder "+builder);
        if (builder != null) {
        	Response build = builder.build();
        	System.out.println("builder.build "+build);
            return build;
        }
        Response build = Response.ok(data).tag(etag).build();
        System.out.println("not cached "+build);
        return Response.ok(data).tag(etag).build();
    }
    
    @POST
    @Consumes("text/plain")
    public Response post() {
        this.data = "additional data"+data;
        Response build = Response.noContent().build();
        System.out.println(build);
        return build;
    }
}
