package rest.examples.jersey.customdatatype;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dbconnection.DataStore;
import rest.examples.jersey.beans.form.ProductCreationForm;
import rest.examples.jersey.model.Image;
import rest.examples.jersey.model.Product;
import rest.examples.jersey.model.ProductCategory;
import rest.examples.jersey.subresource_locator.ItemContentResource;

@Path("product")
public class ProductResource {
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response post(@BeanParam ProductCreationForm productCreationForm) {

		Product product = constructProduct(productCreationForm);

		String output = "Product Created : " + product.getId();

		return Response.status(201).entity(output).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {

		Product product = DataStore.getProducts().get(id);
		if (product == null) {
			return Response.status(200).entity("Product with the id " + id + "doesnt exist ").build();

		}
		System.out.println("---" + product);
		return Response.ok(product).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Product> get() {

		Collection<Product> products = DataStore.getProducts().values();
		if (products == null) {
			return new ArrayList<>();

		}
		System.out.println("---" + products);
		return products;

	}

	@PATCH
	public Response patch(@BeanParam ProductCreationForm productCreationForm) {
		System.out.println("ProductResource.patch()");
		String id = productCreationForm.getProductName() + productCreationForm.getCategory();

		Product productTemp = DataStore.getProducts().get(id);
		if (productTemp == null) {
			return Response.status(200).entity("Product with the id " + id + "doesnt exist ").build();

		}

		Product product = constructProduct(productCreationForm);

		String output = "Product Created : " + product.getId();


		System.out.println("---" + product);
		return Response.status(201).entity(output).build();
	}

	@Path("image/{id}")
	public ImageResource geImageResource() {
		return new ImageResource();
	}

	private Product constructProduct(ProductCreationForm productCreationForm) throws WebApplicationException {

		Product pr = new Product();
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategory(productCreationForm.getCategory());
		pr.setCategory(productCategory);
		pr.setDiscount(pr.getDiscount());
		Image image = new Image();
		image.setName(productCreationForm.getFileDetail().getFileName());
		String uploadedFileLocation = "images/" + productCreationForm.getFileDetail().getFileName();
		image.setPathToImage(uploadedFileLocation);
		pr.setImage(image);
		pr.setName(productCreationForm.getProductName());
		pr.setShortDescription(productCreationForm.getShortDescription());
		pr.setLongDescription(productCreationForm.getLongDescription());
		String id = pr.getName() + pr.getCategory().getCategory();
		if (DataStore.getProducts().keySet().contains(id)) {
			throw new WebApplicationException("Product already exist");
		}
		pr.setId(pr.getName() + pr.getCategory().getCategory());
		InputStream uploadedInputStream = productCreationForm.getUploadedInputStream();
		// save it
		try {
			writeToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			Response exception = Response.status(509).entity("some thing went wrong with the server").build();
			throw new WebApplicationException(exception);
		}
		DataStore.getProducts().put(id, pr);
		return pr;
	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
		java.nio.file.Path path = Paths.get(uploadedFileLocation);
		try (OutputStream writer = Files.newOutputStream(path)) {
			byte[] bytes = new byte[1024];
			while (uploadedInputStream.read(bytes) != -1) {
				writer.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
