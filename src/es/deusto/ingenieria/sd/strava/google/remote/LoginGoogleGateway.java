package es.deusto.ingenieria.sd.strava.google.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginGoogleGateway extends UnicastRemoteObject implements ILoginGoogleGateway {
	private static final long serialVersionUID = 1L;
	
	//Attribute for the Singleton pattern
	public static LoginGoogleGateway instance;
			
	private LoginGoogleGateway() throws RemoteException {
		super();
	}
	
	public static LoginGoogleGateway getInstance() {
		if (instance == null) {
			try {
				instance = new LoginGoogleGateway();
			} catch(Exception ex) {
				System.err.println("  # Error initializing service(): " + ex.getMessage());
			}
		}
		
		return instance;
	}

	@Override
	public boolean login(String email, String comprueba) throws RemoteException {
		System.out.println("estoy en google");
		if(email.equals(comprueba)) {
			System.out.println("el email es correcto");
			return true;
		} else {
			return false;
		}
	}

	
	
}