package model.DataBaseConnection.ConnectCandidate

import model.DataBaseConnection.DBMain
import model.DataBaseConnection.Objects.Candidate

/**
  * Created by Casper on 10/07/2017.
  */
object DBCandidate {

  // methods for Candidate table

  var db = DBMain

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

  def getCandidateByID(CandidateID: Int): Option[Candidate] = {

    db.connect()

    //mysql> select * from Candidate join EducationLevel using (EducationLevelID) join ExperienceLevel using(ExperienceLevelID);


    val selectSQL =
      """SELECT *
        |FROM Candidate JOIN EducationLevel USING(EducationLevelID)
        |JOIN ExperienceLevel USING(ExperienceLevelID)
        |WHERE CandidateID = ?""".stripMargin

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CandidateID)

    val rs = preparedStatement.executeQuery()

    var toReturn: Option[Candidate] = None

    while (rs.next()) {

      val ID = rs.getString("CandidateID")
      val name = rs.getString("Name")
      val surname = rs.getString("Surname")
      val educationName = rs.getString("EducationName")
      val currentJobTitle = rs.getString("CurrentJobTitle")
      val educationLevel = rs.getString("EducationLevel.Name")
      val experienceLevel = rs.getString("ExperienceLevel.Name")

      toReturn = Some(Candidate(ID.toInt, name, surname,
        educationName, currentJobTitle, educationLevel, experienceLevel))
    }


    db.closeConnection()

    toReturn
  }


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

      val candidate = Candidate(ID.toInt, name, surname,
        educationName, currentJobTitle, educationLevel,
        experienceLevel)

      toReturn = toReturn :+ candidate

    }

    db.closeConnection()

    toReturn

  }

}
