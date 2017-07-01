package model.DataBaseConnection

import java.sql.{Connection, DriverManager, SQLException}

/**
  * Created by Casper on 30/06/2017.
  */

object DBConnect {

    val driver = "com.mysql.cj.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/CandidateDataBase"
    val username = "masterUser"
    val password = "password"
    var connection: Connection = _


  def connect() {

      try {
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)

      } catch {
        case e: SQLException => e.printStackTrace()
      }

    }


    def containsUser(userName: String): Boolean ={

      //UserID INT, UserName TEXT, PassWord TEXT

      val selectSQL = "SELECT * FROM Users WHERE UserName = ?"

      val preparedStatement = connection.prepareStatement(selectSQL)

      preparedStatement.setString(1, userName)

      val rs = preparedStatement.executeQuery()

      var isUser = false

      while (rs.next()) {

        val user = rs.getString("UserName")

        if(user == userName){
          isUser = true
        }
      }

      isUser

    }


    def insertNewUser(userID: String, userName: String, passWord: String){

      //UserID INT, UserName TEXT, PassWord TEXT

      val stmt = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?)")

      stmt.setString(1, userID)

      stmt.setString(2, userName)

      stmt.setString(3, passWord)

      stmt.executeUpdate

    }


    def checkUser(userName: String, passWord: String): Boolean = {

      //UserID INT, UserName TEXT, PassWord TEXT

      val selectSQL = "SELECT * FROM Users WHERE UserName = ?"

      val preparedStatement = connection.prepareStatement(selectSQL)

      preparedStatement.setString(1, userName)

      val rs = preparedStatement.executeQuery()

      var isUser = false

      while (rs.next()) {

        val user = rs.getString("UserName")

        if(user == userName){
          val pass = rs.getString("PassWord")

          if(pass == passWord){
            isUser = true
          }
        }
      }

      isUser
    }

    def closeConnection(){
      connection.close()
    }


}
