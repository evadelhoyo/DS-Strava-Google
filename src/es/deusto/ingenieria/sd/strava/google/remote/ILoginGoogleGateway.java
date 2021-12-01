package es.deusto.ingenieria.sd.strava.google.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILoginGoogleGateway extends Remote {
	public boolean login(String email, String comprueba) throws RemoteException;
}
