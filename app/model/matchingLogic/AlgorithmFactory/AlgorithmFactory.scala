package model.matchingLogic.AlgorithmFactory

import model.matchingLogic.{SimilarityFacade, UnCappedSimilarityFacade, AllCappedSimilarityFacade}

/**
  * Factory method for manual dependency injection
  */
object AlgorithmFactory extends Factory{

  def factory(instance: String): SimilarityFacade = instance match {

    case "allCappedSimilarityFacade" => AllCappedSimilarityFacade()
    case "unCappedSimilarityFacade" => UnCappedSimilarityFacade()

  }

}
