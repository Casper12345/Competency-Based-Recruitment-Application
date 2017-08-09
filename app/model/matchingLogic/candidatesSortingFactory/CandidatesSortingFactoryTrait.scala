package model.matchingLogic.candidatesSortingFactory

import model.matchingLogic.SimilarityFacade

/**
  * Created by Casper on 08/08/2017.
  */
trait CandidatesSortingFactoryTrait {

  def factory(instance: String): SimilarityFacade

}
