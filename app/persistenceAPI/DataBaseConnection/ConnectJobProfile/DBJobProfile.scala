package persistenceAPI.DataBaseConnection.ConnectJobProfile

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.Objects._

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


  def jobDescriptionGetSkills(CandidateID: Int): List[JobDescriptionSkill] = {

    db.connect()

    val selectSQL =
      """SELECT JobProfileSkill.SkillID, JobProfileSkill.Rating, Skill.Name
        |FROM JobProfile
        |JOIN JobProfileSkill USING(JobProfileID)
        |JOIN Skill USING (SkillID)
        |WHERE JobProfileID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CandidateID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[JobDescriptionSkill] = Nil

    while (rs.next()) {

      val ID = rs.getString("JobProfileSkill.SkillID")
      val name = rs.getString("Skill.Name")
      val rating = rs.getString("JobProfileSkill.Rating")

      toReturn = toReturn :+ JobDescriptionSkill(ID.toInt, name, rating.toInt)
    }


    db.closeConnection()

    toReturn


  }


  def jobDescriptionGetCompetencies(CandidateID: Int): List[JobDescriptionCompetency] = {
    db.connect()

    val selectSQL =
      """SELECT JobProfileCompetency.CompetencyID, JobProfileCompetency.Rating, Competency.Name
        |FROM JobProfile
        |JOIN JobProfileCompetency USING(JobProfileID)
        |JOIN Competency USING (CompetencyID)
        |WHERE JobProfileID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CandidateID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[JobDescriptionCompetency] = Nil

    while (rs.next()) {

      val ID = rs.getString("JobProfileCompetency.CompetencyID")
      val name = rs.getString("Competency.Name")
      val rating = rs.getString("JobProfileCompetency.Rating")

      toReturn = toReturn :+ JobDescriptionCompetency(ID.toInt, name, rating.toInt)
    }


    db.closeConnection()

    toReturn


  }


  def getJobProfileByID(jobProfileID: Int): Option[JobDescription] = {

    var toReturn: Option[JobDescription] = None

    db.connect()

    val selectSQL =
      """SELECT *
        |FROM JobProfile JOIN EducationLevel USING(EducationLevelID)
        |JOIN ExperienceLevel USING(ExperienceLevelID)
        |WHERE JobProfileID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, jobProfileID)

    val rs = preparedStatement.executeQuery()

    val skills = jobDescriptionGetSkills(jobProfileID)

    val competencies = jobDescriptionGetCompetencies(jobProfileID)

    while (rs.next()) {

      val ID = rs.getString("JobProfileID")
      val jobTitle = rs.getString("JobTitle")
      val educationName = rs.getString("EducationName")
      val educationLevel = rs.getString("EducationLevel.Name")
      val experienceLevel = rs.getString("ExperienceLevel.Name")

      toReturn = Some(JobDescription(ID.toInt, jobTitle, educationName, educationLevel, experienceLevel,
        skills, competencies))

    }

    db.closeConnection()

    toReturn

  }

  def getAllJobDescriptions(): List[JobDescription] = {

    var toReturn: List[JobDescription] = Nil

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

      val skills = jobDescriptionGetSkills(ID.toInt)

      val competencies = jobDescriptionGetCompetencies(ID.toInt)

      toReturn = toReturn :+ JobDescription(ID.toInt, jobTitle,
        educationName, educationLevel, experienceLevel, skills, competencies)

    }

    db.closeConnection()

    toReturn

  }


}
