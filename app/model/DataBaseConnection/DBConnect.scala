package model.DataBaseConnection

import java.sql.{Connection, DriverManager, SQLException}

/**
  * DataBase methods for Users
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


    def getLatestUserId: Int = {

      val selectSQL = "SELECT MAX(UserID) FROM Users"

      val preparedStatement = connection.prepareStatement(selectSQL)

      val rs = preparedStatement.executeQuery()

      var max = 0


      while(rs.next()){
        max = rs.getInt(1)

      }
      max
    }


    def insertNewUser(userName: String, passWord: String): Boolean ={

      //UserID INT, UserName TEXT, PassWord TEXT


      if(containsUser(userName)){

        false

      } else {

        val maxID = getLatestUserId

        val stmt = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?)")

        stmt.setString(1, (maxID + 1).toString)

        stmt.setString(2, userName)

        stmt.setString(3, passWord)

        stmt.executeUpdate

        true
      }

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
