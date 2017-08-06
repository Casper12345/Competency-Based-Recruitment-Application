package persistenceAPI.DataBaseConnection.connectChatMessage

import org.scalatest.FunSuite

/**
  * Created by Casper on 31/07/2017.
  */
class DBChatMessage$Test extends FunSuite {


  test("addChatMessage") {

    val db = DBChatMessage

    db.addChatMessage("Title", "Hello there. This is test", messageRead = false)

    assert(db.getChatMessageByID(1).get.chatMessageID == 1)


  }

  test("getChatMessageByID") {

    val db = DBChatMessage

    println(db.getChatMessageByID(6))

  }

  test("createSentNewChatMessage") {

    val db = DBChatMessage

    db.createSentNewChatMessage(1,3, "Subject", "Body")

  }

  test("setReadToTrueByID"){

    val db = DBChatMessage

    db.setReadToTrueByID(8)

  }

}
