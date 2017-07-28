package persistenceAPI.DataBaseConnection.ConnectExperienceLevel

import persistenceAPI.DataBaseConnection.DBMain

/**
  * DataBase methods for ExperienceLevel table and handling request to ExperienceLevel table.
  */
object DBExperienceLevel {

  /**
    * Connect to main DB
    */
  val db = DBMain

  /**
    * Method for inserting into ExperienceLevel table
    * SQL - ExperienceLevel(ExperienceLevelID INT, Level INT, Name TEXT)
    *
    * @param level
    * @param name
    */
  def addExperienceLevel(level: String, name: String): Unit = {

    db.connect()

    val maxID = db.getLatestId("ExperienceLevel")

    val stmt = db.connection.prepareStatement("INSERT INTO ExperienceLevel VALUES (?,?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, level)

    stmt.setString(3, name)

    stmt.executeUpdate

    db.closeConnection()

  }

  /**
    * Method for getting experienceLevel by ID
    *
    * @param SkillID
    * @return
    */
  def getExperienceLevelByID(SkillID: Int): List[String] = {

    var toReturn: List[String] = Nil

    db.connect()

    val selectSQL = "SELECT * FROM ExperienceLevel WHERE ExperienceLevelID = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, SkillID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      toReturn = toReturn :+ rs.getString("ExperienceLevelID")
      toReturn = toReturn :+ rs.getString("Level")
      toReturn = toReturn :+ rs.getString("Name")

    }

    db.closeConnection()

    toReturn

  }

}
