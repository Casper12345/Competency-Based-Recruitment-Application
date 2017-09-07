package persistenceAPI.DataBaseConnection.connectEducationLevel

import persistenceAPI.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBEducationLevel$Test extends FunSuite {

  val db = DBEducationLevel
  // EducationLevel tests


  test("addEducationLevel") {

    db.addEducationLevel("1", "No Formal Education")

    val e = db.getEducationLevelByID(1)

    assert(e.head == "No Formal Education")

  }


  test("getEducationLevelByID") {

    val educationLevel = db.getEducationLevelByID(1)

    val level = educationLevel(1)

    val name = educationLevel(2)


    assert(level == "0")

    assert(name == "No Education")


  }

}
