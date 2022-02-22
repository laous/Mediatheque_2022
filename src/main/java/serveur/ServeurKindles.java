/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import dao.AdherentUtile;
import dao.AuthenticationUtile;
import dao.DocumentUtile;
import model.Document;

import javax.print.Doc;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ce Thread sera lancer au demarage du systeme pour assurer les
 * communicartions avec les kindles sans interompre les taches de gestion
 * qui seront relisees par l'admin
 */
public class ServeurKindles extends Thread {


    public void run() {
        try {
            ServerSocket sersoc = new ServerSocket(1000);
            while (true) {
                Socket soc = sersoc.accept();
                SocketThread st = new SocketThread(soc);
                Thread t = new Thread(st);
                t.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServeurKindles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Classe interne
    class SocketThread implements Runnable {

        private Socket soc;
        DocumentUtile docDAO;
        AdherentUtile adDAO;
        AuthenticationUtile authDAO;

        public SocketThread(Socket soc) {
            this.soc = soc;
        }

        public void run() {
            OutputStream streamOut = null;
            InputStream streamIn = null;

            try {
                streamOut = soc.getOutputStream();
                OutputStreamWriter sortie = new OutputStreamWriter(streamOut);
                streamIn = soc.getInputStream();
                BufferedReader entree = new BufferedReader(new InputStreamReader(streamIn));

                //enchainement a definir selon le protocole en se seravt de
                //String message = entree.readLine();
                //sortie.write(message) ;
                /**
                 * recevoir information d'authentification
                 * envoyer decision authentification
                 * attendre une requete de recherche de la part de l'adherent
                 * anvoyer le resultat de la requete
                 * repeter les deux taches precedents jusqu'a la deconnexion de l'adherent
                 * une fois que l'adherent est deconnecté alors return
                 **/
                boolean repeat = true;
                String type = authentication(entree, sortie); // authentication
                while (repeat) {
                    if (type != null) {
                        String test = entree.readLine();
                        switch (test) {
                            case "all" -> getAllDocs(sortie);
                            case "titre" -> getDocumentByTitre(entree, sortie);
                            case "editeur" -> getDocumentsByEditeur(entree, sortie);
                            case "edition" -> getDocumentsByEdition(entree, sortie);
                            case "auteur" -> getDocumentsByAuteur(entree, sortie);
                            case "quit" -> repeat = false;
                        }
                    } else {
                        sortie.write("Cannot validate credentials\n");
                        sortie.flush();
                        type = authentication(entree, sortie);
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(ServeurKindles.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    streamOut.close();
                    streamIn.close();
                    soc.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServeurKindles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public String authentication(BufferedReader entree, OutputStreamWriter sortie) throws IOException, SQLException {
            System.out.println("----Authentication----");
            String username = entree.readLine();
            String password = entree.readLine();

            System.out.println("Credentials received");
//           return authDAO.authentication(username,password);
            return "Jonas";
        }


        public void getAllDocs(OutputStreamWriter sortie) throws IOException, SQLException {
           LinkedList doc = docDAO.getAllDocuments();
            sortie.write("All documents\n");
            sortie.flush();
        }

        public void getDocumentByTitre(BufferedReader entree, OutputStreamWriter sortie) throws IOException, SQLException {
            String titre = entree.readLine();
//           Document doc = docDAO.getDocumentByTitre(titre);
            sortie.write("Titre received here's a response\n");
            sortie.flush();
        }

        public void getDocumentsByEditeur(BufferedReader entree, OutputStreamWriter sortie) throws IOException, SQLException {
            String editeur = entree.readLine();
//           LinkedList doc = docDAO.getDocumentsByEditeur(editeur);
            sortie.write("Editeur received here's a response\n");
            sortie.flush();
        }

        public void getDocumentsByEdition(BufferedReader entree, OutputStreamWriter sortie) throws IOException, SQLException {
            String edition = entree.readLine();
//           LinkedList doc = docDAO.getDocumentsByEdition(edition);
            sortie.write("Edition received here's a response\n");
            sortie.flush();
        }

        public void getDocumentsByAuteur(BufferedReader entree, OutputStreamWriter sortie) throws IOException, SQLException {
            String auteur = entree.readLine();
//           LinkedList doc = docDAO.getDocumentsByAuteur(edition);
            sortie.write("Auteur received here's a response\n");
            sortie.flush();
        }
    }

} 
