package model

import org.scalatest.FunSuite

/**
  * Created by Casper on 14/07/2017.
  */
class similarityAlgorithm$Test extends FunSuite {

  test("maxDistCal") {

    val similarity = similarityAlgorithm

    assert(similarity.maxDistCal(List[Int](1,2,3,4)) == 10.0)

    println(similarity.maxDistCal(List[Int](1,2,3,4)))
  }

  test("euclideanDistance") {

    val similarity = similarityAlgorithm

    assert(similarity.euclideanDistance(List(1,3,2,5), List(1,2,3,4)) == 1.7320508075688772)

  }

  test("distSim") {

    val similarity = similarityAlgorithm

    //println(similarity.distSim(List(1,2,3,4,5,1,2,3,4,5,1,2,3,4,5), List(1,2,3,4,5,2,2,3,4,5,1,2,3,4,5)))

    println(similarity.distSim(List(1,1,1,1,1), List(5,2,3,4,5)))
  }


}
