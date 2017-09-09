package model.persistenceAPIInterface.attributesPersistence

import persistenceAPI.DataBaseConnection.connectSkill.DBSkill
import persistenceAPI.DataBaseConnection.objects.Skill

/**
  * Created by Casper on 07/09/2017.
  */
object SkillPersistenceFacade extends SkillPersistenceFacadeTrait {

  /**
    * Composition DBSkill
    */
  val skill = DBSkill

  /**
    * Method for adding skills to the system
    *
    * @param name name of skill
    */
  override def addSkill(name: String): Unit =
    skill.addSkill(name)

  /**
    * Method for getting skill by ID
    *
    * @param SkillID id of skill
    * @return option of Skill
    */
  override def getSkillByID(SkillID: Int): Option[Skill] =
    skill.getSkillByID(SkillID)

  /**
    * Method for getting all skills.
    *
    * @return list of Skill
    */
  override def getAllSkills(): List[Skill] =
    skill.getAllSkills()

  /**
    * Method for deleting skill by id.
    *
    * @param skillId skill id
    */
  override def deleteSkill(skillId: Int): Unit =
    skill.deleteSkill(skillId)
}
