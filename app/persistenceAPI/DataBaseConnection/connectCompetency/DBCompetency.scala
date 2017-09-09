package persistenceAPI.DataBaseConnection.connectCompetency

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.objects.Competency

/**
  * DataBase methods for Competency table and handling request to Competency table.
  */
object DBCompetency {

  /**
    * Connection to main DB
    */
  val db = DBMain

  /**
    * Method for inserting into Competency table
    * SQL - Competency(CompetencyID INT, Name TEXT)
    *
    * @param name
    */
  def addCompetency(name: String): Unit = {

    db.connect()

    val maxID = db.getLatestId("Competency")

    val stmt = db.connection.prepareStatement("INSERT INTO Competency VALUES (?,?)")


    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)


    stmt.executeUpdate

    db.closeConnection()

  }

  /**
    * Method for getting Competency by ID
    *
    * @param CompetencyID
    * @return option of Competency
    */
  def getCompetencyByID(CompetencyID: Int): Option[Competency] = {

    db.connect()

    val selectSQL = "SELECT * FROM Competency WHERE CompetencyID = ?"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    preparedStatement.setInt(1, CompetencyID)

    val rs = preparedStatement.executeQuery()

    var toReturn: Option[Competency] = None

    while (rs.next()) {

      val ID = rs.getString("CompetencyID")
      val name = rs.getString("Name")
      toReturn = Some(Competency(ID.toInt, name))

    }


    db.closeConnection()

    toReturn

  }

  /**
    * Method for getting all Competencies
    *
    * @return list of all competencies in table
    */
  def getAllCompetencies(): List[Competency] = {

    var toReturn: List[Competency] = Nil

    db.connect()

    val selectSQL = "SELECT * FROM Competency"

    val preparedStatement = db.connection.prepareStatement(selectSQL)

    val rs = preparedStatement.executeQuery()

    while (rs.next()) {
      val ID = rs.getString("CompetencyID")
      val name = rs.getString("Name")

      toReturn = toReturn :+ Competency(ID.toInt, name)

    }

    db.closeConnection()

    toReturn

  }

  /**
    * Method for deleting competency by ID
    *
    * @param competencyID competency id
    */
  def deleteCompetency(competencyID: Int): Unit = {
    db.connect()

    val deleteSQL = "DELETE FROM Competency WHERE CompetencyID = ?"

    val preparedStatement = db.connection.prepareStatement(deleteSQL)

    preparedStatement.setInt(1, competencyID)

    preparedStatement.executeUpdate

    db.closeConnection()

  }

}
