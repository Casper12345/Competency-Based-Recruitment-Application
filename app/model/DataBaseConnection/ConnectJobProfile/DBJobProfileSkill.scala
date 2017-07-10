package model.DataBaseConnection.ConnectJobProfile

import model.DataBaseConnection.DBMain

/**
  * Created by Casper on 10/07/2017.
  */
object DBJobProfileSkill {

  // JobProfileSkill DB methods

  val db = DBMain

  def addJobProfileSkill(skillID: Int, jobProfileID: Int): Unit = {

    // JobProfileSkill(SkillID INT, JobProfileID INT);

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO JobProfileSkill VALUES (?,?)")

    stmt.setString(1, skillID.toString)

    stmt.setString(2, jobProfileID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
