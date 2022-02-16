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
    
    private void ajouterKindle(Kindle k){
        kindleDAO.ajouterKindle(k);
    }
    
  
    
     private void supprimerKindle(Kindle k){
            kindleDAO.supprimerKindle(k);
    }
    
     boolean Emprunter(Kindle k, Abonne A) throws SQLException {
        
        if (!k.isEmprunte()){
            Emprunt e= new Emprunt(A.getCode_abonne(),k.getCode_kindle());
            k.setEmprunte(true);
            boolean emprunte=empruntDAO.ajouterEmprunt(e);// la methode ajouterEmprunt() doit verifier si l'adherent n'a pas deja un autre emprunt en cours
            return emprunte;
        }
      
        return false;
        
    }
    
    boolean rendre(Kindle k, Abonne a){
        if (k.isEmprunte() && empruntExiste(k,a)){
            k.setEmprunte(false);
            return true;
        }
        else
            return false;
    }

    private boolean empruntExiste(Kindle k, Abonne a) {
    // return true s'il y a un emprunt recent realise par l'abonne a avec le kindle k 
    return false;
    }
    
    
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
        //***5- suspondre les kindles temporairement  ==>sk.sleep(xx)
        //***6- Reprendre le serveur des kindles  ==> sk.interrupt() // a ne pas afficher si les serveur  est deja demarre
        //***5- Se Deconnecter
        
        
        
        
        
    }
    
    
}
