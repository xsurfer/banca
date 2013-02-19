package it.fperfetti.asos.banca.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/bank")
public interface BancaService {
	   @GET
	   @Produces("text/plain")
	   @Path("/simple")
	   public Response get();
	   
	   @GET
		@Path("/{param}")
		public Response printMessage(@PathParam("param") String msg);
	   
}
