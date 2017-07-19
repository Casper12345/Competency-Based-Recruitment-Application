package model

import org.scalatest.FunSuite

/**
  * Created by Casper on 19/07/2017.
  */
class matchingMethodsFacade$Test extends FunSuite {


  test("getListOfMachingCandidatesFromDB"){

    val m = matchingMethodsFacade

    println(m.getListOfMachingCandidatesFromDB(1))


  }

}
