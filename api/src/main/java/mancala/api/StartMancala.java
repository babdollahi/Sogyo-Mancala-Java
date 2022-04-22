package mancala.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaImpl;
import mancala.domain.Player;

@Path("/start")
public class StartMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response start(@Context HttpServletRequest request, StartMancalaModel model) {
		
        var player1 = new Player(model.getPlayer1().getName());
        var player2 = new Player(model.getPlayer2().getName());
        var mancala = new MancalaImpl(player1, player2);

        HttpSession session = request.getSession(true);
        session.setAttribute("mancala", mancala);

		var output = new MancalaModel(model.getPlayer1(), model.getPlayer2());
		return Response.status(200).entity(output).build();
	}
}
