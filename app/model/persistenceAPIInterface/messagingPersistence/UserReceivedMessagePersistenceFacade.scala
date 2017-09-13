package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.connectUser.DBUserRecievedMessage
import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * User Recieved Message Persistence Facade.
  */
object UserReceivedMessagePersistenceFacade extends UserReceivedMessagePersistenceFacadeTrait {

  /**
    * Composition DBUserRecievedMessage
    */
  val userReceivedMessage = DBUserRecievedMessage

  /**
    * Method for getting all recieved messages by ID
    *
    * @param userID user ID
    * @return list of ChatMessage
    */
  override def getAllUserReceivedMessageByID(userID: Int): List[ChatMessage] =
    userReceivedMessage.getAllUserReceivedMessageByID(userID)

  /**
    * Method for counting unread messages
    *
    * @param userID user id
    * @return int
    */
  override def countUnreadMessagesByUserID(userID: Int): Int =
    userReceivedMessage.countUnreadMessagesByUserID(userID)
}
