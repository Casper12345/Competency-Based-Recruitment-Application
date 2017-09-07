package model.persistenceAPIInterface.jobDescriptionPersistence

/**
  * Created by Casper on 07/09/2017.
  */
trait JobDescriptionCompetencyPersistenceFacadeTrait {

  /**
    * Method for adding competency to jobDescription
    *
    * @param competencyID competency id
    * @param rating       rating
    * @param jobProfileID job profile id
    */
  def addJobProfileCompetency(competencyID: Int, rating: Int, jobProfileID: Int): Unit

}
