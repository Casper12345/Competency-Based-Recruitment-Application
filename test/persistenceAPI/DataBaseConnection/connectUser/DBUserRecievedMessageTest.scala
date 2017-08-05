package persistenceAPI.DataBaseConnection.connectUser

import org.scalatest.FunSuite

/**
  * Created by Casper on 04/08/2017.
  */
class DBUserRecievedMessageTest extends FunSuite {

  test("addUserRecievedMessage") {

    val db = DBUserRecievedMessage

    db.addUserRecievedMessage(1, 1)
  }


  test("getAllUserSentMessageByID") {

    val db = DBUserRecievedMessage

    println(db.getAllUserReceivedMessageByID(3))
  }

}


