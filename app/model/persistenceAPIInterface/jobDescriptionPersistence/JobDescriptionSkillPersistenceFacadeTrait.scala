package model.persistenceAPIInterface.jobDescriptionPersistence

/**
  * Created by Casper on 07/09/2017.
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
