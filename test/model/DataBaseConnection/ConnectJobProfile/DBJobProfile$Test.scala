package model.DataBaseConnection.ConnectJobProfile

import model.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBJobProfile$Test extends FunSuite {

  // JobProfile Tests

  val db = DBJobProfile

  test("addJobProfile"){


    db.addJobProfile("Programmer")

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }


  test("getJobProfileByID"){


    val jobProfile = db.getJobProfileByID(1)

    val ID = jobProfile.head

    val name  = jobProfile(1)


    assert(ID == "1")

    assert(name == "Programmer")

    jobProfile.foreach(a => println(a))


  }



}
