package persistenceAPI.DataBaseConnection.ConnectJobProfile

import persistenceAPI.DataBaseConnection.DBMain

/**
  * DataBase methods for JobProfileSkill table and handling request to JobProfileSkill table
  */
object DBJobProfileSkill {

  /**
    * Connect to main data base
    */
  val db = DBMain

  /**
    * Method for inserting JobProfile Skill
    * SQL - JobProfileSkill(SkillID INT, Rating INT, JobProfileID INT)
    *
    * @param skillID
    * @param rating
    * @param jobProfileID
    */
  def addJobProfileSkill(skillID: Int, rating: Int, jobProfileID: Int): Unit = {

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO JobProfileSkill VALUES (?,?,?)")

    stmt.setString(1, skillID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, jobProfileID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
