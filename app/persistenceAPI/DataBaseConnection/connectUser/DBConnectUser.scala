package persistenceAPI.DataBaseConnection.connectUser

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.objects.{ChatMessage, HRManagerUser, RecruiterUser}

/**
  * DataBase methods for Users table and handling request to Users table
  */

object DBConnectUser extends DBConnectUserTrait {

  /**
    * DBMain through composition
    */
  var db = DBMain

  /**
    * Checks if DB contains UserName to avoid duplicate users
    *
    * @param userName
    * @return boolean
    */
  def containsUser(userName: String): Boolean = {

    //UserID INT, UserName TEXT, PassWord TEXT

    val selectSQL = "SELECT * FROM Users WHERE UserName = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setString(1, userName)

    val rs = preparedStatement.executeQuery()

    if (rs.next()) {
      true
    } else {
      false
    }

  }

  /**
    * Method for inserting new user into table Users
    *
    * @param userName
    * @param passWord
    * @param userPrivilege
    * @return
    */
  def insertNewUser(userName: String, passWord: String, userPrivilege: String): Boolean = {

    //UserID INT, UserName TEXT, PassWord TEXT UserPrivilege TEXT

    db.connect()

    val encryptedPass = EncryptionForDataBase.encryptPassword(passWord)

    if (containsUser(userName)) {

      db.closeConnection()

      false

    } else {

      val maxID = db.getLatestId("Users")

      val stmt = db.connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?)")

      stmt.setString(1, (maxID + 1).toString)

      stmt.setString(2, userName)

      stmt.setString(3, encryptedPass)

      stmt.setString(4, userPrivilege)

      stmt.executeUpdate

      db.closeConnection()

      true
    }


  }

  /**
    * Method for checking if UserName and Password corresponds on login.
    *
    * @param userName
    * @param passWord
    * @return
    */
  def checkUser(userName: String, passWord: String): Boolean = {

    db.connect()

    //UserID INT, UserName TEXT, PassWord TEXT

    val selectSQL = "SELECT * FROM Users WHERE UserName = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setString(1, userName)

    val rs = preparedStatement.executeQuery()

    var isUser = false

    while (rs.next()) {

      val user = rs.getString("UserName")

      if (user == userName) {
        val encryptedPass = rs.getString("PassWord")

        isUser = DecryptPassword.checkPassword(passWord, encryptedPass)

      }
    }


    db.closeConnection()

    isUser
  }

  /**
    * Metod for getting privilege by userName
    *
    * @param userName
    * @return returns user privilege as String
    */
  def getPrivilegeByName(userName: String): String = {

    var toReturn: String = ""

    db.connect()

    val selectSQL = "SELECT UserPrivilege FROM Users WHERE UserName = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setString(1, userName)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {
      toReturn = rs.getString("UserPrivilege")
    }

    db.closeConnection()

    toReturn

  }

  def getIDByUserName(userName: String): Int = {

    db.connect()

    val selectSQL =
      """SELECT Users.UserID
        |FROM Users
        |WHERE Users.UserName = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setString(1, userName)

    val rs = preparedStatement.executeQuery()

    var toReturn: Int = 0

    while (rs.next()) {

      toReturn = rs.getString("Users.UserID").toInt

    }

    db.closeConnection()

    toReturn

  }

  def getAllRecruiterUsers(): List[RecruiterUser] = {

    db.connect()

    val selectSQL =
      """SELECT Users.UserID, Users.UserName
        |FROM Users
        |WHERE UserPrivilege = 'Recruiter'""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[RecruiterUser] = Nil

    while (rs.next()) {

      val userID = rs.getString("Users.UserID")
      val userName = rs.getString("Users.UserName")

      toReturn = toReturn :+ RecruiterUser(userID.toInt, userName)
    }


    db.closeConnection()

    toReturn

  }

  def getAllHRManagerUsers(): List[HRManagerUser] = {

    db.connect()

    //| UserID | UserName | PassWord | UserPrivilege |

    val selectSQL =
      """SELECT Users.UserID, Users.UserName
        |FROM Users
        |WHERE UserPrivilege = 'HRManager'""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[HRManagerUser] = Nil

    while (rs.next()) {

      val userID = rs.getString("Users.UserID")
      val userName = rs.getString("Users.UserName")

      toReturn = toReturn :+ HRManagerUser(userID.toInt, userName)
    }


    db.closeConnection()

    toReturn

  }


}
