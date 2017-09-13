package model.matchingLogic

/**
  * Facade class for uncapped similarity.
  */
case class UnCappedSimilarityFacade() extends SimilarityFacade {

  /**
    * Composition similarity algorithm.
    */
  val simAlg = SimilarityAlgorithm

  /**
    *
    * @param vector1 required vector
    * @param vector2 vector from candidates
    * @return similarity as double
    */
  override def similarity(vector1: List[Int], vector2: List[Int]): Double =
    simAlg.unCappedDistSimilarity(vector1, vector2)

}
