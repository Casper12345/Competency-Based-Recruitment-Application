package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * Trait for Chat message persistence facade
  */
trait ChatMessagePersistenceFacadeTrait {

  /**
    * Method for getting chat message by id.
    *
    * @param chatMessageID chat message id
    * @return option of ChatMessage
    */
  def getChatMessageByID(chatMessageID: Int): Option[ChatMessage]

  /**
    * Method for creating a new sent chat message.
    *
    * @param senderUserID   sender id
    * @param receiverUserID reciever id
    * @param subject        subject
    * @param messageBody    body
    */
  def createSentNewChatMessage(senderUserID: Int, receiverUserID: Int, subject: String,
                               messageBody: String): Unit

  /**
    * Method for setting message boolean to true.
    *
    * @param chatMessageID
    */
  def setReadToTrueByID(chatMessageID: Int): Unit


}
