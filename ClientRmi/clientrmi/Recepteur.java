package clientrmi;

import java.util.Map;

import services.IChatService;

public class Recepteur implements Runnable{
    private IChatService stub;

    public Recepteur(IChatService stub){
        this.stub = stub;
    }

    @Override
    public void run() {
        try {
            String destinataire = this.stub.getLogin();
            
            while (true) {
                Map<String, String> messages = this.stub.recevoirMessage(destinataire);
                //afficher les messages de la bdd
                try{
                    for(String i : messages.keySet()){
                        String message = messages.get(i);
    
                        String[] detailMessage = message.split(":");
                        String auteur = detailMessage[0].trim();
                        String contenu = detailMessage[1].trim();
    
                        System.out.println(auteur + ": " + contenu);
                    }
                }
                catch (Exception e) {
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
