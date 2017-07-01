package model.DataBaseConnection

import org.scalatest.FunSuite

/**
  * Created by Casper on 01/07/2017.
  */
class DBConnect$Test extends FunSuite {

  // Unit test for check user

  test("testCheckUser") {

    val db = DBConnect

    assert(!db.checkUser("jack", "123"))

    assert(db.checkUser("Casper", "MyPassword"))


  }

  test("containsUser"){

    val db = DBConnect

    db.connect()

    assert(!db.containsUser("Jim"))

    assert(db.containsUser("Casper"))

    db.closeConnection()

  }

  test("getLatestUserId"){

    val db = DBConnect

    db.connect()

    assert(db.getLatestUserId == 2)

    assert(db.getLatestUserId != 3)

    db.closeConnection()


  }


  test("insertNewUser"){

    val db = DBConnect

    db.connect()

    assert(!db.insertNewUser("Casper", "123"))

    //assert(db.insertNewUser("John", "123"))

    db.closeConnection()


  }
}
