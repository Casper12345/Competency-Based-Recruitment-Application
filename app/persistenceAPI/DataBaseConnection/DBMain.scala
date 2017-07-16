package persistenceAPI.DataBaseConnection

import java.sql.{Connection, DriverManager, SQLException}

import persistenceAPI.DataBaseConnection.Objects.{Candidate, CandidateSkill, Skill}


/**
  * Created by Casper on 06/07/2017.
  */

object DBMain {

  private val driver = "com.mysql.cj.jdbc.Driver"
  private val url = "jdbc:mysql://localhost:3306/CandidateDataBase?autoReconnect=true&useSSL=false"
  private val username = "masterUser"
  private val password = "password"
  var connection: Connection = _


  def connect(): Unit = {

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

    } catch {
      case e: SQLException => e.printStackTrace()
    }

  }

  def closeConnection(): Unit = {
    connection.close()
  }


  def getLatestId(table: String): Int = {

    def candidateStringFactory(table: String): String = table match {

      case "Candidate" => "SELECT MAX(CandidateID) FROM Candidate"
      case "Competency"=> "SELECT MAX(CompetencyID) FROM Competency"
      case "Skill"=> "SELECT MAX(SkillID) FROM Skill"
      case "EducationLevel" => "SELECT MAX(EducationLevelID) FROM EducationLevel"
      case "ExperienceLevel" => "SELECT MAX(ExperienceLevelID) FROM ExperienceLevel"
      case "JobProfile" => "SELECT MAX(JobProfileID) FROM JobProfile"

    }

    val selectSQL = candidateStringFactory(table)

    val preparedStatement = connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()

    var max = 0

    while (rs.next()) {
      max = rs.getInt(1)
    }
    max
  }

  // add competency to candidate

  def addCompetencyToCandidate(candidateID: Int, competencyID: Int, rating: Int): Unit ={



  }
  /*
  def getCandidateList(): List[Candidate] ={

    var toReturn: List[Candidate] = Nil

    connect()

    val selectSQL = "SELECT * FROM Candidate"


    val preparedStatement = connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      val ID = rs.getString("CanidateID")

      val name = rs.getString("Name")

      val

    }


    toReturn


  }
  */


}
