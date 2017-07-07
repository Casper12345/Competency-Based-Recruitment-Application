package model.DataBaseConnection

import java.sql.{Connection, DriverManager, SQLException}

/**
  * Created by Casper on 06/07/2017.
  */

object DBMain {

  val driver = "com.mysql.cj.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/CandidateDataBase"
  val username = "masterUser"
  val password = "password"
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

      /*
      case "CandidateCompetency" => "SELECT MAX(CompetencyID) FROM CandidateCompetency"
      case "CandidateSkill" => "SELECT MAX(SkillID) FROM CandidateSkill"
      case "CandidateSkill" => "SELECT MAX(SkillID) FROM CandidateSkill"
      */
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


  // methods for Candidate table

  def addCandidate(name: String, surname: String, currentJobTitle: String,
                   educationLevelID: String, experienceLevelID: String): Unit ={

    connect()

    val maxID = getLatestId("Candidate")

    val stmt = connection.prepareStatement("INSERT INTO Candidate VALUES (?,?,?,?,?,?)")


    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)

    stmt.setString(3, surname)

    stmt.setString(4, currentJobTitle)

    stmt.setString(5, educationLevelID)

    stmt.setString(6, experienceLevelID)

    stmt.executeUpdate

    closeConnection()

  }

  def getCandidateByID(CandidateID: Int): List[String] ={

    var toReturn: List[String] = Nil

    connect()

    val selectSQL = "SELECT * FROM Candidate WHERE CandidateID = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CandidateID)

    val rs = preparedStatement.executeQuery()


    while (rs.next()) {

      toReturn =  toReturn :+ rs.getString("CandidateID")
      toReturn =  toReturn :+ rs.getString("Name")
      toReturn =  toReturn :+ rs.getString("Surname")
      toReturn =  toReturn :+ rs.getString("CurrentJobTitle")
      toReturn =  toReturn :+ rs.getString("EducationLevelID")
      toReturn =  toReturn :+ rs.getString("ExperienceLevelID")

    }


    closeConnection()

    toReturn
  }


  // Competency DB methods

  def addCompetency(name: String): Unit ={

    //Competency(CompetencyID INT, Name TEXT);

    connect()

    val maxID = getLatestId("Competency")

    val stmt = connection.prepareStatement("INSERT INTO Competency VALUES (?,?)")


    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)


    stmt.executeUpdate

    closeConnection()

  }

  def getCompetencyByID(CompetencyID: Int): List[String] ={

    var toReturn: List[String] = Nil

    connect()

    val selectSQL = "SELECT * FROM Competency WHERE CompetencyID = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CompetencyID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      toReturn =  toReturn :+ rs.getString("CompetencyID")
      toReturn =  toReturn :+ rs.getString("Name")

    }


    closeConnection()

    toReturn

  }

  // Skill DB methods

  def addSkill(name: String): Unit ={

    //Skill(SkillID INT, Name TEXT);

    connect()

    val maxID = getLatestId("Skill")

    val stmt = connection.prepareStatement("INSERT INTO Skill VALUES (?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)

    stmt.executeUpdate

    closeConnection()

  }


  def getSkillByID(SkillID: Int): List[String] = {

    var toReturn: List[String] = Nil

    connect()

    val selectSQL = "SELECT * FROM Skill WHERE SkillID = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, SkillID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      toReturn = toReturn :+ rs.getString("SkillID")
      toReturn = toReturn :+ rs.getString("Name")

    }

    closeConnection()

    toReturn

  }


  // EducationLevel DB methods

  def addEducationLevel(level: String, name: String): Unit ={

    //EducationLevel(EducationLevelID INT, Level INT, Name TEXT);

    connect()

    val maxID = getLatestId("EducationLevel")

    val stmt = connection.prepareStatement("INSERT INTO EducationLevel VALUES (?,?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, level)

    stmt.setString(3, name)

    stmt.executeUpdate

    closeConnection()

  }

  def getEducationLevelByID(SkillID: Int): List[String] = {

    var toReturn: List[String] = Nil

    connect()

    val selectSQL = "SELECT * FROM EducationLevel WHERE EducationLevelID = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, SkillID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      toReturn = toReturn :+ rs.getString("EducationLevelID")
      toReturn = toReturn :+ rs.getString("Level")
      toReturn = toReturn :+ rs.getString("Name")

    }

    closeConnection()

    toReturn

  }

  // ExperienceLevel DB methods

  def addExperienceLevel(level: String, name: String): Unit ={

    //ExperienceLevel(ExperienceLevelID INT, Level INT, Name TEXT);

    connect()

    val maxID = getLatestId("ExperienceLevel")

    val stmt = connection.prepareStatement("INSERT INTO ExperienceLevel VALUES (?,?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, level)

    stmt.setString(3, name)

    stmt.executeUpdate

    closeConnection()

  }

  def getExperienceLevelByID(SkillID: Int): List[String] = {

    var toReturn: List[String] = Nil

    connect()

    val selectSQL = "SELECT * FROM ExperienceLevel WHERE ExperienceLevelID = ?"

    val preparedStatement = connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, SkillID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      toReturn = toReturn :+ rs.getString("ExperienceLevelID")
      toReturn = toReturn :+ rs.getString("Level")
      toReturn = toReturn :+ rs.getString("Name")

    }

    closeConnection()

    toReturn

  }

  // CandidateCompetency DB methods

  def addCandidateCompetency(competencyID: Int, rating: Int, candidateID: Int): Unit ={

    // CandidateCompetency(CompetencyID INT, Rating INT, CandidateID INT);

    connect()

    val stmt = connection.prepareStatement("INSERT INTO CandidateCompetency VALUES (?,?,?)")

    stmt.setString(1, competencyID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, candidateID.toString)

    stmt.executeUpdate

    closeConnection()

  }

  // CandidateSkill DB methods

  def addCandidateSkill(skillID: Int, rating: Int, candidateID: Int): Unit ={

    // CandidateSkill(SkillID INT, Rating INT, CandidateID INT);

    connect()

    val stmt = connection.prepareStatement("INSERT INTO CandidateSkill VALUES (?,?,?)")

    stmt.setString(1, skillID.toString)

    stmt.setString(2, rating.toString)

    stmt.setString(3, skillID.toString)

    stmt.executeUpdate

    closeConnection()

  }

  // JobProfile DB methods

  def addJobProfile(name: String): Unit ={

    //  JobProfile(JobProfileID INT, Name TEXT)


    connect()

    val maxID = getLatestId("JobProfile")

    val stmt = connection.prepareStatement("INSERT INTO JobProfile VALUES (?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)

    stmt.executeUpdate

    closeConnection()

  }





}
