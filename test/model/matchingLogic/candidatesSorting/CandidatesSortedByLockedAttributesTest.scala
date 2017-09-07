package model.matchingLogic.candidatesSorting

import org.scalatest.FunSuite
import persistenceAPI.DataBaseConnection.objects.{JobDescriptionCompetency, JobDescriptionSkill}


/**
  * Created by Casper on 08/08/2017.
  */
class CandidatesSortedByLockedAttributesTest extends FunSuite {

  test("returnCandidatesByJobDescriptionIDLocked") {
    val skill = List(JobDescriptionSkill(11, "XML", 5), JobDescriptionSkill(4, "MySql", 1))

    val comp = List(JobDescriptionCompetency(1, "LeaderShip", 3))

    val c = CandidatesSortedByLockedAttributes()

    c.setAttributes(skill, comp)

    val returned = c.returnCandidatesByJobDescriptionID(1)

    assert(returned.size == 1)

  }


}
