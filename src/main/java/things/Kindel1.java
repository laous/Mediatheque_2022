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
import java.util.Scanner;


public class Kindel1 {


    public static void main(String args[]) throws IOException {

        String hote = "127.0.0.1";
        int port = 1000;
        Socket soc = new Socket(hote, port);

        Scanner sc = new Scanner(System.in);

        OutputStream streamOut = soc.getOutputStream();
        OutputStreamWriter sortie = new OutputStreamWriter(streamOut);

        InputStream streamIn = soc.getInputStream();
        BufferedReader entree = new BufferedReader(new InputStreamReader(streamIn));

//        sortie.write("message envoye au serveur par le client A \n") ;
//        sortie.flush(); // pour forcer l'envoi de la ligne

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

        System.out.println("Bienvenue\n Entrer votre username : ");
        String username = sc.next();
        System.out.println("Entrer password : ");
        String password = sc.next();

        sortie.write(username + "\n");
        sortie.write(password + "\n");
        sortie.flush();  // Forcer l'envoi
        int choix = 0;

        while (choix != 5) {
            System.out.println("----------Menu------------");
            System.out.println("1. Chercher un document par titre");
            System.out.println("2. Chercher un document par editeur");
            System.out.println("3. Chercher un document par edition");
            System.out.println("4. Chercher un document par auteur");
            System.out.println("5. Quitter");
            System.out.println("--------------------------");

            System.out.println("Choix: ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> searchTitle(entree, sortie, sc);
                case 2 -> searchEditeur(entree, sortie, sc);
                case 3 -> searchEdition(entree, sortie, sc);
                case 4 -> searchAuteur(entree, sortie, sc);
                case 5 -> {
                }
                default -> System.out.println("Choix non valide");
            }
        }
    }

    public static void searchTitle(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.println("Entrer titre : ");
        String titre = sc.nextLine();
        sortie.write("titre\n");
        sortie.flush();
        sortie.write(titre + "\n");
        sortie.flush();
        String respnse = entree.readLine();

        System.out.println(respnse);
    }


    public static void searchEditeur(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.println("Entrer editeur : ");
        String editeur = sc.nextLine();
        sortie.write("editeur\n");
        sortie.write(editeur + "\n");
        sortie.flush();
        String respnse = entree.readLine();

        System.out.println(respnse);
    }

    public static void searchEdition(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.println("Entrer edition : ");
        String edition = sc.nextLine();
        sortie.write("edition\n");
        sortie.write(edition + "\n");
        sortie.flush();
        String respnse = entree.readLine();

        System.out.println(respnse);
    }

    public static void searchAuteur(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.println("Entrer auteur : ");
        String auteur = sc.nextLine();
        sortie.write("auteur\n");
        sortie.write(auteur + "\n");
        sortie.flush();
        String respnse = entree.readLine();

        System.out.println(respnse);
    }
}
