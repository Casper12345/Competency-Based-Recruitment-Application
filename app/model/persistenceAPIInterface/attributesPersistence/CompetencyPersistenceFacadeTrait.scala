package model.persistenceAPIInterface.attributesPersistence

import persistenceAPI.DataBaseConnection.objects.Competency

/**
  * Created by Casper on 07/09/2017.
  */
trait CompetencyPersistenceFacadeTrait {

  /**
    * Method for adding Competency to system
    *
    * @param name name of competency
    */
  def addCompetency(name: String): Unit

  /**
    * Method for getting competency by id
    *
    * @param CompetencyID competency id
    * @return option of Competency
    */
  def getCompetencyByID(CompetencyID: Int): Option[Competency]

  /**
    * Method for getting all competencies
    *
    * @return list of Competency
    */
  def getAllCompetencies(): List[Competency]

  /**
    * Method for deleting competency by id
    *
    * @param competencyID competency ID
    */
  def deleteCompetency(competencyID: Int): Unit

}
