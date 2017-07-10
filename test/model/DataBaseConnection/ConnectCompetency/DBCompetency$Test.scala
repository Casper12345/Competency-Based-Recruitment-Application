package model.DataBaseConnection.ConnectCompetency

import model.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBCompetency$Test extends FunSuite {

  // Competency tests
  val db = DBCompetency

  test("addCompetency"){


    db.addCompetency("Leadership")

    val competency = db.getCompetencyByID(1).get

    assert(competency.name == "Leadership")

  }

  test("getCompetencyByID"){


    val competency = db.getCompetencyByID(1).get



    assert(competency.competencyID == 1)

    assert(competency.name == "Leadership")

    //competency.foreach(a => println(a))

  }

  test("getAllCompetency"){

    val competencies = db.getAllCompetencies()

    competencies.foreach(a => println(a))

  }
}
