package model

import org.scalatest.FunSuite

/**
  * Created by Casper on 16/07/2017.
  */
class matchingMethods$Test extends FunSuite {

  val matchingMethods = new matchingMethods(6)

  test("matchingOnOneSkillSorted"){

    //val matching = matchingMethods.matchingOnOneSkill(1)

    //println(matching)


  }

  test("skillMatching"){


    println(matchingMethods.skillMatching())


  }


  test("competencyMatching"){
    println(matchingMethods.competencyMatching())

  }

  test("overAllMatching"){

    println(matchingMethods.overAllMatching())


  }
  test("returnAllResults"){
    println(matchingMethods.returnAllResults())

  }





}
