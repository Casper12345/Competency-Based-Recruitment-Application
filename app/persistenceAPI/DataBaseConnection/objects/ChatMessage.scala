package persistenceAPI.DataBaseConnection.objects

import java.text.SimpleDateFormat

/**
  * Case class for representing ChatMessage.
  */
case class ChatMessage(
                        senderUserID: Int,
                        senderUserName: String,
                        receiverUserID: Int,
                        receiverUserName: String,
                        chatMessageID: Int,
                        subject: String,
                        messageBody: String,
                        messageRead: Boolean,
                        timeSent: java.sql.Timestamp
                      ) {

  def convertTimeStamp: String = {

    new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(timeSent)

  }
}
