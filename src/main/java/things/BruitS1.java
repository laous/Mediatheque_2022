/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rachad
 */
public class BruitS1 {
    
     public static void main(String args[]) throws IOException, InterruptedException {
        
        String hote = "127.0.0.1" ;
        int port = 5000 ;
        Socket soc = new Socket (hote, port);
        OutputStream streamOut = soc.getOutputStream();
        OutputStreamWriter sortie = new OutputStreamWriter (streamOut) ;
        
        //lire une ligne du fichier
         BufferedReader fs = new BufferedReader(new FileReader("D:\\Top Secret\\WebDev\\Java Workspace\\mediatheque-final\\src\\main\\java\\things\\bruit1.txt"));
         String data= null;
         while((data = fs.readLine()) != null){
             System.out.println(data);
             TimeUnit.SECONDS.sleep(3);
             //l'envoyer au serveurBruit
             sortie.write(data+"\n");

             sortie.flush();
         }


//        sortie.write("message envoye au serveur par le client A \n") ;
//        sortie.flush(); // pour forcer l'envoi de la ligne
//
        
       
    }
    
}
