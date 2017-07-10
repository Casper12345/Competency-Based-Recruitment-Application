package model.DataBaseConnection.ConnectCandidate

import model.DataBaseConnection.DBMain

/**
  * Created by Casper on 10/07/2017.
  */
object DBCandidateSkill {

  // CandidateSkill DB methods

  val db = DBMain

  def addCandidateSkill(skillID: Int, rating: Int, candidateID: Int): Unit ={

    // CandidateSkill(SkillID INT, Rating INT, CandidateID INT);

    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO CandidateSkill VALUES (?,?,?)")

    stmt.setString(1, skillID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, candidateID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
