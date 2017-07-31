package persistenceAPI.DataBaseConnection.connectJobProfile

import persistenceAPI.DataBaseConnection.DBMain

/**
  * DataBase methods for JobProfileCompetency table and handling request to JobProfileCompetency table
  */
object DBJobProfileCompetency {

  /**
    * Connect to main DB
    */
  val db = DBMain

  /**
    * Method for inserting values into JobProfileCompetency table
    * SQL- JobProfileCompetency(CompetencyID INT, Rating INT, JobProfileID INT);
    *
    * @param competencyID
    * @param rating
    * @param jobProfileID
    */
  def addJobProfileCompetency(competencyID: Int, rating: Int, jobProfileID: Int): Unit = {


    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO JobProfileCompetency VALUES (?,?,?)")

    stmt.setString(1, competencyID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, jobProfileID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
