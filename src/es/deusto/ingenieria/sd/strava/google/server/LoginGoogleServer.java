package es.deusto.ingenieria.sd.strava.google.server;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.strava.google.remote.LoginGoogleGateway;
import es.deusto.ingenieria.sd.strava.google.remote.ILoginGoogleGateway;

public class LoginGoogleServer {

	public static void main(String[] args) {
		System.out.println("hello");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		
		try {
			System.out.println("intento");
			ILoginGoogleGateway remoteObject = LoginGoogleGateway.getInstance();			
			Naming.rebind(name, remoteObject);
			System.out.println(" * Currency Exchange Server '" + name + "' started!!");
		} catch (Exception ex) {
			System.out.println(" # Currency Exchange Server: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}