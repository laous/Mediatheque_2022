package serveur;


import dao.AdherentUtile;
import dao.DocumentUtile;
import dao.EmpruntUtile;
import dao.KindleUtile;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import model.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rachad
 */
public class Mediatheque {
    
    // a remplacer par les utiles 
     static DocumentUtile documentDAO;

    static {
        try {
            documentDAO = new DocumentUtile();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ;
     static AdherentUtile adherentDAO;

    static {
        try {
            adherentDAO = new AdherentUtile();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static KindleUtile kindleDAO;

    static {
        try {
            kindleDAO = new KindleUtile();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static EmpruntUtile empruntDAO;

    static {
        try {
            empruntDAO = new EmpruntUtile();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public Mediatheque() throws SQLException{
//
//          documentDAO= new DocumentUtile() ;
//          adherentDAO= new AdherentUtile();
//          kindleDAO= new KindleUtile();
//          empruntDAO= new EmpruntUtile();
//    }

    public static void print(String str){
        System.out.println(str);
    }
    public static boolean auth(String username , String password) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from admin where username like '"+username+"' and password like '"+password+"'");
        while(rs.next()){
            return true;

        }
        return false;
    }

    public static void main(String args[]) throws IOException, SQLException {
        String received;

        //Authentification  ==> BD
        Scanner sc=new Scanner(System.in);
        System.out.println("Entrez votre username:");
        String user = sc.nextLine();
        System.out.println("Entrez votre password:");
        String pw = sc.nextLine();

        if(auth(user,pw)){
            print("Connection reussie!");
        }else{
            print("Connection Echoue :(");
            System.exit(0);
        }

//        if(user != username || pw != password){
//            System.exit(0);
//        }


        //Demarage en background du serveur de communication avec les Kindles ==> Sockets+Threds
        ServeurKindles sk= new ServeurKindles();
        sk.start();
        //Affichage du menu CRUD ==> BD


        //***1- Gestion des Kindles
        //***2- Gestion des Documents
        //***3- Gestion des Adherents
        //***4- Gestion des Emprunts
        //***5- m les kindles temporairement  ==>sk.sleep(xx)
        //***6- Reprendre le serveur des kindles  ==> sk.interrupt() // a ne pas afficher si les serveur  est deja demarre
        //***7- Se Deconnecter

        while (true){

                //Menu Admin
                print(
                        "\nVeuillez choisir une option\n"+
                                "1- Gestion des Kindles\n"+
                                "2- Gestion des Documents\n"+
                                "3- Gestion des Adherents\n"+
                                "4- Gestion des Emprunts\n"+
                                "5- Quitter"
                );

                // la reponse de l'administrateur
                received = sc.nextLine();

                if(received.equals("5"))
                {
                    System.exit(0);
                }

                String code_kindle , choice, kindle_mac;
                // Adherent variables
                String typeAbonne, nomAbonne, prenomAbonne, cinAbonne, cneAbonne,
                        usernameAbonne, passwordAbonne,cnssAbonne;
                //Document variables;
                String titreDocument, isbnDocument, editonDocument,editeurDocument,
                        auteurDocument,nbPagesDocument,nbTomesDocument;
                //Emprunt Variables
                String code_abonne;
                // DAO
                Abonne ab; Kindle kd; Document dc; Emprunt ep;
                boolean response;
                switch (received) {
                    case "1" :
                        print("Gestion des Kindles");
                        print(
                                "\nVeuillez choisir une option\n"+
                                        "1- Ajouter Kindle\n"+
                                        "2- Supprimer Kindle\n"+
                                        "3- Aficher les Kindles\n"+
                                        "4- Aficher Kindles par code\n"+
                                        "5- Quitter"
                        );
                        choice = sc.nextLine();
                        switch (choice){

                            case "1":
                                print("Ajout d'un Kindle....");
                                print("Entrez le code de la kindle");
                                 code_kindle = sc.nextLine();
                                print("Entrez le code mac");
                                kindle_mac = sc.nextLine();
                                 response = kindleDAO.ajouterKindle(new Kindle(code_kindle, kindle_mac));
                                print(response?"Kindle Ajoute" : "Kindle Non Ajoute");

                                break;
                            case "2":
                                print("Suppression d'un Kindle ...");
                                print("Entrez le code de la kindle");
                                 code_kindle = sc.nextLine();
                                 response = kindleDAO.supprimerKindle(code_kindle);
                                print(response?"Kindle Supprime" : "Kindle Non Supprime");

                                break;
                            case "3":
                                print("Affichages des kindles ....");
                                response =kindleDAO.getAllKindles().size()>0;
                                print(response ? kindleDAO.getAllKindles().toString() : "Aucun kindle trouve");
                                break;
                            case "4":
                                print("Entrez le code de la kindle");
                                code_kindle= sc.nextLine();
                                response=kindleDAO.getKindleByCode(code_kindle)!=null;
                                print(response ? kindleDAO.getKindleByCode(code_kindle).toString() : "Kindle non trouve");
                                break;
                            case "5":
                                break;
                            default:
                                print("Invalide Option");
                                break;
                        }
                        break;

                    case "2" :
                        print("Gestion des Documents");
                        print(
                                "\nVeuillez choisir une option\n"+
                                        "1- Ajouter un livre\n"+
                                        "2- Ajouter un roman\n"+
                                        "3- Supprimer un document\n"+
                                        "4- Quitter"
                        );
                        choice = sc.nextLine();
                        switch (choice){
                            case "1":
                                print("Ajout d'un livre</>");
                                print("Entrez le titre");
                                titreDocument=sc.nextLine();
                                print("Entrez l'isbn");
                                isbnDocument=sc.nextLine();
                                print("Entrez l'edition");
                                editonDocument=sc.nextLine();
                                print("Entrez l'editeur");
                                editeurDocument=sc.nextLine();
                                print("Entrez l'auteur");
                                auteurDocument=sc.nextLine();
                                print("Entrez le nombre de pages");
                                nbPagesDocument= sc.nextLine();
                                response = documentDAO.ajouterDocument(new Livre(titreDocument, isbnDocument , editonDocument , editeurDocument ,auteurDocument ,  Integer.parseInt(nbPagesDocument)));
                                print(response?"Livre Ajoute" : "Livre Non Ajoute");
                                break;
                            case "2":
                                print("Ajout d'un roman</>");
                                print("Entrez le titre");
                                titreDocument=sc.nextLine();
                                print("Entrez l'isbn");
                                isbnDocument=sc.nextLine();
                                print("Entrez l'edition");
                                editonDocument=sc.nextLine();
                                print("Entrez l'editeur");
                                editeurDocument=sc.nextLine();
                                print("Entrez l'auteur");
                                auteurDocument=sc.nextLine();
                                print("Entrez le nombre de tomes");
                                nbTomesDocument= sc.nextLine();
                                response = documentDAO.ajouterDocument(new Roman(titreDocument, isbnDocument , editonDocument , editeurDocument ,auteurDocument ,  Integer.parseInt(nbTomesDocument)));
                                print(response?"Roman Ajoute" : "Roman Non Ajoute");
                                break;
                            case "3":
                                print("Supression d'un document</>");
                                print("Entrez l'isbn du document");
                                isbnDocument = sc.nextLine();
                                response = documentDAO.supprimerDocument(documentDAO.getDocumentByIsbn(isbnDocument));
                                print(response?"Document Supprime" : "Document Non Supprime");
                                break;
                            case "4":
                                break;
                            default:
                                print("Invalide Option");
                                break;
                        }
                        break;
                    case "3" :
                       print("Gestion des Adherents");
                        print(
                                "\nVeuillez choisir une option\n"+
                                        "1- Ajouter un adherent\n"+
                                        "2- Supprimer un adherent\n"+
                                        "3- Afficher un adherent par cin\n"+
                                        "4- Afficher tous les adherents\n"+
                                        "5- Quitter"
                        );
                        choice = sc.nextLine();
                        switch (choice){

                            case "1":
                                print("Ajout d'un adherent</>");
                                print("Entrez le nom");
                                nomAbonne= sc.nextLine();
                                print("Entrez le prenom");
                                prenomAbonne= sc.nextLine();
                                print("Entrez le username");
                                usernameAbonne= sc.nextLine();
                                print("Entrez le password");
                                passwordAbonne= sc.nextLine();
                                print("Entrez le cin");
                                cinAbonne= sc.nextLine();
                                print("Entrez le type de l'adherent\n" +
                                        "1: Professeur - 2: Etudiant");
                                typeAbonne = sc.nextLine();
                                if(typeAbonne.equals("1")){
                                    print("Entrez le cnss");
                                    cnssAbonne= sc.nextLine();
                                    response  = adherentDAO.AjouterProfesseur(
                                            new Professeur(usernameAbonne,passwordAbonne,nomAbonne,prenomAbonne,cinAbonne,cnssAbonne)
                                    );
                                    print(response?"Professeur Ajoute" : "Professeur Non Ajoute");
                                }else if(typeAbonne.equals("2")){
                                    print("Entrez le cne");
                                    cneAbonne= sc.nextLine();
                                    response=adherentDAO.AjouterEtudiant(
                                            new Etudiant(usernameAbonne,passwordAbonne,nomAbonne,prenomAbonne,cinAbonne,cneAbonne)
                                    );
                                    print(response?"Etudiant Ajoute" : "Etudiant Non Ajoute");
                                }else{
                                    print("TYPE NON CORRECT=> RETOUR AU MENU");
                                    break;
                                }


                                break;
                            case "2":
                                print("Supression d'un adherent</>");
                                print("Entrez le type de l'adherent\n" +
                                        "1: Professeur - 2: Etudiant");
                                typeAbonne = sc.nextLine();
                                if(typeAbonne.equals("1")){
                                    print("Entrez le cin");
                                    cinAbonne= sc.nextLine();
                                    response =adherentDAO.SupprimerProfesseur(cinAbonne);
                                    print(response?"Professeur Supprime" : "Professeur Non Supprime");
                                }else if(typeAbonne.equals("2")){
                                    print("Entrez le cne");
                                    cneAbonne= sc.nextLine();
                                    response = adherentDAO.SupprimerEtudiant(cneAbonne);
                                    print(response?"Etudiant Supprime" : "Etudiant Non Supprime");
                                }else{
                                    print("TYPE NON CORRECT=> RETOUR AU MENU");
                                    break;
                                }

                                break;
                            case "3":
                                print("Entrez le cin");
                                cinAbonne = sc.nextLine();
                                response = adherentDAO.getAbonneByCIN(cinAbonne) !=null;
                                print(response ? adherentDAO.getAbonneByCIN(cinAbonne).toString() : "Adherent non trouve");
                                break;
                            case "4":
                                print("Affichage de tous les abonnes");
                                response = adherentDAO.getAllAbonnes().size()>0;
                                print(response ? adherentDAO.getAllAbonnes().toString() : "Aucun adherent trouve");
                                break;
                            case "5":
                                break;
                            default:
                                print("Invalide Option");
                                break;
                        }
                        break;
                    case "4":
                        print("Gestion des Emprunts");
                        print(
                                "\nVeuillez choisir une option\n"+
                                        "1- Ajouter un emprunt\n"+
                                        "2- Supprimer un emprunt\n"+
                                        "3- Afficher les emprunts\n"+
                                        "4- Afficher emprunt par cin de l'abonne \n"+
                                        "5- Afficher emprunt par code de la kindle \n"+
                                        "6- Quitter"
                        );
                        choice = sc.nextLine();
                        switch (choice){
                            case "1":
                                print("Ajout d'un emprunt");
                                print("Entrez le cin de l'abonne");
                                code_abonne= sc.nextLine();
                                print("Entrez le code de la kindle");
                                code_kindle = sc.nextLine();
                                response =empruntDAO.ajouterEmprunt(new Emprunt(code_abonne, code_kindle));
                                print(response?"Emprunt Ajoute" : "Emprunt Non Ajoute");
                                break;
                            case "2":
                                print("Suppression d'un emprunt");
                                print("Entrez le cin de l'abonne");
                                code_abonne= sc.nextLine();
                                print("Entrez le code de la kindle");
                                code_kindle = sc.nextLine();
                                response = empruntDAO.supprimerEmprunt(new Emprunt(code_abonne, code_kindle));
                                print(response?"Emprunt Supprime" : "Emprunt Non Supprime");
                                break;
                            case "3":
                                print("Affichage des emprunts");
                                response=empruntDAO.getAllEmprunts().size()>0;
                                print(response ? empruntDAO.getAllEmprunts().toString() : "Aucun emprunt trouve");
                                break;
                            case "4":
                                print("Entrez le cin de l'abonne");
                                cinAbonne= sc.nextLine();
                                response=adherentDAO.getAbonneByCIN(cinAbonne)!=null;
                                if(response){
                                    response=empruntDAO.getEmpruntByAbonne(adherentDAO.getAbonneByCIN(cinAbonne))!=null;
                                    print(response?empruntDAO.getEmpruntByAbonne(adherentDAO.getAbonneByCIN(cinAbonne)).toString():"Aucun Emprunt trouve");
                                    break;
                                }
                                print("Adherent non touve");
                                break;
                            case "5":
                                print("Entrez le code de la kindle");
                                code_kindle= sc.nextLine();
                                response=kindleDAO.getKindleByCode(code_kindle)!=null;
                                if(response){
                                    response=empruntDAO.getEmpruntByKindle(kindleDAO.getKindleByCode(code_kindle))!=null;
                                    print(response?empruntDAO.getEmpruntByKindle(kindleDAO.getKindleByCode(code_kindle)).toString():"Aucun Emprunt trouve");
                                    break;
                                }
                                print("Kindle non touve");
                                break;

                            case "6":
                                break;
                            default:
                                print("Invalide Option");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }

        }
        
        
        
        
        
    }




}
