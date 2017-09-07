package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.connectUser.DBUserSentMessage
import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * Created by Casper on 07/09/2017.
  */
object UserSentMessagePersistenceFacade extends UserSentMessagePersistenceFacadeTrait {

  val userSentMessage = DBUserSentMessage

  override def getAllUserSentMessageByID(userID: Int): List[ChatMessage] =
    userSentMessage.getAllUserSentMessageByID(userID)
}
