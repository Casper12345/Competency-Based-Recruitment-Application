package model.DataBaseConnection.SqlQueries

import org.scalatest.FunSuite

/**
  * Created by Casper on 12/07/2017.
  */
class DBQueries$Test extends FunSuite {

  test("testGetMatchingCandidatesOneSkillByJobID") {

    val db = DBQueries

    val r = db.getMatchingCandidatesOneSkillByJobID(1)

    r.foreach(a => println(a._1))
    r.foreach(a => println(a._2))

  }

}
