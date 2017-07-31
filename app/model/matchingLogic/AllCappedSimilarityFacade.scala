package model.matchingLogic

/**
  *
  */
case class AllCappedSimilarityFacade() extends SimilarityFacade {

  val simAlg: SimilarityAlgorithm.type = SimilarityAlgorithm

  override def similarity(vector1: List[Int], vector2: List[Int]): Double =
    simAlg.cappedDistSimilarity(vector1, vector2)

}
