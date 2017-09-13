package model.matchingLogic.AlgorithmFactory

import model.matchingLogic.{SimilarityFacade, UnCappedSimilarityFacade, AllCappedSimilarityFacade}

/**
  * Factory Operation method for manual dependency injection.
  */
object AlgorithmFactory extends AlgorithmFactoryTrait {

  def factory(instance: String): SimilarityFacade = instance match {

    case "allCappedSimilarityFacade" => AllCappedSimilarityFacade()
    case "unCappedSimilarityFacade" => UnCappedSimilarityFacade()

  }
}
