/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.LinkedList;
import model.Kindel;
import model.Livre;
import model.Roman;

/**
 *
 * @author rachad
 */
public class KindelUtile {
    
    private Connection con;
    
    public KindelUtile() throws SQLException{
            
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque ","root","2020-2021");
    }
    
    
    public Kindel getKindelByMac(String mac) throws SQLException {
        String table="kindel";
        Kindel k= null;


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from"+ table+" where mac like '"+mac+"' ");


        while (rs.next()) {
            k = new Kindel(rs.getString("code_kindel") , rs.getString("mac"));

        }
        return k;
    }
    
    public Kindel getKindelByCode(String code) throws SQLException {

        String table="kindel";
        Kindel k= null;


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from"+ table+" where code like '"+code+"' ");


        while (rs.next()) {
            k = new Kindel(rs.getString("code_kindel") , rs.getString("mac"));

        }
        return k;
    }
    
    public LinkedList<Kindel> getAllKindels(){


         return null;
    }
    
    public boolean ajouterKindel(Kindel k){
         return true;
    }
   
    public boolean supprimerKindel(Kindel k){
         return true;
    }
    
}
