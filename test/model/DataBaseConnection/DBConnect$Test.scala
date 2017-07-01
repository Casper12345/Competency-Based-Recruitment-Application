package model.DataBaseConnection

import org.scalatest.FunSuite

/**
  * Created by Casper on 01/07/2017.
  */
class DBConnect$Test extends FunSuite {

  // Unit test for check user

  test("testCheckUser") {

    val db = DBConnect

    db.connect()

    assert(!db.checkUser("jack", "123"))

    assert(db.checkUser("Casper", "MyPassword"))

    db.closeConnection()


  }

  test("containsUser"){

    val db = DBConnect

    db.connect()

    assert(!db.containsUser("Jim"))

    assert(db.containsUser("Casper"))

    db.closeConnection()

  }

  test("insertNewUser"){


  }
}
