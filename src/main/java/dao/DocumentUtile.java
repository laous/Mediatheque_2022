/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import model.Document;
import model.Etudiant;
import model.Livre;
import model.Professeur;
import model.Roman;

/**
 *
 * @author rachad
 */
public class DocumentUtile <T extends Document> {
    
    private Connection con;

    
    public  DocumentUtile () throws SQLException{
        
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque ","root","");
     
    }



    public LinkedList<T> getDocumentsByTitre(String titre) throws SQLException{

        LinkedList<T> documents= new LinkedList<T>();

        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType)sooper).getActualTypeArguments()[0];
        String table="";

        if(t.getTypeName().equals("Livre"))
            table="livre";
        else
            table="roman";


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from"+ table+" where titre like '"+titre+"' ");


        while (rs.next()) {
            T d= null;
            if (table=="livre"){
                d= (T) new Livre(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbPages") );

            }
            else{
                d=(T)new Roman(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbTomes") );
            }
            documents.add(d);
        }
        return documents;
    }
    
    public T getDocumentByIsbn(String isbn) throws SQLException{
        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType)sooper).getActualTypeArguments()[0];
        String table="";
        
        if(t.getTypeName().equals("Livre"))
            table="livre";
        else
            table="roman";
        
       
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("select * from"+ table+" where isbn like '"+isbn+"' ");
       T d= null;

        while (rs.next()) {
            if (table=="livre"){
                d= (T) new Livre(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbPages") );

            }
            else{
                d=(T)new Roman(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbTomes") );
            }
        }
        return d;
        
    }
    
    public LinkedList<T> getAllDocuments() throws SQLException{
        
        LinkedList<T> documents= new LinkedList<T>();
        
            Type sooper = getClass().getGenericSuperclass();
            Type t = ((ParameterizedType)sooper).getActualTypeArguments()[0];
            String table="";

            if(t.getTypeName().equals("Livre"))
                table="livre";
            else
                table="roman";


           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("select * from"+ table);
          

        while (rs.next()) {
            T d= null;
            if (table=="livre"){
                d= (T) new Livre(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbPages") );

            }
            else{
                d=(T)new Roman(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbTomes") );
            }
            documents.add(d);
        }  
        return documents;
    }
    
    public LinkedList<T> getDocumentsByEditeur(String editeur) throws SQLException {
        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType)sooper).getActualTypeArguments()[0];
        String table="";

        if(t.getTypeName().equals("Livre"))
            table="livre";
        else
            table="roman";


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from"+ table+" where editeur like '"+editeur+"' ");
        T d= null;
        LinkedList<T> documents= new LinkedList<T>();

        while (rs.next()) {
            if (table=="livre"){
                d= (T) new Livre(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbPages") );

            }
            else{
                d=(T)new Roman(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbTomes") );
            }
            documents.add(d);
        }

        return documents;

    }
    
    public LinkedList<T> getDocumentsByEdition(String edition) throws SQLException {
        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType)sooper).getActualTypeArguments()[0];
        String table="";

        if(t.getTypeName().equals("Livre"))
            table="livre";
        else
            table="roman";


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from"+ table+" where edition like '"+edition+"' ");
        T d= null;
        LinkedList<T> documents= new LinkedList<T>();

        while (rs.next()) {
            if (table=="livre"){
                d= (T) new Livre(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbPages") );

            }
            else{
                d=(T)new Roman(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbTomes") );
            }
            documents.add(d);
        }

        return documents;
    }
    
    public LinkedList<T> getDocumentsByAuteur(String auteur) throws SQLException {
        LinkedList<T> documents= new LinkedList<T>();

        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType)sooper).getActualTypeArguments()[0];
        String table="";

        if(t.getTypeName().equals("Livre"))
            table="livre";
        else
            table="roman";


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from"+ table+" where auteur like '"+auteur+"' ");


        while (rs.next()) {
            T d= null;
            if (table=="livre"){
                d= (T) new Livre(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbPages") );

            }
            else{
                d=(T)new Roman(rs.getString("titre") ,rs.getString("isbn"), rs.getString("edition"), rs.getString("editeur"), rs.getString("auteur"), rs.getInt("nbTomes") );
            }
            documents.add(d);
        }
        return documents;
    }

    public boolean ajouterDocument(T d) throws SQLException{
        
    Statement stmt = con.createStatement();
    String query="";
    
    if (d instanceof Livre){
      query="INSERT INTO document (`type`,`titre`, `isbn`, `edition`,`editeur` , `auteur`) VALUES" +
              "('livre','"+ d.getTitre()+"','"+d.getIsbn()+"','"+d.getEdition()+"','"+d.getEditeur()+"','"+d.getAuteur()+"')";
    }
    else  if (d instanceof Roman){
        query="INSERT INTO document (`type`,`titre`, `isbn`, `edition`,`editeur` , `auteur`) VALUES" +
                "('roman','"+ d.getTitre()+"','"+d.getIsbn()+"','"+d.getEdition()+"','"+d.getEditeur()+"','"+d.getAuteur()+"')";
    }
    
    int nbUpdated = stmt.executeUpdate(query);
    return nbUpdated>0;
    
        
    }
   
    public boolean supprimerDocument(T d) throws SQLException {
        Statement stmt = con.createStatement();
        String doc="";

        if (d instanceof Livre){
            doc="livre";
        }
        else  if (d instanceof Roman){
            doc="livre";
        }
        String query="DELETE FROM "+doc+" where isbn like "+d.getIsbn();

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    }
    
}
