package persistenceAPI.DataBaseConnection.ConnectJobProfile

import persistenceAPI.DataBaseConnection.DBMain

/**
  * Created by Casper on 10/07/2017.
  */
object DBJobProfileCompetency {

  // JobProfileCompetency DB methods

  val db = DBMain

  def addJobProfileCompetency(competencyID: Int, rating: Int, jobProfileID: Int): Unit = {

    // JobProfileCompetency(CompetencyID INT, Rating INT, JobProfileID INT);

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO JobProfileCompetency VALUES (?,?,?)")

    stmt.setString(1, competencyID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, jobProfileID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
