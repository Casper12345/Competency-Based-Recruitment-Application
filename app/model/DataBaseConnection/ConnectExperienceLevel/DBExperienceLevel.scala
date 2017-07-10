package model.DataBaseConnection.ConnectExperienceLevel

import model.DataBaseConnection.DBMain

/**
  * Created by Casper on 10/07/2017.
  */
object DBExperienceLevel {

  // ExperienceLevel DB methods

  val db = DBMain

  def addExperienceLevel(level: String, name: String): Unit = {

    //ExperienceLevel(ExperienceLevelID INT, Level INT, Name TEXT);

    db.connect()

    val maxID = db.getLatestId("ExperienceLevel")

    val stmt = db.connection.prepareStatement("INSERT INTO ExperienceLevel VALUES (?,?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, level)

    stmt.setString(3, name)

    stmt.executeUpdate

    db.closeConnection()

  }

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
