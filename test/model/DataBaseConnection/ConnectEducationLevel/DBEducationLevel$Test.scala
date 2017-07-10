package model.DataBaseConnection.ConnectEducationLevel

import model.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBEducationLevel$Test extends FunSuite {

  val db = DBEducationLevel
  // EducationLevel tests
  /*
  test("addEducationLevel"){

    val db = DBMain

    db.addEducationLevel("1", "No Formal Education")

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }
  */

  test("getEducationLevelByID"){

    val educationLevel = db.getEducationLevelByID(1)

    val level = educationLevel(1)

    val name  = educationLevel(2)


    assert(level == "0")

    assert(name == "No Education")

    educationLevel.foreach(a => println(a))


  }

}
