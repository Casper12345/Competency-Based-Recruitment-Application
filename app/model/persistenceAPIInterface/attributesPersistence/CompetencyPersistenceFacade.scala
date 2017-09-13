package model.persistenceAPIInterface.attributesPersistence

import persistenceAPI.DataBaseConnection.connectCompetency.DBCompetency
import persistenceAPI.DataBaseConnection.objects.Competency

/**
  * Facade for Competency persistence.
  */
object CompetencyPersistenceFacade extends CompetencyPersistenceFacadeTrait {

  /**
    * Composition DBCompetency
    */
  val competency = DBCompetency

  /**
    * Method for adding Competency to system
    *
    * @param name name of competency
    */
  override def addCompetency(name: String): Unit =
    competency.addCompetency(name)

  /**
    * Method for getting competency by id
    *
    * @param CompetencyID competency id
    * @return option of Competency
    */
  override def getCompetencyByID(CompetencyID: Int): Option[Competency] =
    competency.getCompetencyByID(CompetencyID)

  /**
    * Method for getting all competencies
    *
    * @return list of Competency
    */
  override def getAllCompetencies(): List[Competency] =
    competency.getAllCompetencies()

  /**
    * Method for deleting competency by id
    *
    * @param competencyID competency ID
    */
  override def deleteCompetency(competencyID: Int): Unit =
    competency.deleteCompetency(competencyID)
}
