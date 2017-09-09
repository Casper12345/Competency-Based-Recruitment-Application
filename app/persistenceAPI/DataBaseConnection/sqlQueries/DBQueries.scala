package persistenceAPI.DataBaseConnection.sqlQueries

import persistenceAPI.DataBaseConnection.connectCandidate.DBCandidate
import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.objects.{Candidate, CandidateSkill}

/**
  * Created by Casper on 12/07/2017.
  */
object DBQueries {

  val db = DBMain

  /**
    *Method for getting candidates that match on at least one skill.
    * @param jobID
    * @return List of tuples with candidates and int of how many skills are matching
    */
  def getMatchingCandidatesOneSkillByJobID(jobID: Int): List[(Candidate, Int)] = {

    db.connect()

    val selectSQL =
      """SELECT CandidateSkill.CandidateID, COUNT(CandidateSkill.CandidateID)
        |AS NumberOfMatchingSkills
        |FROM CandidateSkill INNER JOIN JobProfileSkill ON
        |CandidateSkill.SkillID = JobProfileSkill.SkillID
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

  /**
    * Method for getting candidates that match on at least one competency.
    * @param jobID
    * @return List of tuples with candidates and int of how many competencies  are matching
    */
  def getMatchingCandidatesOneCompetencyByJobID(jobID: Int): List[(Candidate, Int)] = {

    db.connect()

    val selectSQL =
      """SELECT CandidateCompetency.CandidateID, COUNT(CandidateCompetency.CandidateID) AS NumberOfMatchingCompetencies
        |FROM CandidateCompetency INNER JOIN JobProfileCompetency
        |ON CandidateCompetency.CompetencyID = JobProfileCompetency.CompetencyID
        |WHERE JobProfileID = ?
        |GROUP BY CandidateCompetency.CandidateID
        |HAVING COUNT(CandidateCompetency.CandidateID) > 0
        |ORDER BY COUNT(CandidateCompetency.CandidateID) DESC;""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, jobID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[(Candidate, Int)] = Nil

    while (rs.next()) {

      val candidateID = rs.getString("CandidateCompetency.CandidateID")
      val numberOfMatchingSkills = rs.getString("NumberOfMatchingCompetencies")

      val candidate: Candidate = DBCandidate.getCandidateByID(candidateID.toInt).get

      toReturn = toReturn :+ (candidate, numberOfMatchingSkills.toInt)
    }

    db.closeConnection()

    toReturn

  }

}
