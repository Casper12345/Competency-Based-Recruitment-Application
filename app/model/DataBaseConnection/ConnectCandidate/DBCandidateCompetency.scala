package model.DataBaseConnection.ConnectCandidate

import model.DataBaseConnection.DBMain

/**
  * Created by Casper on 10/07/2017.
  */
object DBCandidateCompetency {

  // CandidateCompetency DB methods

  val db = DBMain

  def addCandidateCompetency(competencyID: Int, rating: Int, candidateID: Int): Unit = {

    // CandidateCompetency(CompetencyID INT, Rating INT, CandidateID INT);

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO CandidateCompetency VALUES (?,?,?)")

    stmt.setString(1, competencyID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, candidateID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
