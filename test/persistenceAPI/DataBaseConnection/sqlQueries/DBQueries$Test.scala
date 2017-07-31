package persistenceAPI.DataBaseConnection.sqlQueries

import org.scalatest.FunSuite

/**
  * Created by Casper on 12/07/2017.
  */
class DBQueries$Test extends FunSuite {

  test("testGetMatchingCandidatesOneSkillByJobID") {

    val db = DBQueries

    val r = db.getMatchingCandidatesOneSkillByJobID(4)

    r.foreach(a => println(a._1))
    r.foreach(a => println(a._2))

  }

  test("getMatchingCandidatesOneCompetencyByJobID") {

    val db = DBQueries

    val r = db.getMatchingCandidatesOneCompetencyByJobID(1)

    r.foreach(a => println(a._1))
    r.foreach(a => println(a._2))

  }


}
