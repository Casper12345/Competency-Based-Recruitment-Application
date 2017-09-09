package model

import model.matchingLogic.SimilarityAlgorithm
import org.scalatest.FunSuite

/**
  * Created by Casper on 14/07/2017.
  */
class similarityAlgorithm$Test extends FunSuite {

  test("maxDistCal") {

    val similarity = SimilarityAlgorithm

    assert(similarity.maxDistCal(List[Int](1, 2, 3, 4)) == 5.477225575051661)

  }

  test("euclideanDistance") {

    val similarity = SimilarityAlgorithm

    assert(similarity.euclideanDistance(List(1, 3, 2, 5), List(1, 2, 3, 4)) == 1.7320508075688772)

  }

  test("distSim") {

    val similarity = SimilarityAlgorithm

    assert(similarity.cappedDistSimilarity(List(1, 5, 3), List(1, 0, 0)) == 1.4389239390837645)
  }


  test("stressTestCappedSimilarity") {

    val similarity = SimilarityAlgorithm

    val gen = scala.util.Random

    var vec1 = List[Int]()

    var vec2 = List[Int]()

    for (a <- 0 until 100000) {
      val g = gen.nextInt(5)
      vec1 = vec1 :+ g
      vec2 = vec2 :+ g
    }

    assert(similarity.cappedDistSimilarity(vec1, vec2) == 100.0)

  }


  test("stressTestUnCappedSimilarity") {

    val similarity = SimilarityAlgorithm

    val gen = scala.util.Random

    var vec1 = List[Int]()

    var vec2 = List[Int]()

    for (a <- 0 until 100000) {
      val g = gen.nextInt(5)
      vec1 = vec1 :+ g
      vec2 = vec2 :+ g
    }

    assert(similarity.unCappedDistSimilarity(vec1, vec2) == 100.0)

  }


}
