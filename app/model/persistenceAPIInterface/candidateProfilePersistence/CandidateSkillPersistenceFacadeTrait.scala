package model.persistenceAPIInterface.candidateProfilePersistence

/**
  * Created by Casper on 07/09/2017.
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
