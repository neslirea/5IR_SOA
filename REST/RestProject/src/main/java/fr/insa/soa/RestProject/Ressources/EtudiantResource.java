package fr.insa.soa.RestProject.Ressources;

import fr.insa.soa.RestProject.Data.Binome;
import fr.insa.soa.RestProject.Data.Etudiant;
import fr.insa.soa.RestProject.Data.Stage;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;


@Path("etudiant")
public class EtudiantResource {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant getEtudiant(@Context UriInfo uriInfo) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Aubawi");
        etudiant.setPrenom("Claym");
        etudiant.setId(0);
        etudiant.addLink(getUriforSelf(uriInfo, etudiant), "self", "GET");
        etudiant.addLink(getUriforStage(uriInfo), "Stage", "GET");
        return etudiant;
    }

    @GET
    @Path("/{idEtudiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant getEtudiant(@PathParam("idEtudiant") int id, @Context UriInfo uriInfo) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Aubawi");
        etudiant.setPrenom("Claym");
        etudiant.setId(1);
        if (id<1) {
        	Etudiant etudiant2 = new Etudiant();
        	etudiant2.setNom("Shinary");
        	etudiant2.setPrenom("Aymea");
        	etudiant2.setBinome(new Binome());
        	etudiant2.getBinome().setNom("Aubawi");
        	etudiant2.getBinome().setPrenom("Claym");
            etudiant2.addLink(getUriforSelf(uriInfo, etudiant), "binome", "GET");
            etudiant = etudiant2;
        }
        etudiant.addLink(getUriforSelf(uriInfo, etudiant), "self", "GET");
        etudiant.addLink(getUriforStage(uriInfo), "Stage", "GET");
        return etudiant;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEtudiant(Etudiant etudiant) {
    	System.out.println("Ajout de l'étudiant "+etudiant.getNom()+" "+etudiant.getPrenom()+" réussie!");
    	if(etudiant.getBinome()!=null) {
    		System.out.println("Son binôme est "+etudiant.getBinome().getPrenom()+" "+etudiant.getBinome().getNom());
    	}
    }
    
    private String getUriforStage(UriInfo uriInfo) {
    	String url = uriInfo.getBaseUriBuilder()
    			.path(StageResource.class)
    			.build()
    			.toString();
    	return url;
    }
    
    private String getUriforSelf(UriInfo uriInfo, Etudiant etudiant) {
    	String url = uriInfo.getBaseUriBuilder()
    			.path(EtudiantResource.class)
    			.path(Long.toString(etudiant.getId()))
    			.build()
    			.toString();
    	return url;
    }
}
