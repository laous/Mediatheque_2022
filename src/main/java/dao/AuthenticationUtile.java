package dao;

import java.sql.*;

/**
 * @author Jonas
 */
public class AuthenticationUtile {

    private final Connection con;

    public AuthenticationUtile() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque", "root", "");
    }

    public String authentication(String username, String password ) throws SQLException {
        String query = "SELECT * FROM adherent WHERE username = '"+ username+"' and password='"+password+"'";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return  rs.next() ? rs.getString("type") : null;
    }
}
