package model.persistenceAPIInterface.candidateProfilePersistence

import persistenceAPI.DataBaseConnection.connectCandidate.DBCandidateCompetency

/**
  * Created by Casper on 07/09/2017.
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
