package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import dao.User;

public interface IChatService extends Remote{
    public User connexion(User user) throws RemoteException;
    public void sendMessage(String auteur, String destinataire, String  message) throws RemoteException;
    public Map<String, String> recevoirMessage(String destinataire) throws RemoteException;
    public Map<String, String> recevoirHistorique(String destinataire, int id) throws RemoteException;
    public String getLogin() throws RemoteException;
    public String getNomUser() throws RemoteException;

}
