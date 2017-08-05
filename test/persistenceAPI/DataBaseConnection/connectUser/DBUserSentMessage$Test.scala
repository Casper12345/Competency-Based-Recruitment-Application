package persistenceAPI.DataBaseConnection.connectUser

import org.scalatest.FunSuite

/**
  * Created by Casper on 04/08/2017.
  */
class DBUserSentMessage$Test extends FunSuite {

  test("addUserSentMessage") {

    val db = DBUserSentMessage

    db.addUserSentMessage(1, 1)


  }

  test("getAllUserSentMessageByID") {

    val db = DBUserSentMessage

    println(db.getAllUserSentMessageByID(2))


  }


}
