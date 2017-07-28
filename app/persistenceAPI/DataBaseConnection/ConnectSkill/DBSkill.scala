package persistenceAPI.DataBaseConnection.ConnectSkill

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.Objects.Skill

/**
  * DataBase methods for Skills table and handling request to Skill table
  */
object DBSkill {

  /**
    * Main database connection by composition
    */
  val db = DBMain

  /**
    * Method for adding skills to table Skill
    * SQL - Skill(SkillID INT, Name TEXT);
    *
    * @param name
    */
  def addSkill(name: String): Unit = {


    db.connect()

    val maxID = db.getLatestId("Skill")

    val stmt = db.connection.prepareStatement("INSERT INTO Skill VALUES (?,?)")

    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)

    stmt.executeUpdate

    db.closeConnection()

  }

  /**
    * Method for getting skill by ID
    *
    * @param SkillID
    * @return
    */
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


  /**
    * Method for getting full list of skills
    *
    * @return
    */
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
