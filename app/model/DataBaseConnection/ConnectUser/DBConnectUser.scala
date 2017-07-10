package model.DataBaseConnection.ConnectUser

import java.sql.{Connection, DriverManager, SQLException}

/**
  * DataBase methods for Users
  */

object DBConnectUser extends DBConnectUserTrait {

  val driver = "com.mysql.cj.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/CandidateDataBase?autoReconnect=true&useSSL=false"
  val username = "masterUser"
  val password = "password"
  var connection: Connection = _

  def connect(): Unit = {

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

    } catch {
      case e: SQLException => e.printStackTrace()
    }

  }


  def containsUser(userName: String): Boolean = {

    //UserID INT, UserName TEXT, PassWord TEXT

    val selectSQL = "SELECT * FROM Users WHERE UserName = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setString(1, userName)

    val rs = preparedStatement.executeQuery()

    if (rs.next()) {
      true
    } else {
      false
    }

  }


  def getLatestUserId: Int = {

    val selectSQL = "SELECT MAX(UserID) FROM Users"

    val preparedStatement = connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()

    var max = 0

    while (rs.next()) {
      max = rs.getInt(1)
    }
    max
  }


  def insertNewUser(userName: String, passWord: String, userPrivilege: String): Boolean = {

    //UserID INT, UserName TEXT, PassWord TEXT UserPrivilege TEXT

    connect()


    if (containsUser(userName)) {

      closeConnection()

      false

    } else {

      val maxID = getLatestUserId

      val stmt = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?)")

      stmt.setString(1, (maxID + 1).toString)

      stmt.setString(2, userName)

      stmt.setString(3, passWord)

      stmt.setString(4, userPrivilege)

      stmt.executeUpdate

      closeConnection()

      true
    }


  }

  def checkUser(userName: String, passWord: String): Boolean = {

    connect()

    //UserID INT, UserName TEXT, PassWord TEXT

    val selectSQL = "SELECT * FROM Users WHERE UserName = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setString(1, userName)

    val rs = preparedStatement.executeQuery()

    var isUser = false

    while (rs.next()) {

      val user = rs.getString("UserName")

      if (user == userName) {
        val pass = rs.getString("PassWord")

        if (pass == passWord) {
          isUser = true
        }
      }
    }


    closeConnection()

    isUser
  }

  def getPrivilege(userID: Int): String = {

    var toReturn: String = ""

    connect()

    val selectSQL = "SELECT UserPrivilege FROM Users WHERE UserID = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setString(1, userID.toString)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {
      toReturn = rs.getString("UserPrivilege")
    }

    closeConnection()

    toReturn

  }

  def closeConnection(): Unit = {
    connection.close()
  }


}
