package clientrmi;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.IChatService;

public class Envoyeur implements Runnable {
    private IChatService stub;

    public Envoyeur(IChatService stub){
        this.stub = stub;
    }
    
    @Override
    public void run() {
        Scanner saisie = new Scanner(System.in);

        String entre;
        while (true) {
            try{
                String auteur = this.stub.getNomUser();

                System.out.print(auteur + ": ");
                entre = saisie.nextLine();

                //vérifier si la chaine des correcte (nomDestinataire:message)
                Pattern expression = Pattern.compile("[a-zA-Z0-9!@#$%^&*()_=\\s]+:[a-zA-Z0-9!@#$%^&*()_=\\s]+");
                Matcher validateur = expression.matcher(entre);
    
                if(validateur.matches()){
                    String[] partieMessage = entre.split(":", 2);
    
                    String destinataire = partieMessage[0].trim();
                    String message = partieMessage[1].trim();
    
                    this.stub.sendMessage(auteur, destinataire, message);
                }
                else{
                    System.out.println("format du message incorrecte");
                }
            }
            catch(Exception e){
                
            }

        }
    }
    
}
