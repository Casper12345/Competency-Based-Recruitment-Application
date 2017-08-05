package persistenceAPI.DataBaseConnection

import persistenceAPI.DataBaseConnection.connectUser.DBConnectUser
import org.scalatest.FunSuite

/**
  * Created by Casper on 01/07/2017.
  */
class DBConnectUser$Test extends FunSuite {

  // Unit test for check user

  val mainDB = DBMain

  test("testCheckUser") {

    val db = DBConnectUser

    assert(!db.checkUser("jack", "123"))

    assert(db.checkUser("Casper", "123"))


  }

  test("containsUser") {


    val db = DBConnectUser

    mainDB.connect()

    assert(!db.containsUser("Jim"))

    assert(db.containsUser("Casper"))

    mainDB.closeConnection()

  }

  test("insertNewUser") {

    val db = DBConnectUser

    mainDB.connect()

    assert(!db.insertNewUser("Casper", "123", "SuperUser"))

    assert(db.insertNewUser("Finn", "123456", "SuperUser"))

    mainDB.closeConnection()

  }

  test("getPrivilegeByName") {

    val db = DBConnectUser

    val privilege = db.getPrivilegeByName("Casper")

    assert(privilege == "SuperUser")
  }

  test("getAllRecruiterUsers") {

    val db = DBConnectUser

    println(db.getAllRecruiterUsers())

  }

  test("getIDByUserName") {

    val db = DBConnectUser

    assert(db.getIDByUserName("John") == 2)
  }



}
