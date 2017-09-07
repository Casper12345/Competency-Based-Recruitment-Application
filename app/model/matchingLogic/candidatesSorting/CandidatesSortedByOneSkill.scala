package model.matchingLogic.candidatesSorting

import model.persistenceAPIInterface.matchingPersistence.MatchingQueriesPersistenceFacade
import persistenceAPI.DataBaseConnection.objects.Candidate
import persistenceAPI.DataBaseConnection.sqlQueries.DBQueries

/**
  * Created by Casper on 08/08/2017.
  */
case class CandidatesSortedByOneSkill() extends CandidatesSortingTrait {

  override def returnCandidatesByJobDescriptionID(jobDescriptionID: Int): List[Candidate] = {

    val matchingQueriesFacade = MatchingQueriesPersistenceFacade

    matchingQueriesFacade.getMatchingCandidatesOneSkillByJobID(jobDescriptionID).map(a => a._1)

  }

}
