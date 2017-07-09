package model.DataBaseConnection

import org.scalatest.FunSuite

/**
  * Created by Casper on 06/07/2017.
  */
class DBMain$Test extends FunSuite {

  // utilities tests

  test("getLatestId"){

    val db = DBMain

    db.connect()

    assert(db.getLatestId("Candidate") == 1)

    assert(db.getLatestId("Candidate") != 2)

    db.closeConnection()

  }

  // Candidate tests


  test("addCandidate"){

    val db = DBMain

    db.addCandidate("Paul", "Larson", "Programmer","ComputerScience", "4", "2")

    val getCandidate = db.getCandidateByID(2)

    val name = getCandidate(1)

    val surname = getCandidate(2)

    val currrentJobTitle = getCandidate(3)


    assert(name == "Paul")
    assert(surname == "Larson")
    assert(currrentJobTitle == "Programmer")


  }

  test("getCandidateByID"){

    val db = DBMain

    val candidate = db.getCandidateByID(2)

    val name = candidate(1)

    val surname = candidate(2)

    val currentJobTitle = candidate(3)

    assert(name == "Paul")
    assert(surname == "Larson")
    assert(currentJobTitle == "Programmer")

  }

  // Competency tests

  test("addCompetency"){

    val db = DBMain

    db.addCompetency("Leadership")

    val competency = db.getCompetencyByID(1)

    assert(competency(1) == "Leadership")

  }

  test("getCompetencyByID"){

    val db = DBMain

    val competency = db.getCompetencyByID(1)

    val ID = competency.head

    val name = competency(1)

    assert(ID == "1")

    assert(name == "Leadership")

    //competency.foreach(a => println(a))


  }

  // Skill tests

  test("addSkill"){

    val db = DBMain

    db.addSkill("C++")

    val competency = db.getSkillByID(1)

    assert(competency(1) == "C++")

  }

  test("getSkillByID"){

    val db = DBMain

    val skill = db.getSkillByID(1)

    val ID = skill.head

    val name = skill(1)

    assert(ID == "1")

    assert(name == "C++")

    skill.foreach(a => println(a))


  }

  // EducationLevel tests

  test("addEducationLevel"){

    val db = DBMain

    db.addEducationLevel("0", "No Formal Education")

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

  test("getEducationLevelByID"){

    val db = DBMain

    val educationLevel = db.getEducationLevelByID(1)

    val level = educationLevel(1)

    val name  = educationLevel(2)


    assert(level == "0")

    assert(name == "No Formal Education")

    educationLevel.foreach(a => println(a))


  }


  // ExperienceLevel Tests

  test("addExperienceLevel"){

    val db = DBMain

    db.addExperienceLevel("0", "No Experience")

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

  test("getExperienceLevelByID"){

    val db = DBMain

    val experienceLevel = db.getExperienceLevelByID(1)

    val level = experienceLevel(1)

    val name  = experienceLevel(2)


    assert(level == "0")

    assert(name == "No Experience")

    experienceLevel.foreach(a => println(a))


  }

  // CandidateCompetency Test

  test("addCandidateCompetency"){

    val db = DBMain

    db.addCandidateCompetency(1, 2, 1)

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

  // CandidateSkill Tests

  test("addCandidateSkill"){

    val db = DBMain

    db.addCandidateSkill(1, 5, 1)

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

  // JobProfile Tests

  test("addJobProfile"){

    val db = DBMain

    db.addJobProfile("Programmer")

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }


  test("getJobProfileByID"){

    val db = DBMain

    val jobProfile = db.getJobProfileByID(1)

    val ID = jobProfile.head

    val name  = jobProfile(1)


    assert(ID == "1")

    assert(name == "Programmer")

    jobProfile.foreach(a => println(a))


  }

  // JobProfileCompetency Tests

  test("addJobProfileCompetency"){

    val db = DBMain

    db.addJobProfileCompetency(1,1)

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

  // JobProfileSkill Test
  test("addJobProfileSkill"){

    val db = DBMain

    db.addJobProfileSkill(1,1)

    //val competency = db.getSkillByID(1)

    //assert(competency(1) == "Leadership")

  }

  // getAllSkills Test

  test("getAllSkill"){
    val db = DBMain

    val skills = db.getAllSkills()

    skills.foreach(a => println(a))

  }

  test("getAllCompetency"){
    val db = DBMain

    val skills = db.getAllCompetencies()

    skills.foreach(a => println(a))

  }



}
