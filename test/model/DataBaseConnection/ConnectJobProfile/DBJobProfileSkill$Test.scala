package model.DataBaseConnection.ConnectJobProfile

import model.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBJobProfileSkill$Test extends FunSuite {

  // JobProfileSkill Test
  test("addJobProfileSkill"){

    val db = DBJobProfileSkill

    db.addJobProfileSkill(1,1)

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

}
