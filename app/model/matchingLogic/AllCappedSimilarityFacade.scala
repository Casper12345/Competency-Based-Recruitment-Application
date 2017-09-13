package model.matchingLogic

/**
  *
  */
case class AllCappedSimilarityFacade() extends SimilarityFacade {

  val simAlg: SimilarityAlgorithm.type = SimilarityAlgorithm

  /**
    * Facade for all capped similarity algorithm
    *
    * @param vector1 vector 1
    * @param vector2 vector 2
    * @return Double of matching in percent.
    */
  override def similarity(vector1: List[Int], vector2: List[Int]): Double =
    simAlg.cappedDistSimilarity(vector1, vector2)

}
