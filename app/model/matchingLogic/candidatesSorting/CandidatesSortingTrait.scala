package model.matchingLogic.candidatesSorting

import persistenceAPI.DataBaseConnection.objects.Candidate

/**
  * Trait for Candidate Sorting.
  */
trait CandidatesSortingTrait {

  /**
    * Method for returning candidates by job description id.
    *
    * @param jobDescriptionID job description id.
    * @return list of candidates
    */
  def returnCandidatesByJobDescriptionID(jobDescriptionID: Int): List[Candidate]

}
