package model.matchingLogic.AlgorithmFactory

import model.matchingLogic.SimilarityFacade

/**
  * Created by Casper on 31/07/2017.
  */
trait AlgorithmFactoryTrait {
  def factory(instance: String): SimilarityFacade
}
