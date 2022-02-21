package serveur;


import dao.AdherentUtile;
import dao.DocumentUtile;
import dao.EmpruntUtile;
import dao.KindleUtile;
import java.io.IOException;
import java.sql.SQLException;
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
     static AdherentUtile adherentDAO;
     static KindleUtile kindleDAO;
     static EmpruntUtile empruntDAO;
    
    
    public Mediatheque() throws SQLException{
        
          documentDAO= new DocumentUtile() ;
          adherentDAO= new AdherentUtile();
          kindleDAO= new KindleUtile();
          empruntDAO= new EmpruntUtile();
    }
    
    
    //CRUD Kindles
    
//    public static boolean ajouterKindle(Kindle k) throws SQLException {
//        return kindleDAO.ajouterKindle(k);
//    }
//
//    public static boolean supprimerKindle(Kindle k) throws SQLException {
//           return kindleDAO.supprimerKindle(k);
//    }
//
//    public LinkedList<Kindle> getAllKindles() throws  SQLException{
//        return kindleDAO.getAllKindles();
//    }
//
//    public boolean emprunter(Kindle k, Abonne a) throws SQLException {
//
//        if (!k.isEmprunte()){
//            Emprunt e= new Emprunt(a.getCode_abonne(),k.getCode_kindle());
//            k.setEmprunte(true);
//            boolean emprunte=empruntDAO.ajouterEmprunt(e);// la methode ajouterEmprunt() doit verifier si l'adherent n'a pas deja un autre emprunt en cours
//            return emprunte;
//        }
//
//        return false;
//
//    }
//
//    public boolean rendre(Kindle k, Abonne a) throws SQLException {
//        if (k.isEmprunte() && empruntExiste(a,k)){
//            k.setEmprunte(false);
//            return true;
//        }
//        else
//            return false;
//    }
//
//    public  boolean empruntExiste(Abonne a , Kindle k) throws SQLException {
//    // return true s'il y a un emprunt recent realise par l'abonne a avec le kindle k
//    return empruntDAO.getEmprunt(a,k) != null;
//    }
//
//    //CRUD Adherents
//
//    public  Abonne getAdherent(Abonne ab) throws SQLException {
//        if(ab instanceof Etudiant){
//            return adherentDAO.getEtudiantByCne(((Etudiant) ab).getCne());
//        }else if(ab instanceof Professeur){
//            return adherentDAO.getProfesseurByCin(((Professeur) ab).getCin());
//        }
//        return null;
//    }
//
//    public  LinkedList<Emprunt> getEmpruntByAdherent(Abonne ab){return null;}
//
//    public boolean ajouterAdherent(Abonne ab) throws SQLException {
//        if(ab instanceof Professeur){
//            return adherentDAO.AjouterProfesseur((Professeur) ab);
//        }else if(ab instanceof Etudiant){
//            return adherentDAO.AjouterEtudiant((Etudiant) ab);
//        }
//        return false;
//    }
//
//    public boolean supprimerAdherent(Abonne ab) throws SQLException{
//        if(ab instanceof Professeur){
//            return adherentDAO.SupprimerProfesseur( ((Professeur) ab).getCin());
//        }else if(ab instanceof Etudiant){
//            return adherentDAO.SupprimerEtudiant( ((Etudiant) ab).getCne());
//        }
//        return false;
//    }
//
//    //CRUD Document
//
//    public LinkedList<Document> getAllDocuments() throws SQLException {
//        return documentDAO.getAllDocuments();
//    }
//
//    public boolean ajouterDocument(Document d) throws SQLException {
//
//        return documentDAO.ajouterDocument(d);
//    }
//
//    public boolean supprimerDocument(Document d) throws SQLException {return documentDAO.supprimerDocument(d);}
    public static void print(String str){
        System.out.println(str);
    }





    
    
    public static void main(String args[]) throws IOException, SQLException {
        String username="oussama";
        String password="123";
        String received;

        
        //Authentification  ==> BD
        Scanner sc=new Scanner(System.in);
        System.out.println("Entrez votre username:");
        String user = sc.nextLine();
        System.out.println("Entrez votre password:");
        String pw = sc.nextLine();

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
        //***5- Se Deconnecter

        while (true){

                //Menu Admin
                System.out.println(
                        "\n\nVeuillez choisir une option\n"+
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
                boolean response;
                switch (received) {
                    case "1" :
                        print("Gestion des Kindles");
                        print(
                                "\n\nVeuillez choisir une option\n"+
                                        "1- Ajouter Kindle\n"+
                                        "2- Supprimer Kindle\n"+
                                        "3- Quitter"
                        );
                        choice = sc.nextLine();
                        switch (choice){

                            case "1":
                                print("Ajout d'un Kindle</>");
                                print("Entrez le code de la kindle");
                                 code_kindle = sc.nextLine();
                                print("Entrez le code mac");
                                kindle_mac = sc.nextLine();
                                 response = kindleDAO.ajouterKindle(new Kindle(code_kindle, kindle_mac));
                                print(response?"Ajoute" : "Non Ajoute");

                                break;
                            case "2":
                                print("Suppression d'un Kindle</>");
                                print("Entrez le code de la kindle");
                                 code_kindle = sc.nextLine();
                                 response = kindleDAO.supprimerKindle(kindleDAO.getKindleByCode(code_kindle));
                                print(response?"Supprime" : "Non Supprime");

                                break;
                            case "3":
                                break;
                            default:
                                print("Invalide Option");
                                break;
                        }
                        break;

                    case "2" :
                        print("Gestion des Documents");
                        print(
                                "\n\nVeuillez choisir une option\n"+
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
                                documentDAO.ajouterDocument(new Livre(titreDocument, isbnDocument , editonDocument , editeurDocument ,auteurDocument ,  Integer.parseInt(nbPagesDocument)));

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
                                documentDAO.ajouterDocument(new Roman(titreDocument, isbnDocument , editonDocument , editeurDocument ,auteurDocument ,  Integer.parseInt(nbTomesDocument)));

                                break;
                            case "3":
                                print("Supression d'un document</>");
                                print("Entrez l'isbn du document");
                                isbnDocument = sc.nextLine();
                                documentDAO.supprimerDocument(documentDAO.getDocumentByIsbn(isbnDocument));
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
                                "\n\nVeuillez choisir une option\n"+
                                        "1- Ajouter un adherent\n"+
                                        "2- Supprimer un adherent\n"+
                                        "3- Quitter"
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
                                    adherentDAO.AjouterProfesseur(
                                            new Professeur(usernameAbonne,passwordAbonne,nomAbonne,prenomAbonne,cinAbonne,cnssAbonne)
                                    );
                                }else if(typeAbonne.equals("2")){
                                    print("Entrez le cne");
                                    cneAbonne= sc.nextLine();
                                    adherentDAO.AjouterEtudiant(
                                            new Etudiant(usernameAbonne,passwordAbonne,nomAbonne,prenomAbonne,cinAbonne,cneAbonne)
                                    );
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
                                    adherentDAO.SupprimerProfesseur(cinAbonne);
                                }else if(typeAbonne.equals("2")){
                                    print("Entrez le cne");
                                    cneAbonne= sc.nextLine();
                                    adherentDAO.SupprimerEtudiant(cneAbonne);
                                }else{
                                    print("TYPE NON CORRECT=> RETOUR AU MENU");
                                    break;
                                }

                                break;
                            case "3":
                                break;
                            default:
                                print("Invalide Option");
                                break;
                        }
                        break;
                    case "4":
                        print("Gestion des Emprunts");
                        print(
                                "\n\nVeuillez choisir une option\n"+
                                        "1- Ajouter un emprunt\n"+
                                        "2- Supprimer un emprunt\n"+
                                        "3- Quitter"
                        );
                        choice = sc.nextLine();
                        switch (choice){
                            case "1":
                                print("Ajout d'un emprunt");
                                print("Entrez le cin de l'abonne");
                                code_abonne= sc.nextLine();
                                print("Entrez le code de la kindle");
                                code_kindle = sc.nextLine();
                                empruntDAO.ajouterEmprunt(new Emprunt(code_abonne, code_kindle));
                                break;
                            case "2":
                                print("Suppression d'un emprunt");
                                print("Entrez le cin de l'abonne");
                                code_abonne= sc.nextLine();
                                print("Entrez le code de la kindle");
                                code_kindle = sc.nextLine();
                                empruntDAO.supprimerEmprunt(new Emprunt(code_abonne, code_kindle));
                                break;
                            case "3":
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
