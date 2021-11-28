package es.deusto.ingenieria.sd.strava.google.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginGoogleGateway extends UnicastRemoteObject implements ILoginGoogleGateway {
	private static final long serialVersionUID = 1L;

	protected static final String URL = "https://free.currconv.com/api/v7/convert?q=USD_EUR,GBP_EUR&compact=ultra&apiKey=42168425fe4a2d6c6a0e";
	public static String USUARIO_GOOGLE = "Jon";
	public static String GOOGLE_USUARIO = "feefvriodjneisffedwsdw";
	public static float USD_RATE = 0.85f;
	public static float GBP_RATE = 1.17f;
	
	//Attribute for the Singleton pattern
	public static LoginGoogleGateway instance;
			
	private LoginGoogleGateway() throws RemoteException {
		super();
		getConversionRates();
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
	public String login() throws RemoteException {
		System.out.println(" - Getting Login confirm from 'Google.com'....");
		
		try {			
			HttpURLConnection con = (HttpURLConnection) (new URL(URL).openConnection());			
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			con.disconnect();

			inputLine = response.toString();
			USD_RATE = Float.parseFloat(inputLine.substring(inputLine.indexOf(":")+1, inputLine.indexOf(",")));
			GBP_RATE = Float.parseFloat(inputLine.substring(inputLine.lastIndexOf(":")+1, inputLine.indexOf("}")));
		} catch(Exception ex) {
			System.out.println("  # Error getting conversion rates(): " + ex.getMessage());
		}
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
		
		
		System.out.println(" - Exchange rates obtained (" + dateFormatter.format(Calendar.getInstance().getTime()) + ")!!");
		System.out.println("\t- EURO to USD rate: " + USD_RATE);
		System.out.println("\t- EURO to GBP rate: " + GBP_RATE);
		
		return null;
	}

	
	private static final void getConversionRates() {
		System.out.println(" - Getting exchange rates from 'free.currconv.com'....");
		
		try {			
			HttpURLConnection con = (HttpURLConnection) (new URL(URL).openConnection());			
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			con.disconnect();

			inputLine = response.toString();
			USD_RATE = Float.parseFloat(inputLine.substring(inputLine.indexOf(":")+1, inputLine.indexOf(",")));
			GBP_RATE = Float.parseFloat(inputLine.substring(inputLine.lastIndexOf(":")+1, inputLine.indexOf("}")));
		} catch(Exception ex) {
			System.out.println("  # Error getting conversion rates(): " + ex.getMessage());
		}
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY - HH:mm");
		
		
		System.out.println(" - Exchange rates obtained (" + dateFormatter.format(Calendar.getInstance().getTime()) + ")!!");
		System.out.println("\t- EURO to USD rate: " + USD_RATE);
		System.out.println("\t- EURO to GBP rate: " + GBP_RATE);
	}
	
	public float getUSDRate() throws RemoteException {
		getConversionRates();
		return USD_RATE;
	}

	public float getGBPRate() throws RemoteException {
		getConversionRates();
		return GBP_RATE;
	}
	
	
}