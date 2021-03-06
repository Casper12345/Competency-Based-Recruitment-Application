package model.persistenceAPIInterface.jobDescriptionPersistence

import persistenceAPI.DataBaseConnection.objects.JobDescription

/**
  * Trait for Job Description Facade.
  */
trait JobDescriptionPersistenceFacadeTrait {

  /**
    * Method for adding job description to the system
    *
    * @param jobTitle          job title
    * @param educationName     education name
    * @param educationLevelID  education level id
    * @param experienceLevelID experience level id
    */
  def addJobProfile(jobTitle: String, educationName: String,
                    educationLevelID: String, experienceLevelID: String): Unit


  /**
    * Method for getting jobprofile by id
    *
    * @param jobProfileID job profile id
    * @return option of JobDescription
    */
  def getJobProfileByID(jobProfileID: Int): Option[JobDescription]

  /**
    * Method for getting all Job Descriptions
    *
    * @return list of Job Descriptions
    */
  def getAllJobDescriptions(): List[JobDescription]

  /**
    * Method for deleting job descriptions by id
    *
    * @param jobDescriptionID job description id
    */
  def deleteJobDescription(jobDescriptionID: Int): Unit

}
