package model.persistenceAPIInterface.attributesPersistence

import persistenceAPI.DataBaseConnection.objects.Skill

/**
  * Created by Casper on 07/09/2017.
  */
trait SkillPersistenceFacadeTrait {

  /**
    * Method for adding skills to the system
    *
    * @param name name of skill
    */
  def addSkill(name: String): Unit

  /**
    * Method for getting skill by ID
    *
    * @param SkillID id of skill
    * @return option of Skill
    */
  def getSkillByID(SkillID: Int): Option[Skill]

  /**
    * Method for getting all skills.
    *
    * @return list of Skill
    */
  def getAllSkills(): List[Skill]

  /**
    * Method for deleting skill by id.
    * @param skillId skill id
    */
  def deleteSkill(skillId: Int): Unit


}
