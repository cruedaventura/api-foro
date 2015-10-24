package edu.upc.eetac.dsa.beeter;

import edu.upc.eetac.dsa.beeter.dao.GroupDAO;
import edu.upc.eetac.dsa.beeter.dao.GroupDAOImpl;
import edu.upc.eetac.dsa.beeter.entity.AuthToken;
import edu.upc.eetac.dsa.beeter.entity.Group;
import edu.upc.eetac.dsa.beeter.entity.GroupCollection;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("group")
public class GroupResource
{
    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(BeeterMediaType.BEETER_STING)
    public Response createGroup(@FormParam("name") String name, @Context UriInfo uriInfo) throws URISyntaxException
    {
        if (name == null) {
            throw new BadRequestException("all parameters are mandatory");
        }
        GroupDAO  groupDAO            = new GroupDAOImpl();
        Group     group               = null;
        AuthToken authenticationToken = null;
        try {
            group = groupDAO.createGroup(this.securityContext.getUserPrincipal().getName(), name);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }

        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + group.getId());

        return Response.created(uri).type(BeeterMediaType.BEETER_GROUP).entity(group).build();
    }

    @GET
    @Produces(BeeterMediaType.BEETER_GROUP_COLLECTION)
    public GroupCollection getGroup()
    {
        GroupCollection groupCollection = null;
        GroupDAO        groupDAO        = new GroupDAOImpl();
        try {
            groupCollection = groupDAO.getGroups();
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return groupCollection;
    }

}
