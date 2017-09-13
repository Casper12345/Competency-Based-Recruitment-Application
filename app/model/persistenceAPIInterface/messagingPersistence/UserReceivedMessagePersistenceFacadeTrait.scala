package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * Trait for User Recieved Message Persistence Facade.
  */
trait UserReceivedMessagePersistenceFacadeTrait {

  /**
    * Method for getting all recieved messages by ID
    *
    * @param userID user ID
    * @return list of ChatMessage
    */
  def getAllUserReceivedMessageByID(userID: Int): List[ChatMessage]

  /**
    * Method for counting unread messages
    *
    * @param userID user id
    * @return int
    */
  def countUnreadMessagesByUserID(userID: Int): Int


}
