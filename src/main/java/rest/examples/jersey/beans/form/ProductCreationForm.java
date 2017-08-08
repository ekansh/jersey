package rest.examples.jersey.beans.form;

import java.io.File;
import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


public class ProductCreationForm {
	@FormDataParam("productName") private String productName;
	@FormDataParam("longDescription") private String longDescription;
	@FormDataParam("shortDescription") private String shortDescription;
	@FormDataParam("category") private String category;
	@FormDataParam("discount") private String price;
	@FormDataParam("discount") private String discount;
	@FormDataParam("file") private InputStream uploadedInputStream;
	@FormDataParam("file") FormDataContentDisposition fileDetail;
	public InputStream getUploadedInputStream() {
		return uploadedInputStream;
	}
	public FormDataContentDisposition getFileDetail() {
		return fileDetail;
	}
	public ProductCreationForm(){}
	public String getProductName() {
		return productName;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public String getCategory() {
		return category;
	}
	public String getPrice() {
		return price;
	}
	public String getDiscount() {
		return discount;
	}
	
}
