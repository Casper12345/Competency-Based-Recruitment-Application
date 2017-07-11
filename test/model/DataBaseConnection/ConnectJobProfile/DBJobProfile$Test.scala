package model.DataBaseConnection.ConnectJobProfile

import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBJobProfile$Test extends FunSuite {

  // JobProfile Tests

  val db = DBJobProfile

  test("addJobProfile") {

    db.addJobProfile("Programmer", "Biology", "1", "1")

  }


  test("getJobProfileByID") {


    val jobProfile = db.getJobProfileByID(1).get


    assert(jobProfile.jobTitle == "Programmer")

    assert(jobProfile.educationName == "Biology")

    println(jobProfile.educationLevel)
    println(jobProfile.experienceLevel)


  }

  test("getAllJobDescriptions") {


    val jobProfile = db.getAllJobDescriptions()

    jobProfile.foreach(a => println(a.jobTitle))
  }
}
