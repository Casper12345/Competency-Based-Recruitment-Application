package model.DataBaseConnection.ConnectCandidate

import model.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBCandidate$Test extends FunSuite {

  // Candidate tests

  val db = DBCandidate

  test("addCandidate"){

    db.addCandidate("Paul", "Larson", "ComputerScience","Programmer", "4", "2")

    val candidate = db.getCandidateByID(1).get

    assert(candidate.name == "Paul")
    assert(candidate.surname == "Larson")
    assert(candidate.currentJobTitle == "Programmer")
    assert(candidate.educationLevel == "Masters Level")


  }

  test("getCandidateByID"){

    val candidate = db.getCandidateByID(1).get


    assert(candidate.name == "Paul")
    assert(candidate.surname == "Larson")
    assert(candidate.currentJobTitle == "Programmer")
    println(candidate.educationLevel)
  }

}
