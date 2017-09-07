package model.persistenceAPIInterface.jobDescriptionPersistence

import persistenceAPI.DataBaseConnection.connectJobProfile.DBJobProfileSkill

/**
  * Created by Casper on 07/09/2017.
  */
object JobDescriptionSkillPersistenceFacade extends JobDescriptionSkillPersistenceFacadeTrait {

  /**
    * Compostion DBJobProfileSkill
    */
  val jobDecriptionSkill = DBJobProfileSkill

  /**
    * Method for adding jobDescription skills to the system
    *
    * @param skillID      skill id
    * @param rating       rating
    * @param jobProfileID job Description id
    */
  override def addJobProfileSkill(skillID: Int, rating: Int, jobProfileID: Int): Unit =
    jobDecriptionSkill.addJobProfileSkill(skillID, rating, jobProfileID)
}
