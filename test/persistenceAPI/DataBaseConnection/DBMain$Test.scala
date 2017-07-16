package persistenceAPI.DataBaseConnection

import org.scalatest.FunSuite

/**
  * Created by Casper on 06/07/2017.
  */
class DBMain$Test extends FunSuite {

  // utilities tests

  test("getLatestId"){

    val db = DBMain

    db.connect()

    assert(db.getLatestId("Candidate") == 1)

    assert(db.getLatestId("Candidate") != 2)

    db.closeConnection()

  }
}
