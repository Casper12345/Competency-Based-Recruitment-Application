package model.matchingLogic.candidatesSorting

import model.persistenceAPIInterface.matchingPersistence.MatchingQueriesPersistenceFacade
import persistenceAPI.DataBaseConnection.objects.Candidate

/**
  * Candidates sorted by one skill
  */
case class CandidatesSortedByOneSkill() extends CandidatesSortingTrait {

  /**
    * Method for returning candidates by job description id.
    *
    * @param jobDescriptionID job description id
    * @return list of candidates
    */
  override def returnCandidatesByJobDescriptionID(jobDescriptionID: Int): List[Candidate] = {

    val matchingQueriesFacade = MatchingQueriesPersistenceFacade

    matchingQueriesFacade.getMatchingCandidatesOneSkillByJobID(jobDescriptionID).map(a => a._1)

  }

}
