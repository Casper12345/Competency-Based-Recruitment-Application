package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * Trait for User Sent Message Persistence Facade.
  */
trait UserSentMessagePersistenceFacadeTrait {

  def getAllUserSentMessageByID(userID: Int): List[ChatMessage]

}
