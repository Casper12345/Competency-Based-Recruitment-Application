package persistenceAPI.DataBaseConnection.connectJobProfile

import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBJobProfileCompetency$Test extends FunSuite {

  // JobProfileCompetency Tests

  test("addJobProfileCompetency") {

    val db = DBJobProfileCompetency

    val db2 = DBJobProfile

    db.addJobProfileCompetency(1, 1, 1)

    val competency = db2.getJobProfileByID(1)

    assert(competency.get.competencies.head.name == "Leadership")

  }

}
