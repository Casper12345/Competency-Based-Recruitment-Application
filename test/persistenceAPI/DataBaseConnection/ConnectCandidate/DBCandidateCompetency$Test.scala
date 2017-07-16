package persistenceAPI.DataBaseConnection.ConnectCandidate

import persistenceAPI.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBCandidateCompetency$Test extends FunSuite {

  // CandidateCompetency Test

  test("addCandidateCompetency"){

    val db = DBCandidateCompetency

    db.addCandidateCompetency(1, 2, 1)

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

}
