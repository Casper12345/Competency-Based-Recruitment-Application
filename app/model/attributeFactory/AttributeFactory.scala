package model.attributeFactory

import persistenceAPI.DataBaseConnection.connectCompetency.DBCompetency
import persistenceAPI.DataBaseConnection.connectSkill.DBSkill

/**
  * Created by Casper on 09/08/2017.
  */
object AttributeFactory {

  val dbSkill = DBSkill

  val dBCompetency = DBCompetency

  def getNameOfSkill(skillID: Int): String = dbSkill.getSkillByID(skillID).get.name

  def getNameOfCompetency(competencyID: Int): String =
    dBCompetency.getCompetencyByID(competencyID).get.name


  def createAttribute(typeOfAttribute: String)(ID: Int, rating: Int): Attribute =
    typeOfAttribute match {

      case "skill" => Attribute(ID, getNameOfSkill(ID), rating, "skill")
      case "competency" => Attribute(ID, getNameOfCompetency(ID), rating, "competency")

    }

}
