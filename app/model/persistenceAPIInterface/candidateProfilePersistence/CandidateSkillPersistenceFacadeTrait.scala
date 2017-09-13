package model.persistenceAPIInterface.candidateProfilePersistence

/**
  * Trait for Candidate Persistence Facade.
  */
trait CandidateSkillPersistenceFacadeTrait {

  /**
    * Method for adding candidateSkill
    *
    * @param skillID     skillID
    * @param rating      rating
    * @param candidateID candidate ID
    */
  def addCandidateSkill(skillID: Int, rating: Int, candidateID: Int): Unit

}
