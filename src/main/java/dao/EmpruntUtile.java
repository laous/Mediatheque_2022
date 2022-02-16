/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author rachad
 */
public class EmpruntUtile {
    private Connection con;


    public  EmpruntUtile () throws SQLException {

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque ","root","2020-2021");

    }

    public boolean ajouterEmprunt(Emprunt ep) throws SQLException {
        Statement stmt = con.createStatement();
        String query="";
        int nbUpdated = stmt.executeUpdate(query);

        return  nbUpdated>0;
    }

    public boolean supprimerEmprunt(Emprunt ep){
        return false;
    }
    public Emprunt getEmprunt(Abonne ab , Kindle kd) throws SQLException {
        Emprunt ep=null;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emprunt where code_abonne like "+ ab.getCode_abonne());
        while(rs.next()){
            ep=new Emprunt(rs.getString("code_abonne"), rs.getString("code_kindle"));

        }
        return ep;
    }

    public LinkedList<Emprunt> getEmpruntsByAbonne(Abonne ab){
        return new LinkedList<Emprunt>();
    }
    public LinkedList<Emprunt> getEmpruntsByKindle(Kindle kd){
        return new LinkedList<Emprunt>();
    }
    public LinkedList<Emprunt> getAllEmprunts() throws SQLException {

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from emprunt");

        LinkedList<Emprunt> emprunts= new LinkedList<>();

        while (rs.next()) {
            Emprunt ep= new Emprunt(rs.getString("code_abonne") ,rs.getString("code_kindle"));
            emprunts.add(ep);
        }
        return emprunts;
    }
    
}
