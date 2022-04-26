package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaImpl;

@Path("/start")
public class StartMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response execute(
			@Context HttpServletRequest request, 
			StartMancalaModel model) {

        var mancala = new MancalaImpl();
        PlayerModel player1 = model.getPlayer1();
		PlayerModel player2 = model.getPlayer2();
		
        HttpSession session = request.getSession(true);
        session.setAttribute("mancala", mancala);
        session.setAttribute("player1", player1.getName());
        session.setAttribute("player2", player2.getName());

		var output = new Mancala(mancala, player1.getName(), player2.getName());
		return Response.status(200).entity(output).build();
	}
}
