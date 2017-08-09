package model.matchingLogic.candidatesSortingFactory

import model.matchingLogic.candidatesSorting.{CandidatesSortedByLockedAttributes, CandidatesSortedByOneSkill, CandidatesSortingTrait}

/**
  * Created by Casper on 08/08/2017.
  */
object CandidateSortingFactory {

  def factory(instance: String): CandidatesSortingTrait = instance match {

    case "candidatesSortedByOneSkill" => CandidatesSortedByOneSkill()
    case "candidatesSortedByLockedAttributes" => CandidatesSortedByLockedAttributes()
  }

}
