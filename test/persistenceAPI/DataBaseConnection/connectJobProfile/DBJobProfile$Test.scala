package persistenceAPI.DataBaseConnection.connectJobProfile

import org.scalatest.FunSuite

/**
  * Created by Casper on 10/07/2017.
  */
class DBJobProfile$Test extends FunSuite {

  // JobProfile Tests

  val db = DBJobProfile

  test("addJobProfile") {

    db.addJobProfile("Programmer", "Biology", "1", "1")

    val jobProfile = db.getJobProfileByID(1).get

    assert(jobProfile.jobTitle == "Programmer")

  }


  test("getJobProfileByID") {

    val jobProfile = db.getJobProfileByID(1).get

    assert(jobProfile.jobTitle == "Programmer")

    assert(jobProfile.educationName == "Biology")


  }

  test("getAllJobDescriptions") {

    val jobProfile = db.getAllJobDescriptions()

    assert(jobProfile.head.jobTitle == "Programer")


  }

  test("jobDescriptionGetSkills") {

    val jobProfile = db.jobDescriptionGetSkills(1)
    assert(jobProfile.head.name == "C++")

  }

  test("jobDescriptionGetCompetencies") {

    val jobProfile = db.jobDescriptionGetCompetencies(2)
    jobProfile.foreach(a => println(a.name))
    assert(jobProfile.head.name == "Management")


  }

  test("deleteJobDescription"){

    val jobProfile = db.deleteJobDescription(18)

    assert(db.getJobProfileByID(18).isEmpty)

  }
}
