/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author rachad
 */
public class BruitS1 {
    
     public static void main(String args[]) throws IOException{
        
        String hote = "127.0.0.1" ;
        int port = 5000 ;
        Socket soc = new Socket (hote, port);
        OutputStream streamOut = soc.getOutputStream(); 
        OutputStreamWriter sortie = new OutputStreamWriter (streamOut) ;
        
        //lire une ligne du fichier
        //l'envoyer au serveurBruit
        //sortie.write(data)
   
 
        
       
        
        sortie.write("message envoye au serveur par le client A \n") ; 
        sortie.flush(); // pour forcer l'envoi de la ligne 
        
        
       
    }
    
}
