package model.persistenceAPIInterface.candidateProfilePersistence

/**
  * Created by Casper on 07/09/2017.
  */
trait CandidateCompetencyPersistenceFacadeTrait {

  /**
    * Method for adding competencies to candidates
    *
    * @param competencyID competency ID
    * @param rating       rating
    * @param candidateID  candidate id
    */
  def addCandidateCompetency(competencyID: Int, rating: Int, candidateID: Int): Unit

}
