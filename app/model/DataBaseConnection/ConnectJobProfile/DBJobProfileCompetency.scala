package model.DataBaseConnection.ConnectJobProfile

import model.DataBaseConnection.DBMain

/**
  * Created by Casper on 10/07/2017.
  */
object DBJobProfileCompetency {

  // JobProfileCompetency DB methods

  val db = DBMain

  def addJobProfileCompetency(competencyID: Int, jobProfileID: Int): Unit = {

    // JobProfileCompetency(CompetencyID INT, JobProfileID INT);

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO JobProfileCompetency VALUES (?,?)")

    stmt.setString(1, competencyID.toString)

    stmt.setString(2, jobProfileID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
