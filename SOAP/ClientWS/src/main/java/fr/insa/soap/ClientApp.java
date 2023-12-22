package fr.insa.soap;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import fr.insa.soap.wsdltojava.UserWS;
import fr.insa.soap.wsdltojava.UserWS_Service;
import fr.insa.soap.wsdltojava.User;

public class ClientApp {

	public static void main(String[] args) throws MalformedURLException {
		final String adresse = "http://localhost:8089/userWS";
		
		final URL url = URI.create(adresse).toURL();
		
		final UserWS_Service service = new UserWS_Service(url);
		
		final UserWS port = service.getPort(UserWS.class);
		
		//Début appels méthodes
		User clea = new User();
		clea.setFirstName("Cléa");
		clea.setLastName("Aubery");
		clea.setId(1);
		
		User maxime = new User();
		maxime.setFirstName("Maxime");
		maxime.setLastName("David");
		maxime.setId(2);
		
		User V1 = new User();
		V1.setFirstName("Vali");
		V1.setLastName("Dateur");
		V1.setId(101);
		
		port.createUser(clea);
		port.createUser(maxime);
		port.createUser(V1);
		port.setValidator(1, 101);
		System.out.println(port.getUsers());
	}

}
