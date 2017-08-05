package persistenceAPI.DataBaseConnection

import java.sql.{Connection, DriverManager, SQLException}

/**
  * Created by Casper on 06/07/2017.
  */


object DBMain {

  private val driver = "com.mysql.cj.jdbc.Driver"
  private val url = "jdbc:mysql://localhost:3306/CandidateDataBase?autoReconnect=true&useSSL=false"
  private val username = "masterUser"
  private val password = "password"
  var connection: Connection = _

  /**
    * Creates connection to CandidateDataBase
    */
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

  /**
    * String factory that aids creation of ID by getting latest ID created
    *
    * @param table
    * @return returns max ID for given table
    */
  def getLatestId(table: String): Int = {

    def candidateStringFactory(table: String): String = table match {

      case "Candidate" => "SELECT MAX(CandidateID) FROM Candidate"
      case "Competency" => "SELECT MAX(CompetencyID) FROM Competency"
      case "Skill" => "SELECT MAX(SkillID) FROM Skill"
      case "EducationLevel" => "SELECT MAX(EducationLevelID) FROM EducationLevel"
      case "ExperienceLevel" => "SELECT MAX(ExperienceLevelID) FROM ExperienceLevel"
      case "JobProfile" => "SELECT MAX(JobProfileID) FROM JobProfile"
      case "Users" => "SELECT MAX(UserID) FROM Users"
      case "ChatMessage" => "SELECT MAX(ChatMessageID) FROM ChatMessage"

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


}
