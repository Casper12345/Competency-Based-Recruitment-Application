package model.persistenceAPIInterface.jobDescriptionPersistence

import persistenceAPI.DataBaseConnection.connectJobProfile.DBJobProfile
import persistenceAPI.DataBaseConnection.objects.JobDescription

/**
  * Created by Casper on 07/09/2017.
  */
object JobDescriptionPersistenceFacade extends JobDescriptionPersistenceFacadeTrait {

  /**
    * Composition DBJobProfile
    */
  val jobDescription = DBJobProfile

  /**
    * Method for adding job description to the system
    *
    * @param jobTitle          job title
    * @param educationName     education name
    * @param educationLevelID  education level id
    * @param experienceLevelID experience level id
    */
  override def addJobProfile(jobTitle: String,
                             educationName: String,
                             educationLevelID: String,
                             experienceLevelID: String): Unit = {

    jobDescription.addJobProfile(jobTitle, educationName, educationLevelID, experienceLevelID)
  }

  /**
    * Method for getting jobprofile by id
    *
    * @param jobProfileID job profile id
    * @return option of JobDescription
    */
  override def getJobProfileByID(jobProfileID: Int): Option[JobDescription] =
    jobDescription.getJobProfileByID(jobProfileID)

  /**
    * Method for getting all Job Descriptions
    *
    * @return list of Job Descriptions
    */
  override def getAllJobDescriptions(): List[JobDescription] =
    jobDescription.getAllJobDescriptions()
}
