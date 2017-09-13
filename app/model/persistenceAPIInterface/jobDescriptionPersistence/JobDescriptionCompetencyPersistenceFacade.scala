package model.persistenceAPIInterface.jobDescriptionPersistence

import persistenceAPI.DataBaseConnection.connectJobProfile.DBJobProfileCompetency

/**
  * Job Description Competency Facade.
  */
object JobDescriptionCompetencyPersistenceFacade extends JobDescriptionCompetencyPersistenceFacadeTrait {

  /**
    * Composition DBJobProfileCompetency
    */
  val jobDescriptionCompetency = DBJobProfileCompetency

  /**
    * Method for adding competency to jobDescription
    *
    * @param competencyID competency id
    * @param rating       rating
    * @param jobProfileID job profile id
    */
  override def addJobProfileCompetency(competencyID: Int, rating: Int, jobProfileID: Int): Unit =
    jobDescriptionCompetency.addJobProfileCompetency(competencyID, rating, jobProfileID)
}
