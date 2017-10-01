package rest.examples.jersey.customdatatype;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


public class ImageResource {
	@GET
	@Produces("image/*")
	public Response get(@PathParam("id") String id) {
		System.out.println("getting image for id "+id);
		String image = id;
		File f = new File(image);
		if (!f.exists()) {
			throw new WebApplicationException(404);
		}
		String mt = new MimetypesFileTypeMap().getContentType(f);
		 Response response= Response.ok(f, mt).build();
		 return response;
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		System.out.println("POST image");
		String uploadedFileLocation = "images/" + fileDetail.getFileName();
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();
	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		java.nio.file.Path path = Paths.get(uploadedFileLocation);

		try (OutputStream writer = Files.newOutputStream(path)) {
			byte[] bytes = new byte[1024];
			while (uploadedInputStream.read(bytes) != -1) {
				writer.write(bytes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
