package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import dao.User;
import metier.UserMetier;

public class ChatService extends UnicastRemoteObject implements IChatService{
    private UserMetier userMetier;

    public ChatService() throws RemoteException{
        super();
    }
    
    @Override
    public User connexion(User user) throws RemoteException {
        this.userMetier = new UserMetier();
        User utilisateur = this.userMetier.seConnecter(user);

        System.out.println("Utilisateur " + utilisateur.getNmUser());
        return utilisateur;
    }

    @Override
    public void sendMessage(String auteur, String destinataire, String  message) throws RemoteException{
        this.userMetier.sendMessage(auteur, destinataire, message);
    }

    @Override
    public Map<String, String> recevoirMessage(String destinataire) throws RemoteException{
        return this.userMetier.recevoirMessage(destinataire);
    }
    
    @Override
    public Map<String, String> recevoirHistorique(String destinataire, int id) throws RemoteException{
        return this.userMetier.recevoirHistorique(destinataire, id);
    }

    @Override
    public String getLogin(){
        return this.userMetier.getLogin();
    }

    @Override
    public String getNomUser() throws RemoteException{
        return this.userMetier.getNomUser();
    }
}
