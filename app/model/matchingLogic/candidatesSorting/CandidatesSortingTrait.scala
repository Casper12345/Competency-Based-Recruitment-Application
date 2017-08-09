package model.matchingLogic.candidatesSorting

import persistenceAPI.DataBaseConnection.objects.Candidate

/**
  * Created by Casper on 08/08/2017.
  */
trait CandidatesSortingTrait {

  def returnCandidatesByJobDescriptionID(jobDescriptionID: Int): List[Candidate]

}
