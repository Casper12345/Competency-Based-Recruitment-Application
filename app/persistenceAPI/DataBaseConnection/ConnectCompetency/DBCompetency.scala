package persistenceAPI.DataBaseConnection.ConnectCompetency

import persistenceAPI.DataBaseConnection.DBMain
import persistenceAPI.DataBaseConnection.Objects.Competency

/**
  * Created by Casper on 10/07/2017.
  */
object DBCompetency {

  // Competency DB methods

  val db = DBMain

  def addCompetency(name: String): Unit = {

    //Competency(CompetencyID INT, Name TEXT);

    db.connect()

    val maxID = db.getLatestId("Competency")

    val stmt = db.connection.prepareStatement("INSERT INTO Competency VALUES (?,?)")


    stmt.setString(1, (maxID + 1).toString)

    stmt.setString(2, name)


    stmt.executeUpdate

    db.closeConnection()

  }

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

  // get all Competencies

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

}
