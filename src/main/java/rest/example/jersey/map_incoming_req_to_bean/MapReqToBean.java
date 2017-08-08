package rest.example.jersey.map_incoming_req_to_bean;

import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import rest.examples.jersey.model.ABean;

@Path("mapreqtobean/{p}")
public class MapReqToBean {
	@POST
	public Response post(@BeanParam ABean abean) {
		String debug = "Path Param "+abean.getPathParam()+
		" header Param "+abean.getHeaderParam()+
		" matrix Param "+abean.getMatrixParam()+
		" query Param "+abean.getQueryParam();
		System.out.println("debug "+debug);
		return Response.ok().entity(debug).build();
	}
}
