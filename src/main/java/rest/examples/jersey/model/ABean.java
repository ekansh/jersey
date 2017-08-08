package rest.examples.jersey.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.Encoded;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class ABean {
	@PathParam("p")
	private String pathParam;
	@MatrixParam("m")
	@Encoded
	@DefaultValue("default")
	private String matrixParam;
	@HeaderParam("header")
	private String headerParam;
	private String queryParam;

	public ABean(@QueryParam("q") String queryParam) {
		this.queryParam = queryParam;
	}

	public String getPathParam() {
		return pathParam;
	}
	
	public String getMatrixParam() {
		return matrixParam;
	}

	public String getHeaderParam() {
		return headerParam;
	}

	public String getQueryParam() {
		return queryParam;
	}
}
