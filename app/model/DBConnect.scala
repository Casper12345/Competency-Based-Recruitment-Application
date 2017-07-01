package model

import java.sql.{Connection, DriverManager, SQLException}

/**
  * Created by Casper on 30/06/2017.
  */
object DBConnect {


    def DBConnector(userName: String, passWord: String) {

      val driver = "com.mysql.jdbc.Driver"
      val url = "jdbc:mysql://localhost:3306/CandidateDataBase"
      val username = "masterUser"
      val password = "password"

      // there's probably a better way to do this

      var connection: Connection = null

      try {
        // make the connection
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)

        // create the statement, and run the select query
        //val statement = connection.createStatement()

        //val resultSet = statement.executeQuery("SELECT * FROM test")

        val stmt = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?)")

        //stmt.setString(1, "1")

        //stmt.setString(2, word)

        //stmt.setString(3, word)


        stmt.executeUpdate


      } catch {

        case e: SQLException => e.printStackTrace()

      }
      connection.close()
    }

}
