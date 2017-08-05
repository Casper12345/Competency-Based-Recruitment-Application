package persistenceAPI.DataBaseConnection.objects

/**
  * Created by Casper on 05/08/2017.
  */
/**
  * Case class for representing SentChatMessage.
  */
case class SentChatMessage(
                            senderID: Int,
                            senderName: String,
                            chatMessageID: Int,
                            subject: String,
                            messageBody: String,
                            messageRead: Boolean
                          )
