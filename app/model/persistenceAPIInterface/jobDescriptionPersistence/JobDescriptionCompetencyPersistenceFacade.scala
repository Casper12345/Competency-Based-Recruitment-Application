package model.persistenceAPIInterface.jobDescriptionPersistence

import persistenceAPI.DataBaseConnection.connectJobProfile.DBJobProfileCompetency

/**
  * Created by Casper on 07/09/2017.
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
