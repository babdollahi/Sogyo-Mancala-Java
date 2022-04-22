package mancala.api;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaImpl;

@Path("/start")
public class StartMancala {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response start(@Context HttpServletRequest request, StartMancalaModel model) {
        String namePlayer1 = model.getPlayer1().getName();
		String namePlayer2 = model.getPlayer2().getName();
		
        var mancala = new MancalaImpl();

        HttpSession session = request.getSession(true);
        session.setAttribute("mancala", mancala);
        session.setAttribute("player1", namePlayer1);
        session.setAttribute("player2", namePlayer2);

		var output = new Mancala(mancala, namePlayer1, namePlayer2);
		return Response.status(200).entity(output).build();
	}
}
