package model.persistenceAPIInterface.jobDescriptionPersistence

/**
  * Trait for Job Description Skill Persistence Facade.
  */
trait JobDescriptionSkillPersistenceFacadeTrait {

  /**
    * Method for adding jobDescription skills to the system
    *
    * @param skillID      skill id
    * @param rating       rating
    * @param jobProfileID job Description id
    */
  def addJobProfileSkill(skillID: Int, rating: Int, jobProfileID: Int): Unit

}
