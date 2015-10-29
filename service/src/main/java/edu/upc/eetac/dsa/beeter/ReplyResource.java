package edu.upc.eetac.dsa.beeter;

import edu.upc.eetac.dsa.beeter.dao.ReplyDAO;
import edu.upc.eetac.dsa.beeter.dao.ReplyDAOImpl;
import edu.upc.eetac.dsa.beeter.entity.AuthToken;
import edu.upc.eetac.dsa.beeter.entity.Reply;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("/group/stings/reply")
public class ReplyResource
{
    @Context
    private SecurityContext securityContext;

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(BeeterMediaType.BEETER_REPLY)
    public Response createReply(@PathParam("id") String id, @FormParam("content") String content, @Context UriInfo uriInfo) throws URISyntaxException
    {
        if (content == null) {
            throw new BadRequestException("all parameters are mandatory");
        }
        ReplyDAO replyDAO            = new ReplyDAOImpl();
        Reply reply               = null;
        AuthToken authenticationToken = null;
        try {
            reply = replyDAO.createReply(securityContext.getUserPrincipal().getName(), id, content);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + reply.getId());
        return Response.created(uri).type(BeeterMediaType.BEETER_REPLY).entity(reply).build();
    }
}
