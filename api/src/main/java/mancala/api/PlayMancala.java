package mancala.api;

import org.eclipse.jetty.server.session.Session;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.Playable;

@Path("/play")
public class PlayMancala {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(
            @Context HttpServletRequest request,
            PlayDTO index) {
        // Retrieve HTTP session.
        HttpSession session = request.getSession();

        // Retrieve game.
        Playable game = (Playable) session.getAttribute("mancala");
        // Play a pit.
        game.playPit(index.getIndex());
        game.getWinner();

        // Use the game to create a DTO.
        MancalaDTO output =  new MancalaDTO(game);

        // Send DTO back in response.
        return Response.status(200).entity(output).build();
    }

}
