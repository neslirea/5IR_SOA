package fr.insa.soa.RestProject.Client;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

public class ClientRest {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		Response response = client.target("http://localhost:8080/RestProject/webapi/user/1").request().get();
		System.out.println(response.readEntity(String.class));
		
		response = client.target("http://localhost:8080/RestProject/webapi/user/2").request().get();
		System.out.println(response.readEntity(String.class));
		
		response = client.target("http://localhost:8080/RestProject/webapi/user/1/setValidator/3").request().put(Entity.text(""));
		System.out.println(response.readEntity(String.class));
		
		String requestBody = "{ \"id\":3, \"firstName\":\"Jean\", \"lastName\":\"Neymar\", \"validator\":5}";
		response = client.target("http://localhost:8080/RestProject/webapi/user").request().post(Entity.json(requestBody));
		System.out.println(response.readEntity(String.class));
		
	}

}
