package model.DataBaseConnection.ConnectJobProfile

import model.DataBaseConnection.DBMain

/**
  * Created by Casper on 10/07/2017.
  */
object DBJobProfile {

  // JobProfile DB methods

  val db = DBMain

  def addJobProfile(name: String): Unit = {

    //  JobProfile(JobProfileID INT, Name TEXT)

    db.connect()

    val maxID = db.getLatestId("JobProfile")

    val stmt = db.connection.prepareStatement("INSERT INTO JobProfile VALUES (?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)

    stmt.executeUpdate

    db.closeConnection()

  }

  def getJobProfileByID(SkillID: Int): List[String] = {

    var toReturn: List[String] = Nil

    db.connect()

    val selectSQL = "SELECT * FROM JobProfile WHERE JobProfileID = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, SkillID)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      toReturn = toReturn :+ rs.getString("JobProfileID")
      toReturn = toReturn :+ rs.getString("Name")

    }

    db.closeConnection()

    toReturn

  }


}
