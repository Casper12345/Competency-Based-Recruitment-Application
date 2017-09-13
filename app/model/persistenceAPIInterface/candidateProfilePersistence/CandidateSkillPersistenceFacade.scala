package model.persistenceAPIInterface.candidateProfilePersistence

import persistenceAPI.DataBaseConnection.connectCandidate.DBCandidateSkill

/**
  * Candidate Skill Persistence facade.
  */
object CandidateSkillPersistenceFacade extends CandidateSkillPersistenceFacadeTrait {

  /**
    * Composition DBCandidateSkill
    */
  val candidateSkill = DBCandidateSkill

  /**
    * Method for adding candidateSkill
    *
    * @param skillID     skillID
    * @param rating      rating
    * @param candidateID candidate ID
    */
  override def addCandidateSkill(skillID: Int, rating: Int, candidateID: Int): Unit =
    candidateSkill.addCandidateSkill(skillID, rating, candidateID)

}
