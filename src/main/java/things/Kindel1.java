/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import model.Kindle;

import java.io.*;
import java.net.Socket;
import java.util.*;


public class Kindel1 {


    public static void main(String args[]) throws IOException {

        Kindle k = new Kindle("AA3", "M12");

        String hote = "127.0.0.1";
        int port = 1000;
        Socket soc = new Socket(hote, port);

        Scanner sc = new Scanner(System.in);

        OutputStream streamOut = soc.getOutputStream();
        OutputStreamWriter sortie = new OutputStreamWriter(streamOut);

        InputStream streamIn = soc.getInputStream();
        BufferedReader entree = new BufferedReader(new InputStreamReader(streamIn));

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

        // Envoyer infos d'authentification
        authentication(entree, sortie, sc);

        // Envoyer infos kindle
        sendKindleInfos(entree, sortie, k);

        // Affichage du menu de choix
        displayMenu(entree,sortie,sc);



    }

    public static void authentication(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {

        System.out.print("Bienvenue\nEntrer votre username : ");
        String username = sc.next();
        System.out.print("Entrer password : ");
        String password = sc.next();

        sortie.write(username + "\n");
        sortie.write(password + "\n");
        sortie.flush();  // Forcer l'envoi
        String response = entree.readLine();

        while (Objects.equals(response, "unauthorized")) {
            System.out.print("Données invalide\nEntrer votre username : ");
            username = sc.next();
            System.out.print("Entrer password : ");
            password = sc.next();

            sortie.write(username + "\n");
            sortie.write(password + "\n");
            sortie.flush();  // Forcer l'envoi
            response = entree.readLine();
        }
    }

    public static void sendKindleInfos(BufferedReader entree, OutputStreamWriter sortie, Kindle k) throws IOException {
        /**
         *  TODO: Send kindle infos to ServerKindles
         *  Infos Kindle : code_kindle and mac
         *  Receive response from server
         */
        sortie.write(k.getCode_kindle() + "\n");
        sortie.flush();
    }

    public static void displayMenu(BufferedReader entree, OutputStreamWriter sortie,Scanner sc) throws IOException {
        int choix = 0;

        while (choix != 6) {
            System.out.println("----------Menu------------");
            System.out.println("1. Liste des documents");
            System.out.println("2. Chercher un document par titre");
            System.out.println("3. Chercher un document par editeur");
            System.out.println("4. Chercher un document par edition");
            System.out.println("5. Chercher un document par auteur");
            System.out.println("6. Quitter");
            System.out.println("--------------------------");

            System.out.print("Choix: ");



            // TODO: handle Error if not int value provided
//            try {
                choix = sc.nextInt();
                sc.nextLine();
//            } catch (InputMismatchException e) {
//                e.printStackTrace();
//            }

            switch (choix) {
                case 1 -> allDocs(entree, sortie);
                case 2 -> searchTitle(entree, sortie, sc);
                case 3 -> searchEditeur(entree, sortie, sc);
                case 4 -> searchEdition(entree, sortie, sc);
                case 5 -> searchAuteur(entree, sortie, sc);
                case 6 -> quit(sortie);
                default -> System.out.println("Choix invalide. Ressayer!!");
            }
        }
    }

    public static void allDocs(BufferedReader entree, OutputStreamWriter sortie) throws IOException {
        sortie.write("all\n");
        sortie.flush();
        List<String> response = readAllLines(entree);

        printListOfString(response); // print response
        System.out.println("");
    }

    public static void searchTitle(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.print("Entrer titre : ");
        String titre = sc.nextLine();
        sortie.write("titre\n");
        sortie.flush();
        sortie.write(titre + "\n");
        sortie.flush();
        String respnse = entree.readLine();

        System.out.println(respnse);
    }

    public static void searchEditeur(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.print("Entrer editeur : ");
        String editeur = sc.nextLine();
        sortie.write("editeur\n");
        sortie.write(editeur + "\n");
        sortie.flush();
        List<String> response = readAllLines(entree);

        printListOfString(response);
    }

    public static void searchEdition(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.print("Entrer edition : ");
        String edition = sc.nextLine();
        sortie.write("edition\n");
        sortie.write(edition + "\n");
        sortie.flush();
        List<String> response = readAllLines(entree);

        printListOfString(response);
    }

    public static void searchAuteur(BufferedReader entree, OutputStreamWriter sortie, Scanner sc) throws IOException {
        System.out.print("Entrer auteur : ");
        String auteur = sc.nextLine();
        sortie.write("auteur\n");
        sortie.write(auteur + "\n");
        sortie.flush();
        List<String> response = readAllLines(entree);

        printListOfString(response);

    }

    public static void quit(OutputStreamWriter sortie) throws IOException {
        System.out.println("Bye!!");
        sortie.write("quit\n");
        sortie.flush();
    }

    private static List<String> readAllLines(BufferedReader br) throws IOException {
        List<String> data = new ArrayList<>();
        for (String line; !Objects.equals(line = br.readLine(), ""); ) {
            data.add(line);
        }
        return data;
    }

    private static void printListOfString(List<String> ls) {
        for (String s : ls) {
            System.out.println(s);
        }
    }
}
