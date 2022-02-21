package serveur;


import dao.AdherentUtile;
import dao.DocumentUtile;
import dao.EmpruntUtile;
import dao.KindleUtile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
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
     DocumentUtile documentDAO;
     AdherentUtile adherentDAO;
     KindleUtile kindleDAO;
     EmpruntUtile empruntDAO;
    
    
    public Mediatheque() throws SQLException{
        
          documentDAO= new DocumentUtile() ;
          adherentDAO= new AdherentUtile();
          kindleDAO= new KindleUtile();
          empruntDAO= new EmpruntUtile();
    }
    
    
    //CRUD Kindles
    
    private boolean ajouterKindle(Kindle k) throws SQLException {
        return kindleDAO.ajouterKindle(k);
    }

    private boolean supprimerKindle(Kindle k) throws SQLException {
           return kindleDAO.supprimerKindle(k);
    }

    LinkedList<Kindle> getAllKindles() throws  SQLException{
        return kindleDAO.getAllKindles();
    }
    
    boolean emprunter(Kindle k, Abonne a) throws SQLException {
        
        if (!k.isEmprunte()){
            Emprunt e= new Emprunt(a.getCode_abonne(),k.getCode_kindle());
            k.setEmprunte(true);
            boolean emprunte=empruntDAO.ajouterEmprunt(e);// la methode ajouterEmprunt() doit verifier si l'adherent n'a pas deja un autre emprunt en cours
            return emprunte;
        }
      
        return false;
        
    }
    
    boolean rendre(Kindle k, Abonne a) throws SQLException {
        if (k.isEmprunte() && empruntExiste(a,k)){
            k.setEmprunte(false);
            return true;
        }
        else
            return false;
    }

    private boolean empruntExiste(Abonne a , Kindle k) throws SQLException {
    // return true s'il y a un emprunt recent realise par l'abonne a avec le kindle k 
    return empruntDAO.getEmprunt(a,k) != null;
    }

    //CRUD Adherents

    private Abonne getAdherent(Abonne ab) throws SQLException {
        if(ab instanceof Etudiant){
            return adherentDAO.getEtudiantByCne(((Etudiant) ab).getCne());
        }else if(ab instanceof Professeur){
            return adherentDAO.getProfesseurByCin(((Professeur) ab).getCin());
        }
        return null;
    }

    private LinkedList<Emprunt> getEmpruntByAdherent(Abonne ab){return null;}

    boolean ajouterAdherent(Abonne ab) throws SQLException {
        if(ab instanceof Professeur){
            return adherentDAO.AjouterProfesseur((Professeur) ab);
        }else if(ab instanceof Etudiant){
            return adherentDAO.AjouterEtudiant((Etudiant) ab);
        }
        return false;
    }

    boolean supprimerAdherent(Abonne ab) throws SQLException{
        if(ab instanceof Professeur){
            return adherentDAO.SupprimerProfesseur( ((Professeur) ab).getCin());
        }else if(ab instanceof Etudiant){
            return adherentDAO.SupprimerEtudiant( ((Etudiant) ab).getCne());
        }
        return false;
    }

    //CRUD Document

    LinkedList<Document> getAllDocuments() throws SQLException {
        return documentDAO.getAllDocuments();
    }

    boolean ajouterDocument(Document d) throws SQLException {

        return documentDAO.ajouterDocument(d);
    }

    boolean supprimerDocument(Document d) throws SQLException {return documentDAO.supprimerDocument(d);}





    
    
    public static void main(String args[]) throws IOException{
        
        //Authentification  ==> BD

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
        
        
        
        
        
    }
    
    
}
