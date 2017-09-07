package model

import model.matchingLogic.SimilarityAlgorithm
import org.scalatest.FunSuite

/**
  * Created by Casper on 14/07/2017.
  */
class similarityAlgorithm$Test extends FunSuite {

  test("maxDistCal") {

    val similarity = SimilarityAlgorithm

    assert(similarity.maxDistCal(List[Int](1,2,3,4)) == 5.477225575051661)

  }

  test("euclideanDistance") {

    val similarity = SimilarityAlgorithm

    assert(similarity.euclideanDistance(List(1,3,2,5), List(1,2,3,4)) == 1.7320508075688772)

  }

  test("distSim") {

    val similarity = SimilarityAlgorithm

    assert(similarity.cappedDistSimilarity(List(1,5,3), List(1,0,0)) == 1.4389239390837645)
  }


}
