package model.matchingLogic

/**
  * Trait for similarity algorithms.
  */
trait SimilarityFacade {

  /**
    * Method for similarity logic
    *
    * @param vector1 required vector
    * @param vector2 vector from candidates
    * @return similarity as double
    */
  def similarity(vector1: List[Int], vector2: List[Int]): Double

}
