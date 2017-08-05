package persistenceAPI.DataBaseConnection.objects


/**
  * Case class for representing SentChatMessage.
  */
case class ReceivedChatMessage(
                                senderName: String,
                                chatMessageID: Int,
                                subject: String,
                                messageBody: String,
                                messageRead: Boolean
                              )
