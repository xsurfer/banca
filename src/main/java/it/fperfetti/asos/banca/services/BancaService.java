package it.fperfetti.asos.banca.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public interface BancaService {

	@GET
	@Produces("application/json")
	@Path("/check/{cardnumber}/{cvv}/{owner_name}/{owner_surname}")
	public Long isValid(
			@PathParam("cardnumber") String cardnumber,
			@PathParam("cvv") String cvv,
			@PathParam("owner_name") String owner_name,
			@PathParam("owner_surname") String owner_surname);
	
	@GET
	@Produces("application/json")
	@Path("/withdrawal/{vendor}/{id}/{amount}")
	public Boolean withdrawal(@PathParam("vendor") String vendor,
			@PathParam("id") Long accountId,
			@PathParam("amount") Double amount);

}
