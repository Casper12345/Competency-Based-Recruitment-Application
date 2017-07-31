package model.matchingLogic

/**
  * Trait for similarity algorithms
  */
trait SimilarityFacade {

  def similarity(vector1: List[Int], vector2: List[Int]): Double

}
