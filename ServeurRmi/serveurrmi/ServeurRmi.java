package serveurrmi;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import services.ChatService;

public class ServeurRmi {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        
       LocateRegistry.createRegistry(1099);
       ChatService skeleton = new ChatService();
       Naming.rebind("rmi://localhost:1099/Chat", skeleton);

    }
    
}
