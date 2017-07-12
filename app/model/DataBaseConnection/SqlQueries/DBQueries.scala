package model.DataBaseConnection.SqlQueries

import model.DataBaseConnection.ConnectCandidate.DBCandidate
import model.DataBaseConnection.DBMain
import model.DataBaseConnection.Objects.{Candidate, CandidateSkill}

/**
  * Created by Casper on 12/07/2017.
  */
object DBQueries {

  val db = DBMain

  def getMatchingCandidatesOneSkillByJobID(jobID: Int): List[(Candidate, Int)] = {

    db.connect()

    val selectSQL =
      """SELECT CandidateSkill.CandidateID, COUNT(CandidateSkill.CandidateID) AS NumberOfMatchingSkills
        |FROM CandidateSkill INNER JOIN JobProfileSkill ON CandidateSkill.SkillID = JobProfileSkill.SkillID
        |WHERE JobProfileID = ?
        |GROUP BY CandidateSkill.CandidateID
        |HAVING COUNT(CandidateSkill.CandidateID) > 0
        |ORDER BY COUNT(CandidateSkill.CandidateID) DESC;""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, jobID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[(Candidate, Int)] = Nil

    while (rs.next()) {

      val candidateID = rs.getString("CandidateSkill.CandidateID")
      val numberOfMatchingSkills = rs.getString("NumberOfMatchingSkills")

      val candidate: Candidate = DBCandidate.getCandidateByID(candidateID.toInt).get

      toReturn = toReturn :+ (candidate, numberOfMatchingSkills.toInt)
    }

    db.closeConnection()

    toReturn


  }

}
