package persistenceAPI.DataBaseConnection.connectChatMessage

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.connectUser.{DBUserRecievedMessage, DBUserSentMessage}
import persistenceAPI.DataBaseConnection.objects.{Candidate, ChatMessage}

/**
  * DataBase methods for ChatMessage table and handling request to ChatMessage table.
  */
object DBChatMessage {

  /**
    * Connecting to main DB
    */
  var db = DBMain


  /**
    * Method for inserting into ChatMessage
    *
    * SQL - ChatMessage(ChatMessageID INT, Subject TEXT,
    * MessageBody TEXT, MessageRead BOOLEAN);
    *
    *
    */
  def addChatMessage(subject: String,
                     messageBody: String, messageRead: Boolean): Unit = {

    var messageReadAsInt = 0

    // transform boolean to tinyInt
    if (messageRead) {
      messageReadAsInt = 1
    }


    db.connect()

    val maxID = db.getLatestId("ChatMessage")

    val stmt = db.connection.prepareStatement("INSERT INTO ChatMessage VALUES (?,?,?,?)")


    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, subject)

    stmt.setString(3, messageBody.toString)

    stmt.setString(4, messageReadAsInt.toString)


    stmt.executeUpdate

    db.closeConnection()


  }


  /**
    * Main method for getting ChatMessages by ID
    *
    * @param chatMessageID
    * @return
    */
  def getChatMessageByID(chatMessageID: Int): Option[ChatMessage] = {

    db.connect()

    val selectSQL =
      """SELECT *
        |FROM ChatMessage
        |WHERE ChatMessageID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, chatMessageID)

    val rs = preparedStatement.executeQuery()

    var toReturn: Option[ChatMessage] = None

    while (rs.next()) {

      val chatMessageID = rs.getString("ChatMessageID")
      val subject = rs.getString("Subject")

      val messageBody = rs.getString("MessageBody")
      val messageReadAsString = rs.getString("MessageRead")

      var messageReadAsBooolean = false

      // transform tinyInt to boolean
      if (messageReadAsString.toInt == 1) {
        messageReadAsBooolean = true
      }

      val dbUserReceivedMessage = DBUserRecievedMessage

      val (sentMessageUserID, sentMessageUserName) =
        dbUserReceivedMessage.getUserNameAndIDBYChatMessageIDSentMessage(chatMessageID.toInt)

      val dbUserSentMessage = DBUserSentMessage

      val (recievedMessageUserID, receivedMessageUserName) =

        dbUserSentMessage.getUserNameAndIDBYChatMessageIDRecievedMessage(chatMessageID.toInt)


      toReturn = Some(ChatMessage(sentMessageUserID.toInt, sentMessageUserName,
        recievedMessageUserID.toInt, receivedMessageUserName,
        chatMessageID.toInt, subject,
        messageBody, messageReadAsBooolean))
    }


    db.closeConnection()

    toReturn
  }


  def createSentNewChatMessage(senderUserID: Int, receiverUserID: Int, subject: String,
                               messageBody: String): Unit = {


    addChatMessage(subject, messageBody, messageRead = false)

    db.connect()

    val latestChatMessageID = db.getLatestId("ChatMessage")

    db.closeConnection()

    val userSentMessageDB = DBUserSentMessage
    val userReceivedMessageDB = DBUserRecievedMessage

    userSentMessageDB.addUserSentMessage(senderUserID, latestChatMessageID)

    userReceivedMessageDB.addUserRecievedMessage(receiverUserID, latestChatMessageID)


  }

  def setReadToTrueByID(chatMessageID: Int): Unit = {

    db.connect()

    val updateSQL =
      """UPDATE ChatMessage
        |SET ChatMessage.MessageRead = 1
        |WHERE ChatMessage.ChatMessageID = ?""".stripMargin


    val stmt = db.connection.prepareStatement(updateSQL)

    stmt.setInt(1, chatMessageID)

    stmt.executeUpdate

    db.closeConnection()

  }


}
