package model.persistenceAPIInterface.candidateProfilePersistence

import persistenceAPI.DataBaseConnection.connectCandidate.DBCandidateCompetency

/**
  * Canidate Competency persistence facade.
  */
object CandidateCompetencyPersistenceFacade extends CandidateCompetencyPersistenceFacadeTrait {

  /**
    * Composition DBCandidateCompetency
    */
  val candidateCompetency = DBCandidateCompetency

  /**
    * Method for adding competencies to candidates
    *
    * @param competencyID competency ID
    * @param rating       rating
    * @param candidateID  candidate id
    */
  override def addCandidateCompetency(competencyID: Int, rating: Int, candidateID: Int): Unit =
    candidateCompetency.addCandidateCompetency(competencyID, rating, candidateID)
}
