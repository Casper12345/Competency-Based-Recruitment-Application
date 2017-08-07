package persistenceAPI.DataBaseConnection.connectChatMessage

import java.text.SimpleDateFormat

import model.messageSystemLogic.DateTimeGet
import org.scalatest.FunSuite

/**
  * Created by Casper on 31/07/2017.
  */
class DBChatMessage$Test extends FunSuite {


  test("addChatMessage") {

    val db = DBChatMessage

    val sqlTime = DateTimeGet.getTimeAsTimeStamp

    db.addChatMessage("Title", "Hello there. This is test", messageRead = false, sqlTime)

    assert(db.getChatMessageByID(1).get.chatMessageID == 1)

  }

  test("getChatMessageByID") {

    val db = DBChatMessage

    println(db.getChatMessageByID(1))
    println(db.getChatMessageByID(1).get.timeSent.toString)

    val df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").
      format(db.getChatMessageByID(1).get.timeSent)

    println(df)

  }

  test("createSentNewChatMessage") {

    val db = DBChatMessage

    db.createSentNewChatMessage(1, 3, "Subject", "Body")

  }

  test("setReadToTrueByID") {

    val db = DBChatMessage

    db.setReadToTrueByID(8)

  }

}
