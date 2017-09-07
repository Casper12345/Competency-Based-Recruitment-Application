package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.connectChatMessage.DBChatMessage
import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * Implements ChatMessagePersistenceFacade
  */
object ChatMessagePersistenceFacade extends ChatMessagePersistenceFacadeTrait {

  /**
    * Composition DBChatMessage
    */
  val chatMessage = DBChatMessage

  /**
    * Method for getting chat message by id.
    *
    * @param chatMessageID chat message id
    * @return option of ChatMessage
    */
  override def getChatMessageByID(chatMessageID: Int): Option[ChatMessage] =
    chatMessage.getChatMessageByID(chatMessageID)

  /**
    * Method for creating a new sent chat message.
    *
    * @param senderUserID   sender id
    * @param receiverUserID reciever id
    * @param subject        subject
    * @param messageBody    body
    */
  override def createSentNewChatMessage(senderUserID: Int,
                                        receiverUserID: Int,
                                        subject: String, messageBody: String): Unit = {
    chatMessage.createSentNewChatMessage(senderUserID, receiverUserID, subject, messageBody)

  }

  /**
    * Method for setting message boolean to true.
    *
    * @param chatMessageID
    */
  override def setReadToTrueByID(chatMessageID: Int): Unit =
    chatMessage.setReadToTrueByID(chatMessageID)
}
