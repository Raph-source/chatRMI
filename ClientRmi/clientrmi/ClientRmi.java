package clientrmi;

import dao.User;
import java.net.MalformedURLException;
import services.IChatService;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Scanner;

public class ClientRmi {

    public static void main(String[] args) throws NotBoundException, RemoteException, MalformedURLException, NullPointerException{
        Scanner saisie = new Scanner(System.in);
        String login;
        String password;

        System.out.print("Login: ");
        login = saisie.nextLine();
        System.out.print("Mot de passe: ");
        password = saisie.nextLine();

        User utilisateur = new User(login, password);

        IChatService stub = (IChatService) Naming.lookup("rmi://localhost:1099/Chat");
        User user = stub.connexion(utilisateur);
        
        //le recepteur et l'envoyeur
        Thread recepteur = new Thread(new Recepteur(stub));
        Thread envoyeur = new Thread(new Envoyeur(stub));

        if(user != null){
            System.out.println("Bienvenue " + user.getNmUser());

            System.out.println("(C)hat\n(H)istorique\n==>");
            String choix = saisie.nextLine();

            if(choix.charAt(0) == 'C'){
                recepteur.start();
                envoyeur.start();
            }
            else if(choix.charAt(0) == 'H'){
                String destinataire = user.getLogin();
                int id = user.getIdUser();
                Map<String, String> historique = stub.recevoirHistorique(destinataire, id);

                for(String i : historique.keySet()){
                    String message = historique.get(i);

                    String[] detailMessage = message.split(":");
                    String auteur = detailMessage[0].trim();
                    String contenu = detailMessage[1].trim();

                    System.out.println(auteur + ": " + contenu);
                }
            }
            else{
                System.out.println("choix incorrecte");
            }
        }else{
            System.out.println("Login ou mot de passe incorrect");
        }
    }
}
     


