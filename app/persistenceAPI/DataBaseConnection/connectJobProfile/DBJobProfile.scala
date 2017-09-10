package persistenceAPI.DataBaseConnection.connectJobProfile

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.objects._

/**
  * DataBase methods for JobProfile table and handling request to JobProfile table.
  */
object DBJobProfile {

  /**
    * Connect to main DB.
    */
  val db = DBMain

  /**
    * Method for inserting into JobProfile table
    * SQL - jobProfile(JobProfileID INT, JobTitle TEXT, EducationName TEXT, EducationLevelID INT, ExperienceLevelID INT);
    *
    * @param jobTitle
    * @param educationName
    * @param educationLevelID
    * @param experienceLevelID
    */
  def addJobProfile(jobTitle: String, educationName: String,
                    educationLevelID: String, experienceLevelID: String): Unit = {


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

  /**
    * Method for getting skills pertaining to a specific jobDescription
    * Used by jobProfile getter methods to aid with joins
    *
    * @param CandidateID
    * @return
    */
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

  /**
    * Method for getting competencies  pertaining to a specific jobDescription
    * Used by jobProfile getter methods to aid with joins
    *
    * @param CandidateID
    * @return
    */
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

  /**
    * Main method for getting jobProfiles by ID
    *
    * @param jobProfileID
    * @return Option of JobDescription
    */
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

    // gets skills
    val skills = jobDescriptionGetSkills(jobProfileID)

    // gets competencies
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

  /**
    * Main method for getting all jobDescription
    *
    * @return Full list of job descriptions
    */
  def getAllJobDescriptions(): List[JobDescription] = {

    var toReturn: List[JobDescription] = Nil

    db.connect()

    val selectSQL =
      """SELECT *
        |FROM JobProfile JOIN EducationLevel USING(EducationLevelID)
        |JOIN ExperienceLevel USING(ExperienceLevelID)
        |ORDER BY JobProfileID""".stripMargin

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

  /**
    * Method for deleting job description
    *
    * @param jobDescriptionID job description id
    */
  def deleteJobDescription(jobDescriptionID: Int): Unit = {

    db.connect()

    val deleteSQL = "DELETE FROM JobProfile WHERE JobProfileID = ?"

    val preparedStatement = db.connection.prepareStatement(deleteSQL)

    preparedStatement.setInt(1, jobDescriptionID)

    preparedStatement.executeUpdate

    db.closeConnection()

  }

}
