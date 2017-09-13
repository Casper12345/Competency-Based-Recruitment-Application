package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.connectUser.DBUserSentMessage
import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * User Sent Messages Persistence Facade.
  */
object UserSentMessagePersistenceFacade extends UserSentMessagePersistenceFacadeTrait {

  val userSentMessage = DBUserSentMessage

  override def getAllUserSentMessageByID(userID: Int): List[ChatMessage] =
    userSentMessage.getAllUserSentMessageByID(userID)
}
