package it.fperfetti.asos.banca.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/bank")
public class BancaServiceImpl implements BancaService {

	@Override
	@GET
	@Produces("text/plain")
	@Path("/simple")
	public Response get() {
		return Response.status(200).entity("Ciao Mondo").build();
	}

	@Override
	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {
 
		String result = "Restful example : " + msg;
 
		return Response.status(200).entity(result).build();
 
	}

}
