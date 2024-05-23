/*
 Pour acceder à la bas  de donnée
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class UserModel {
    public Connection bdd = null;
    
    private List<User>users = new ArrayList<>();//Couplage faible
        

    public UserModel() {
       //connexion bdd
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  

            this.bdd = DriverManager.getConnection("jdbc:mysql://localhost:3307/chat", "root", "");

            //récuperation des users
            String sql = "SELECT * FROM utilisateur";

            try{
                java.sql.Statement requete = this.bdd.createStatement();

                ResultSet trouver = ((java.sql.Statement) requete).executeQuery(sql);
                System.out.println(trouver);

                int id;
                String nom;
                String login;
                String password;

                while (trouver.next()) {
                    id = Integer.parseInt(trouver.getString("id"));
                    nom = trouver.getString("nom");
                    login = trouver.getString("login");
                    password = trouver.getString("mdp");

                    this.users.add(new User(id, nom, login, password));
                }
            }
            catch (SQLException e) {
            } 
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
    
    public User getUser(User user){
        for(User utilisateur : users){
            if(user.getLogin().equals(utilisateur.getLogin())& 
               user.getPassword().equals(utilisateur.getPassword())){
                return utilisateur;
            }
        }
        
        return null;
    }
    
    public User getUserByLoginPassword(String login, String password){
        for(User utilisateur : users){
            if(login.equals(utilisateur.getLogin())& 
               password.equals(utilisateur.getPassword())){
                return utilisateur;
            }
        }
        return null;
    }

    public void sauvegarderMessage(int idAuteur, String destinataire, String message){
        String sql = "INSERT INTO message (contenu, idUser, destinataire) " + 
                    "VALUES ('" + message + "', '" + idAuteur + "', '" + destinataire +"')";
        
        try{
            java.sql.Statement requete = this.bdd.createStatement();

            ((java.sql.Statement) requete).executeUpdate(sql);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Map<String, String> getAllMessage(String destinataire){
        Map<String, String> messages = new Hashtable<>();

        String sql = "SELECT u.nom AS nom, m.contenu AS contenu, m.id AS idMessage FROM message AS m " + 
                    "INNER JOIN utilisateur AS u ON m.idUser = u.id " + 
                    "WHERE m.destinataire = '" + destinataire + "' AND m.vu = 0";

        try{
            java.sql.Statement requete = this.bdd.createStatement();
            java.sql.Statement req = this.bdd.createStatement();

            ResultSet trouver = ((java.sql.Statement) requete).executeQuery(sql);
            
            //afficher les messages de la bdd
            try{
                int i = 0;
                while (trouver.next()){
                    String auteur = trouver.getString("nom");
                    String contenu = trouver.getString("contenu");
                    int idMessage = Integer.parseInt(trouver.getString("idMessage"));

                    messages.put(i + "", auteur + ":" +contenu);
                    
                    //changer le vu du message
                    sql = "UPDATE message SET vu = 1 WHERE id = '" + idMessage + "'";
                    ((java.sql.Statement) req).executeUpdate(sql);

                    i++;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return messages;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    public Map<String, String> getHistorique(String destinataire, int id){
        System.out.println(id);
        Map<String, String> messages = new Hashtable<>();

        String sql = "SELECT u.nom AS nom, m.contenu AS contenu, m.id AS idMessage FROM message AS m " + 
                    "INNER JOIN utilisateur AS u ON m.idUser = u.id " + 
                    " WHERE m.destinataire = '" + destinataire + "' OR m.idUser = '" + id + "'";

        try{
            java.sql.Statement requete = this.bdd.createStatement();

            ResultSet trouver = ((java.sql.Statement) requete).executeQuery(sql);
            
            try{
                int i = 0;
                while (trouver.next()){
                    String auteur = trouver.getString("nom");
                    String contenu = trouver.getString("contenu");

                    messages.put(i + "", auteur + ":" +contenu);

                    i++;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return messages;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

}

    

    
    
    

