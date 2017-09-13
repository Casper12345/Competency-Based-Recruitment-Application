package model.persistenceAPIInterface.jobDescriptionPersistence

/**
  * Trait for Job Description Competency facade.
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
