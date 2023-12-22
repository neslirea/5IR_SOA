package fr.insa.soa.RestProject.Ressources;

import java.util.Date;

import fr.insa.soa.RestProject.Data.Etudiant;
import fr.insa.soa.RestProject.Data.Stage;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;


@Path("stage")
public class StageResource {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stage getStage() {
        Stage stage = new Stage();
        stage.setEvaluation(12);
        stage.setCompetences("Oui. Et Non.");
        stage.setAnnée(new Date().getYear()+1900);
        return stage;
    }
    
}
