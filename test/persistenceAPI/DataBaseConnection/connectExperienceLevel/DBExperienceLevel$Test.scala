package persistenceAPI.DataBaseConnection.connectExperienceLevel

import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBExperienceLevel$Test extends FunSuite {


  val db = DBExperienceLevel
  // EducationLevel tests

  test("addEducationLevel") {


    db.addExperienceLevel("1", "No Experience")

    val e = db.getExperienceLevelByID(1)

    assert(e.head == "No Experience")

  }


  test("getEducationLevelByID") {

    val educationLevel = db.getExperienceLevelByID(1)

    val level = educationLevel(1)

    val name = educationLevel(2)


    assert(level == "0")

    assert(name == "No Experience")


  }


}
