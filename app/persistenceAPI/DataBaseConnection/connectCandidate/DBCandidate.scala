package persistenceAPI.DataBaseConnection.connectCandidate

import controllers.MainApp
import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.objects.{Candidate, CandidateCompetency, CandidateSkill, Skill}

/**
  * DataBase methods for Candidate ltable and handling request to Candidate table.
  */
object DBCandidate {

  /**
    * Connecting to main DB
    */
  var db = DBMain


  /**
    * Method for inserting into table Candidate
    *
    * @param name
    * @param surname
    * @param educationName
    * @param currentJobTitle
    * @param educationLevelID
    * @param experienceLevelID
    */
  def addCandidate(name: String, surname: String, educationName: String, currentJobTitle: String,
                   educationLevelID: String, experienceLevelID: String): Unit = {

    db.connect()

    val maxID = db.getLatestId("Candidate")

    val stmt = db.connection.prepareStatement("INSERT INTO Candidate VALUES (?,?,?,?,?,?,?)")


    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)

    stmt.setString(3, surname)

    stmt.setString(4, educationName)

    stmt.setString(5, currentJobTitle)

    stmt.setString(6, educationLevelID)

    stmt.setString(7, experienceLevelID)

    stmt.executeUpdate

    db.closeConnection()

  }

  /**
    * Method for getting skill for specific candidate
    * Method is used by get candidate methods
    *
    * @param CandidateID
    * @return List of candidate skills
    */
  def candidateGetSkills(CandidateID: Int): List[CandidateSkill] = {
    db.connect()

    val selectSQL =
      """SELECT CandidateSkill.SkillID, CandidateSkill.Rating, Skill.Name
        |FROM Candidate
        |JOIN CandidateSkill USING(CandidateID)
        |JOIN Skill USING (SkillID)
        |WHERE CandidateID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CandidateID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[CandidateSkill] = Nil

    while (rs.next()) {

      val ID = rs.getString("CandidateSkill.SkillID")
      val name = rs.getString("Skill.Name")
      val rating = rs.getString("CandidateSkill.Rating")

      toReturn = toReturn :+ CandidateSkill(ID.toInt, name, rating.toInt)
    }


    db.closeConnection()

    toReturn


  }

  /**
    * Method for getting competencies for specific candidates.
    * Method is used by get candidate methods
    *
    * @param CandidateID
    * @return List of CandidatesCompetencies
    */
  def candidateGetCompetencies(CandidateID: Int): List[CandidateCompetency] = {

    db.connect()

    val selectSQL =
      """SELECT CandidateCompetency.CompetencyID, CandidateCompetency.Rating, Competency.Name
        |FROM Candidate
        |JOIN CandidateCompetency USING(CandidateID)
        |JOIN Competency USING (CompetencyID)
        |WHERE CandidateID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CandidateID)

    val rs = preparedStatement.executeQuery()

    var toReturn: List[CandidateCompetency] = Nil

    while (rs.next()) {

      val ID = rs.getString("CandidateCompetency.CompetencyID")
      val name = rs.getString("Competency.Name")
      val rating = rs.getString("CandidateCompetency.Rating")

      toReturn = toReturn :+ CandidateCompetency(ID.toInt, name, rating.toInt)
    }


    db.closeConnection()

    toReturn


  }

  /**
    * Main method for getting candidates by ID.
    *
    * @param CandidateID
    * @return Option of Candidate
    */
  def getCandidateByID(CandidateID: Int): Option[Candidate] = {

    db.connect()

    val selectSQL =
      """SELECT *
        |FROM Candidate JOIN EducationLevel USING(EducationLevelID)
        |JOIN ExperienceLevel USING(ExperienceLevelID)
        |WHERE CandidateID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CandidateID)

    val rs = preparedStatement.executeQuery()

    var toReturn: Option[Candidate] = None

    val candidateSkills = candidateGetSkills(CandidateID)

    val candidateCompetencies = candidateGetCompetencies(CandidateID)

    while (rs.next()) {

      val ID = rs.getString("CandidateID")
      val name = rs.getString("Name")
      val surname = rs.getString("Surname")
      val educationName = rs.getString("EducationName")
      val currentJobTitle = rs.getString("CurrentJobTitle")
      val educationLevel = rs.getString("EducationLevel.Name")
      val experienceLevel = rs.getString("ExperienceLevel.Name")

      toReturn = Some(Candidate(ID.toInt, name, surname,
        educationName, currentJobTitle, educationLevel, experienceLevel,
        candidateSkills, candidateCompetencies))
    }


    db.closeConnection()

    toReturn
  }

  /**
    * Main method for getting all canidates from Candidate Table.
    *
    * @return List of Candidates
    */
  def getAllCandidates(): List[Candidate] = {

    var toReturn: List[Candidate] = Nil

    db.connect()

    val selectSQL =
      """SELECT *
        |FROM Candidate JOIN EducationLevel USING(EducationLevelID)
        |JOIN ExperienceLevel USING(ExperienceLevelID)
        |ORDER BY CandidateID""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()


    while (rs.next()) {

      val ID = rs.getString("CandidateID")
      val name = rs.getString("Name")
      val surname = rs.getString("Surname")
      val educationName = rs.getString("EducationName")
      val currentJobTitle = rs.getString("CurrentJobTitle")
      val educationLevel = rs.getString("EducationLevel.Name")
      val experienceLevel = rs.getString("ExperienceLevel.Name")

      val candidateSkills = candidateGetSkills(ID.toInt)
      val candidateCompetencies = candidateGetCompetencies(ID.toInt)

      val candidate = Candidate(ID.toInt, name, surname,
        educationName, currentJobTitle, educationLevel,
        experienceLevel, candidateSkills, candidateCompetencies)

      toReturn = toReturn :+ candidate

    }

    db.closeConnection()

    toReturn

  }

}
