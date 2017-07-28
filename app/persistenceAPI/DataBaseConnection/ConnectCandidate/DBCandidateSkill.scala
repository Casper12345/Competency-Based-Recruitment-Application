package persistenceAPI.DataBaseConnection.ConnectCandidate

import persistenceAPI.DataBaseConnection.DBMain

/**
  * DataBase methods for CandidateSkill table and handling request to CandidateSkill table.
  */
object DBCandidateSkill {

  /**
    * Method for inserting into table CandidateSkill
    * SQL - CandidateSkill(SkillID INT, Rating INT, CandidateID INT);
    *
    */
  val db = DBMain

  def addCandidateSkill(skillID: Int, rating: Int, candidateID: Int): Unit = {


    db.connect()

    val stmt = db.connection.prepareStatement("INSERT INTO CandidateSkill VALUES (?,?,?)")

    stmt.setString(1, skillID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, candidateID.toString)

    stmt.executeUpdate

    db.closeConnection()

  }

}
