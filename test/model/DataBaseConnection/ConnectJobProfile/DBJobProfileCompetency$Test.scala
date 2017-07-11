package model.DataBaseConnection.ConnectJobProfile

import model.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBJobProfileCompetency$Test extends FunSuite {

  // JobProfileCompetency Tests

  test("addJobProfileCompetency"){

    val db = DBJobProfileCompetency

    db.addJobProfileCompetency(1,1,1)

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

}
