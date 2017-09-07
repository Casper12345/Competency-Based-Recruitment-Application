package persistenceAPI.DataBaseConnection.connectUser

import org.scalatest.FunSuite

/**
  * Created by Casper on 04/08/2017.
  */
class DBUserSentMessage$Test extends FunSuite {

  test("addUserSentMessage") {

    val db = DBUserSentMessage

    db.addUserSentMessage(1, 1)

    assert(db.getAllUserSentMessageByID(3).head.senderUserID == 3)


  }

  test("getAllUserSentMessageByID") {

    val db = DBUserSentMessage

    assert(db.getAllUserSentMessageByID(3).head.senderUserID == 3)


  }


}
