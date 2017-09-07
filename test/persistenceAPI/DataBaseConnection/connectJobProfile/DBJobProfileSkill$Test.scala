package persistenceAPI.DataBaseConnection.connectJobProfile

import persistenceAPI.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBJobProfileSkill$Test extends FunSuite {

  // JobProfileSkill Test
  test("addJobProfileSkill") {

    val db = DBJobProfileSkill

    val db2 = DBJobProfile

    db.addJobProfileSkill(1, 1, 1)

    val skill = db2.getJobProfileByID(1)

    assert(skill.get.competencies.head.name == "C++")


  }

}
