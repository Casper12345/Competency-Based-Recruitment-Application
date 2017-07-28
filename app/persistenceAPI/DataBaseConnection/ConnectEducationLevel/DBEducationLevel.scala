package persistenceAPI.DataBaseConnection.ConnectEducationLevel

import persistenceAPI.DataBaseConnection.DBMain

/**
  * DataBase methods for EducationLevel table and handling request to EducationLevel table.
  */
object DBEducationLevel {

  /**
    * Connect to main DB
    */
  var db = DBMain

  /**
    * Method for inserting into EducationLevel table.
    *
    * @param level
    * @param name
    */
  def addEducationLevel(level: String, name: String): Unit = {

    //EducationLevel(EducationLevelID INT, Level INT, Name TEXT);

    db.connect()

    val maxID = db.getLatestId("EducationLevel")

    val stmt = db.connection.prepareStatement("INSERT INTO EducationLevel VALUES (?,?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, level)

    stmt.setString(3, name)

    stmt.executeUpdate

    db.closeConnection()

  }

  /**
    * Method for getting EducationLevel by ID
    *
    * @param SkillID
    * @return List of educationLevel as String
    */
  def getEducationLevelByID(SkillID: Int): List[String] = {

    var toReturn: List[String] = Nil

    db.connect()

    val selectSQL = "SELECT * FROM EducationLevel WHERE EducationLevelID = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, SkillID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      toReturn = toReturn :+ rs.getString("EducationLevelID")
      toReturn = toReturn :+ rs.getString("Level")
      toReturn = toReturn :+ rs.getString("Name")

    }

    db.closeConnection()

    toReturn

  }

}
