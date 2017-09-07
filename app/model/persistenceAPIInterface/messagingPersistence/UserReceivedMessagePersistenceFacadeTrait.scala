package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * Created by Casper on 07/09/2017.
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
