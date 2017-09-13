package model.persistenceAPIInterface.candidateProfilePersistence

/**
  * Trait for Candidate Competency facade.
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
