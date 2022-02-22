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
import model.Kindle;
import model.Livre;
import model.Roman;

/**
 *
 * @author rachad
 */
public class KindleUtile {
    
    private Connection con;
    
    public KindleUtile() throws SQLException{
            
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque ","root","");
    }
    
    
    public Kindle getKindleByMac(String mac) throws SQLException {
        String table="kindle";
        Kindle k= null;


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from"+ table+" where mac like '"+mac+"' ");


        while (rs.next()) {
            k = new Kindle(rs.getString("code_kindle") , rs.getString("mac"));

        }
        return k;
    }
    
    public Kindle getKindleByCode(String code) throws SQLException {
        Kindle k= null;


        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from kindle where code_kindle like '"+code+"' ");


        while (rs.next()) {
            k = new Kindle(rs.getString("code_kindle") , rs.getString("mac"));

        }
        return k;
    }
    
    public LinkedList<Kindle> getAllKindles() throws SQLException {
        LinkedList<Kindle> kindles = new LinkedList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from kindle");

        while(rs.next()){
            kindles.add(new Kindle(rs.getString("code_kindle") ,rs.getString("mac")));
        }



         return kindles;
    }
    
    public boolean ajouterKindle(Kindle k) throws SQLException {
        String query = "INSERT INTO adherent (code_kindle, mac) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, k.getCode_kindle());
        ps.setString(2, k.getMac());
        int rs = ps.executeUpdate(query);
        return rs > 0;
    }
   
    public boolean supprimerKindle(Kindle k) throws SQLException {
        Statement stmt = con.createStatement();
        String query="DELETE FROM kindle where code_kindle like '"+k.getCode_kindle()+"'";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated>0;
    }
    
}
