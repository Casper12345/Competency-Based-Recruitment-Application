package model.matchingLogic.attributeFactory

import model.persistenceAPIInterface.attributesPersistence.{CompetencyPersistenceFacade, SkillPersistenceFacade}


/**
  * Attribute factory for DI.
  */
object AttributeFactory {

  val skillPersistence = SkillPersistenceFacade

  val competencyPersistence = CompetencyPersistenceFacade

  def getNameOfSkill(skillID: Int): String = skillPersistence.getSkillByID(skillID).get.name

  def getNameOfCompetency(competencyID: Int): String =
    competencyPersistence.getCompetencyByID(competencyID).get.name

  def createAttribute(typeOfAttribute: String)(ID: Int, rating: Int): Attribute =
    typeOfAttribute match {

      case "skill" => Attribute(ID, getNameOfSkill(ID), rating, "skill")
      case "competency" => Attribute(ID, getNameOfCompetency(ID), rating, "competency")

    }

}
