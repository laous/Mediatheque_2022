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
public class Kindel1 {
    
    
    public static void main(String args[]) throws IOException{
        
        String hote = "127.0.0.1" ;
        int port = 1000 ;
        Socket soc = new Socket (hote, port);
       
        OutputStream streamOut = soc.getOutputStream(); 
        OutputStreamWriter sortie = new OutputStreamWriter (streamOut) ; 
        
        InputStream streamIn  = soc.getInputStream ();
        BufferedReader entree = new BufferedReader (new InputStreamReader (streamIn));
        
        sortie.write("message envoye au serveur par le client A \n") ; 
        sortie.flush(); // pour forcer l'envoi de la ligne 
        
        //enchainement a definir selon le protocole en se servant de 
                //String message = entree.readLine();
                //sortie.write(message) ; 
            
        /**Protocole
         * envoyer information d'authentification
         * reception decision authentification
         * Affichage du menu
         * **** 1 chercher un document par titre
         * **** 2 chercher un document par editeur
         * **** 3 chercher un document par edition
         * **** 4 chercher un document par auteur
         * **** 5 se deconnecter
         * Afficher la liste des resultats
         * chosir un docuemnt à visualiser ou retourner au menu principal
        **/
       
    }
    
}
