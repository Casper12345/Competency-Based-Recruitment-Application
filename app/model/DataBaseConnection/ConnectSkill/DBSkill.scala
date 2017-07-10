package model.DataBaseConnection.ConnectSkill

import model.DataBaseConnection.DBMain
import model.DataBaseConnection.Objects.Skill

/**
  * Created by Casper on 10/07/2017.
  */
object DBSkill {
  // Skill DB methods

  val db = DBMain

  def addSkill(name: String): Unit = {

    //Skill(SkillID INT, Name TEXT);

    db.connect()

    val maxID = db.getLatestId("Skill")

    val stmt = db.connection.prepareStatement("INSERT INTO Skill VALUES (?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)

    stmt.executeUpdate

    db.closeConnection()

  }


  def getSkillByID(SkillID: Int): Option[Skill] = {

    db.connect()

    val selectSQL = "SELECT * FROM Skill WHERE SkillID = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, SkillID)

    val rs = preparedStatement.executeQuery()

    var toReturn: Option[Skill] = None

    while (rs.next()) {

      val skillID = rs.getString("SkillID")
      val name = rs.getString("Name")

      toReturn = Some(Skill(skillID.toInt, name))

    }

    db.closeConnection()

    toReturn

  }

  // get all skills

  def getAllSkills(): List[Skill] = {

    var toReturn: List[Skill] = Nil

    db.connect()

    val selectSQL = "SELECT * FROM Skill"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {

      val ID = rs.getString("SkillID")
      val name = rs.getString("Name")

      toReturn = toReturn :+ Skill(ID.toInt, name)
    }

    db.closeConnection()

    toReturn

  }


}
