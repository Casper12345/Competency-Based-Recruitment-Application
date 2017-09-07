package model.persistenceAPIInterface.messagingPersistence

import persistenceAPI.DataBaseConnection.objects.ChatMessage

/**
  * Created by Casper on 07/09/2017.
  */
trait UserSentMessagePersistenceFacadeTrait {

  def getAllUserSentMessageByID(userID: Int): List[ChatMessage]

}
