package persistenceAPI.DataBaseConnection.connectCandidate

import persistenceAPI.DataBaseConnection.DBMain

/**
  * DataBase methods for CandidateCompetency ltable and handling request to CandidateCompetency table.
  */
object DBCandidateCompetency {

  /**
    * Connection to main DB
    */
  val db = DBMain

  /**
    * Method for inserting into table CandidateCompetency
    * SQL - CandidateCompetency(CompetencyID INT, Rating INT, CandidateID INT)
    *
    * @param competencyID
    * @param rating
    * @param candidateID
    */
  def addCandidateCompetency(competencyID: Int, rating: Int, candidateID: Int): Unit = {

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO CandidateCompetency VALUES (?,?,?)")

    stmt.setString(1, competencyID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, candidateID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
