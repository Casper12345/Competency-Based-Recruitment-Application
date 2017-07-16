package persistenceAPI.DataBaseConnection.ConnectJobProfile

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

    jobProfile.competencies.foreach(a => println(a.name))
    jobProfile.skills.foreach(a => println(a.name))

  }

  test("getAllJobDescriptions") {

    val jobProfile = db.getAllJobDescriptions()

    jobProfile.foreach(a => println(a.jobTitle))
    jobProfile.foreach(a => a.competencies.foreach(b => println(b.name)))
    jobProfile.foreach(a => a.skills.foreach(b => println(b.name)))


  }

  test("jobDescriptionGetSkills") {

    val jobProfile = db.jobDescriptionGetSkills(1)
    jobProfile.foreach(a => println(a.name))

  }

  test("jobDescriptionGetCompetencies") {

    val jobProfile = db.jobDescriptionGetCompetencies(2)
    jobProfile.foreach(a => println(a.name))

  }
}
