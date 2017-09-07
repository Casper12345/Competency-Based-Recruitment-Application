package persistenceAPI.DataBaseConnection.connectCompetency

import persistenceAPI.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBCompetency$Test extends FunSuite {

  // Competency tests
  val db = DBCompetency

  test("addCompetency") {


    db.addCompetency("Leadership")

    val competency = db.getCompetencyByID(1).get

    assert(competency.name == "Leadership")

  }

  test("getCompetencyByID") {


    val competency = db.getCompetencyByID(1).get

    assert(competency.competencyID == 1)

    assert(competency.name == "Leadership")


  }

  test("getAllCompetency") {

    val competencies = db.getAllCompetencies()

    assert(competencies.head.name == "Leadership")

  }
}
