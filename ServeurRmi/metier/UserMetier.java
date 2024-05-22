
package metier;

import java.util.Map;

import dao.User;
import dao.UserModel;


public class UserMetier {
    private UserModel userModel;
    private User user;
    public UserMetier(){
        this.userModel = new UserModel();
    }
    public User seConnecter(User user){    
        this.user = this.userModel.getUser(user);
        return this.user;
    }

    public void sendMessage(String auteur, String destinataire, String message){
        //sauvegader dans la bdd
        int idAuteur = this.user.getIdUser();
        this.userModel.sauvegarderMessage(idAuteur, destinataire, message);
    }

    public Map<String, String> recevoirMessage(String destinataire){
        return this.userModel.getAllMessage(destinataire);
        
    }

    public Map<String, String> recevoirHistorique(String destinataire, int id){
        return this.userModel.getHistorique(destinataire, id);
        
    }

    public String getNomUser(){
        return this.user.getNmUser();
    }

    public String getLogin(){
        return this.user.getLogin();
    }

}