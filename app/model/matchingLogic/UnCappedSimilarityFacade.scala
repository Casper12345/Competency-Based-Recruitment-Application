package model.matchingLogic

/**
  * Created by Casper on 31/07/2017.
  */
case class UnCappedSimilarityFacade() extends SimilarityFacade {

  val simAlg = SimilarityAlgorithm

  override def similarity(vector1: List[Int], vector2: List[Int]): Double =
    simAlg.unCappedDistSimilarity(vector1, vector2)

}
