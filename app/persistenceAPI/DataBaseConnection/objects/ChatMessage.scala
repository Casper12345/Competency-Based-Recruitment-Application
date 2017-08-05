package persistenceAPI.DataBaseConnection.objects

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
                        messageRead: Boolean
                      )
