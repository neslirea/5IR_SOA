package fr.insa.soa.RestProject.Client;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

public class ClientRest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/RestProject/webapi/comparator/longueur/Toulouse").request().get();
		System.out.println(response.readEntity(String.class));
		
		response = client.target("http://localhost:8080/RestProject/webapi/etudiant/1").request().get();
		System.out.println(response.readEntity(String.class));
		
	}

}
