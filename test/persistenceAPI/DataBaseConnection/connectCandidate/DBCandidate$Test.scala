package persistenceAPI.DataBaseConnection.connectCandidate

import persistenceAPI.DataBaseConnection.DBMain
import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBCandidate$Test extends FunSuite {

  // Candidate tests

  val db = DBCandidate

  test("addCandidate") {

    db.addCandidate("Paul", "Larson", "ComputerScience", "Programmer", "4", "2")

    val candidate = db.getCandidateByID(1).get

    assert(candidate.name == "Paul")
    assert(candidate.surname == "Larson")
    assert(candidate.currentJobTitle == "Programmer")
    assert(candidate.educationLevel == "Masters Level")


  }

  test("getCandidateByID") {

    val candidate = db.getCandidateByID(1).get


    assert(candidate.name == "Paul")
    assert(candidate.surname == "Larson")
    assert(candidate.currentJobTitle == "Programmer")
    candidate.skills.foreach(a => println(a.name))
  }

  test("candidateGetSkills") {

    val candidate = db.candidateGetSkills(1)
    assert(candidate.head.name == "C++")

  }

  test("candidateGetCompetencies") {

    val candidate = db.candidateGetCompetencies(3)
    candidate.foreach(a => println(a.name))
    assert(candidate.head.competencyID == 3)
  }

  test("getAllCandidates") {

    val candidates = db.getAllCandidates()

    assert(candidates.head.name == "Paul")

  }

  test("deleteCandidate"){

    val candidate = db.deleteCandidate(13)

    assert(db.getCandidateByID(13).isEmpty)

  }

}
