package persistenceAPI.DataBaseConnection.connectUser

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.objects.{ChatMessage, ReceivedChatMessage}

/**
  * DataBase methods for UserRecievedMessage table and handling request to UserRecievedMessage table
  *
  * SQL - UserRecievedMessage(UserID INT, ChatMessageID INT)
  */
object DBUserRecievedMessage {

  /**
    * Connection to main DB
    */
  val db = DBMain

  def addUserRecievedMessage(userID: Int, chatMessageID: Int): Unit = {

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO UserRecievedMessage VALUES (?,?)")

    stmt.setString(1, userID.toString)

    stmt.setString(2, chatMessageID.toString)

    stmt.executeUpdate

    db.closeConnection()


  }


  def getUserNameAndIDBYChatMessageIDSentMessage(chatMessageID: Int): (Int, String) = {
    db.connect()

    val selectSQL =
      """SELECT Users.UserName, Users.UserID
        |FROM Users JOIN UserSentMessage USING (UserID)
        |WHERE UserSentMessage.ChatMessageID = ?
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
  def getAllUserReceivedMessageByID(userID: Int): List[ChatMessage] = {

    db.connect()

    val selectSQL =
      """SELECT
        |  UserRecievedMessage.UserID,
        |  Users.UserName,
        |  ChatMessage.ChatMessageID,
        |  ChatMessage.Subject,
        |  ChatMessage.MessageBody,
        |  ChatMessage.MessageRead
        |FROM UserRecievedMessage
        |  JOIN ChatMessage USING (ChatMessageID)
        |  JOIN Users USING (UserID)
        |WHERE UserRecievedMessage.UserID = ?
      """.stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, userID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[ChatMessage] = Nil

    while (rs.next()) {
      val userID = rs.getString("UserRecievedMessage.UserID")
      val userName = rs.getString("Users.UserName")
      val chatMessageID = rs.getString("ChatMessage.ChatMessageID")
      val subject = rs.getString("ChatMessage.Subject")
      val messageBody = rs.getString("ChatMessage.MessageBody")
      val messageReadAsString = rs.getString("ChatMessage.MessageRead")

      var messageReadAsBoolean = false

      if (messageReadAsString.toInt == 1) {
        messageReadAsBoolean = true
      }

      val (senderUserID, senderUserName) =
        getUserNameAndIDBYChatMessageIDSentMessage(chatMessageID.toInt)

      toReturn = toReturn :+ ChatMessage(senderUserID, senderUserName, userID.toInt,
        userName, chatMessageID.toInt, subject,
        messageBody, messageReadAsBoolean)
    }


    db.closeConnection()

    toReturn

  }


}
