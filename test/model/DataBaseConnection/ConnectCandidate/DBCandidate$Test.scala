package model.DataBaseConnection.ConnectCandidate

import model.DataBaseConnection.DBMain
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
    candidate.foreach(a => println(a.name))

  }

  test("candidateGetCompetencies") {

    val candidate = db.candidateGetCompetencies(1)
    candidate.foreach(a => println(a.name))

  }

  test("getAllCandidates") {

    val candidates = db.getAllCandidates()

    candidates.foreach(a => println(a.name))

    candidates.foreach(a => a.competencies.foreach(b => println(b)))
  }

}
