package model.matchingLogic.attributeFactory

import model.persistenceAPIInterface.attributesPersistence.{CompetencyPersistenceFacade, SkillPersistenceFacade}


/**
  * Created by Casper on 09/08/2017.
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
