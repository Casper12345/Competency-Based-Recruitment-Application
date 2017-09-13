package model.persistenceAPIInterface.matchingPersistence

import persistenceAPI.DataBaseConnection.objects.Candidate

/**
  * Trait for Matching Queries Persistence Facade.
  */
trait MatchingQueriesPersistenceFacadeTrait {

  /**
    * Method for getting candidates from the data base that match by at least one skill
    *
    * @param jobID job id
    * @return list of tuple with candidate and number of matching skills
    */
  def getMatchingCandidatesOneSkillByJobID(jobID: Int): List[(Candidate, Int)]

}
