package model.matchingLogic.AlgorithmFactory

import model.matchingLogic.SimilarityFacade

/**
  * Trait for AlgorithmFactory.
  */
trait AlgorithmFactoryTrait {
  def factory(instance: String): SimilarityFacade
}
