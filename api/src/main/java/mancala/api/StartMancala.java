package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.Mancala;

@Path("/start")
public class StartMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response start(
			@Context HttpServletRequest request, 
			PlayerInputDTO players
	) {
		// Create HTTP session.
		HttpSession session = request.getSession(true);

		// Initialize game.
        var mancala = new Mancala(players.getNameplayer1(), players.getNameplayer2());

		// Attach game to session.
        session.setAttribute("mancala", mancala);

		// Use the game to create a DTO.
		var output = new MancalaDTO(mancala);

		// Send DTO back in response.
		return Response.status(200).entity(output).build();
	}
}
