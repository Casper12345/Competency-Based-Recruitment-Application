package model.persistenceAPIInterface.matchingPersistence

import persistenceAPI.DataBaseConnection.objects.Candidate
import persistenceAPI.DataBaseConnection.sqlQueries.DBQueries

/**
  * Matching Queries Persistence Facade.
  */
object MatchingQueriesPersistenceFacade extends MatchingQueriesPersistenceFacadeTrait {

  /**
    * Composition DBQueries
    */
  val queries = DBQueries

  /**
    * Method for getting candidates from the data base that match by at least one skill
    *
    * @param jobID job id
    * @return list of tuple with candidate and number of matching skills
    */
  override def getMatchingCandidatesOneSkillByJobID(jobID: Int): List[(Candidate, Int)] =
    queries.getMatchingCandidatesOneSkillByJobID(jobID)
}
