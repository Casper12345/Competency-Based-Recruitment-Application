package persistenceAPI.DataBaseConnection.connectUser

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.objects.{ChatMessage, RecruiterUser, SentChatMessage}

/**
  * DataBase methods for UserSentMessage table and handling request to UserSentMessage table
  */
object DBUserSentMessage {

  /**
    * Connection to main DB
    * SQL - UserSentMessage(UserID INT, ChatMessageID INT);
    *
    */
  val db = DBMain

  def addUserSentMessage(userID: Int, chatMessageID: Int): Unit = {

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO UserSentMessage VALUES (?,?)")

    stmt.setString(1, userID.toString)

    stmt.setString(2, chatMessageID.toString)

    stmt.executeUpdate

    db.closeConnection()


  }


  def getUserNameAndIDBYChatMessageIDRecievedMessage(chatMessageID: Int): (Int, String) = {
    db.connect()

    val selectSQL =
      """SELECT Users.UserName, Users.UserID
        |FROM Users JOIN UserRecievedMessage USING (UserID)
        |WHERE UserRecievedMessage.ChatMessageID = ?
      """.stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, chatMessageID)

    val rs = preparedStatement.executeQuery()

    var intToReturn = 0

    var stringToReturn = ""


    while (rs.next()) {

      intToReturn = rs.getString("Users.UserID").toInt
      stringToReturn = rs.getString("Users.UserName")

    }


    db.closeConnection()

    (intToReturn, stringToReturn)

  }


  /**
    *
    * @param userID
    * @return
    */
  def getAllUserSentMessageByID(userID: Int): List[ChatMessage] = {

    db.connect()

    val selectSQL =
      """SELECT
        |  UserSentMessage.UserID,
        |  Users.UserName,
        |  ChatMessage.ChatMessageID,
        |  ChatMessage.Subject,
        |  ChatMessage.MessageBody,
        |  ChatMessage.MessageRead,
        |  ChatMessage.TimeSent
        |FROM UserSentMessage
        |  JOIN ChatMessage USING (ChatMessageID)
        |  JOIN Users USING (UserID)
        |WHERE UserSentMessage.UserID = ?
      """.stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, userID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[ChatMessage] = Nil

    while (rs.next()) {

      val userID = rs.getString("UserSentMessage.UserID")
      val userName = rs.getString("Users.UserName")
      val chatMessageID = rs.getString("ChatMessage.ChatMessageID")
      val subject = rs.getString("ChatMessage.Subject")
      val messageBody = rs.getString("ChatMessage.MessageBody")
      val messageReadAsString = rs.getString("ChatMessage.MessageRead")
      val timeStamp = rs.getTimestamp("ChatMessage.TimeSent")

      val (receiverUserID, recieverUserName) =
        getUserNameAndIDBYChatMessageIDRecievedMessage(chatMessageID.toInt)

      var messageReadAsBoolean = false

      if (messageReadAsString.toInt == 1) {
        messageReadAsBoolean = true
      }


      toReturn = toReturn :+ ChatMessage(userID.toInt,
        userName, receiverUserID, recieverUserName, chatMessageID.toInt, subject,
        messageBody, messageReadAsBoolean, timeStamp)
    }


    db.closeConnection()

    toReturn

  }

}
