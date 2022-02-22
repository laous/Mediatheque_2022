package dao;

import model.Abonne;
import model.Etudiant;
import model.Professeur;

import java.sql.*;

/**
 * @author Jonas
 */
public class AuthenticationUtile {

    private final Connection con;

    public AuthenticationUtile() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "");
    }

    public Abonne authentication(String username, String password ) throws SQLException {
        String query = "SELECT * FROM adherent WHERE username = '"+ username+"' and password='"+password+"'";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            if (rs.getString("type").equals("professeur")) {  // Type professeur
                return new Professeur(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cnss"));
            }else  if (rs.getString("type").equals("etudiant")){
                return new Etudiant(rs.getString("username"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cin"), rs.getString("cne"));
            }
    }
        return null;
    }
}
