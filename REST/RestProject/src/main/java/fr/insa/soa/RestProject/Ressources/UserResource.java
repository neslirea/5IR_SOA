package fr.insa.soa.RestProject.Ressources;

import fr.insa.soa.RestProject.Data.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;


@Path("user")
public class UserResource {
	
    @GET
    @Path("/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("idUser") int id, @Context UriInfo uriInfo) {
    	User user = new User();
    	user.setLastName("Aubawi");
    	user.setFirstName("Claym");
    	user.setId(1);
        if (id==2) {
        	User user2 = new User();
        	user2.setLastName("Shinary");
        	user2.setFirstName("Aymea");
        	user2.setId(id);
        	user2.setValidator(user.getId());
            user = user2;
        }
    	user.addLink(getUriforSelf(uriInfo, user), "self", "GET");
    	if(user.hasValidator()) {
        	user.addLink(getUriforValidator(uriInfo, user), "validator", "GET");
    	}
        return user;
    }
    
    @PUT
    @Path("/{idUser}/setValidator/{idValidator}")
    @Produces(MediaType.APPLICATION_JSON)
    public void setValidator(@PathParam("idUser") int id, @PathParam("idUser") int id_validator, @Context UriInfo uriInfo) {

    	System.out.println("Modification de l'utilisateur "+ id+". Modification de son valideur :  "+ id_validator +" réussie!");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
    	System.out.println("Ajout de l'utilisateur "+user.getLastName()+" "+user.getFirstName()+" réussie!");
    	if(user.hasValidator()) {
    		System.out.println("Son valideur est "+user.getValidator());
    	}
    }
    
    private String getUriforValidator(UriInfo uriInfo, User user) {
    	String url = uriInfo.getBaseUriBuilder()
    			.path(UserResource.class)
    			.path(Long.toString(user.getValidator()))
    			.build()
    			.toString();
    	return url;
    }
    
    private String getUriforSelf(UriInfo uriInfo, User user) {
    	String url = uriInfo.getBaseUriBuilder()
    			.path(UserResource.class)
    			.path(Long.toString(user.getId()))
    			.build()
    			.toString();
    	return url;
    }
}
