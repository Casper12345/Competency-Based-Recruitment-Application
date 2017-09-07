package persistenceAPI.DataBaseConnection.connectCandidate

import persistenceAPI.DataBaseConnection.DBMain
import org.scalatest.FunSuite
import persistenceAPI.DataBaseConnection.connectSkill.DBSkill

/**
  * Created by Casper on 10/07/2017.
  */
class DBCandidateSkill$Test extends FunSuite {

  // CandidateSkill Tests

  test("addCandidateSkill") {

    val db = DBCandidateSkill

    db.addCandidateSkill(1, 5, 1)

    val db2 = DBSkill

    val competency = db2.getSkillByID(1)

    assert(competency.get.name == "Leadership")

  }

}
