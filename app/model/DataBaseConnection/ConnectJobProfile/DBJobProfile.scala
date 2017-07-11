package model.DataBaseConnection.ConnectJobProfile

import model.DataBaseConnection.DBMain
import model.DataBaseConnection.Objects.{Candidate, JobProfile}

/**
  * Created by Casper on 10/07/2017.
  */
object DBJobProfile {

  // JobProfile DB methods

  val db = DBMain

  def addJobProfile(jobTitle: String, educationName: String,
                    educationLevelID: String, experienceLevelID: String): Unit = {

    //  JobProfile(JobProfileID INT, JobTitle TEXT, EducationName TEXT, EducationLevelID INT, ExperienceLevelID INT);

    db.connect()

    val maxID = db.getLatestId("JobProfile")

    val stmt = db.connection.prepareStatement("INSERT INTO JobProfile VALUES (?,?,?,?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, jobTitle)

    stmt.setString(3, educationName)

    stmt.setString(4, educationLevelID)

    stmt.setString(5, experienceLevelID)


    stmt.executeUpdate

    db.closeConnection()

  }

  def getJobProfileByID(jobProfilID: Int): Option[JobProfile] = {

    var toReturn: Option[JobProfile] = None

    db.connect()

    val selectSQL =
      """SELECT *
        |FROM JobProfile JOIN EducationLevel USING(EducationLevelID)
        |JOIN ExperienceLevel USING(ExperienceLevelID)
        |WHERE JobProfileID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, jobProfilID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      val ID = rs.getString("JobProfileID")
      val jobTitle = rs.getString("JobTitle")
      val educationName = rs.getString("EducationName")
      val educationLevel = rs.getString("EducationLevel.Name")
      val experienceLevel = rs.getString("ExperienceLevel.Name")

      toReturn = Some(JobProfile(ID.toInt, jobTitle, educationName, educationLevel, experienceLevel))

    }

    db.closeConnection()

    toReturn

  }

  def getAllJobDescriptions(): List[JobProfile] = {

    var toReturn: List[JobProfile] = Nil

    db.connect()

    val selectSQL =
      """SELECT *
        |FROM JobProfile JOIN EducationLevel USING(EducationLevelID)
        |JOIN ExperienceLevel USING(ExperienceLevelID)""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()


    while (rs.next()) {

      val ID = rs.getString("JobProfileID")
      val jobTitle = rs.getString("JobTitle")
      val educationName = rs.getString("EducationName")
      val educationLevel = rs.getString("EducationLevel.Name")
      val experienceLevel = rs.getString("ExperienceLevel.Name")

      toReturn = toReturn :+ JobProfile(ID.toInt, jobTitle, educationName, educationLevel, experienceLevel)

    }

    db.closeConnection()

    toReturn

  }


}
