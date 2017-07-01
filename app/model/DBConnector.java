package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Casper on 29/06/2017.
 */
public class DBConnector {

  private final String URL = "jdbc:mysql://localhost:3306/CandidateDataBase";
  private final String USERNAME = "masterUser";
  private final String PASSWORD = "password";


  public void connect(String id, String name) throws SQLException {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    Connection conn = null;

    try {
      conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }


    //assert conn != null;
    PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users VALUES (?,?)");

    stmt.setString(1, id);
    stmt.setString(2, name);
    stmt.executeUpdate();
    stmt.close();
    conn.close();

  }


}
