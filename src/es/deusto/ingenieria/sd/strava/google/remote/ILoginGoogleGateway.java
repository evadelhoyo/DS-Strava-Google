package es.deusto.ingenieria.sd.strava.google.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILoginGoogleGateway extends Remote {
	public float getUSDRate() throws RemoteException;
	public float getGBPRate() throws RemoteException;
	public String login() throws RemoteException;
}
