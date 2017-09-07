package model.matchingLogic.candidatesSorting

import model.persistenceAPIInterface.matchingPersistence.MatchingQueriesPersistenceFacade
import persistenceAPI.DataBaseConnection.objects.{Candidate, JobDescriptionCompetency, JobDescriptionSkill}
import persistenceAPI.DataBaseConnection.sqlQueries.DBQueries

/**
  * Created by Casper on 08/08/2017.
  */
case class CandidatesSortedByLockedAttributes() extends CandidatesSortingTrait {


  var lockedJobDescriptionSkills: List[JobDescriptionSkill] = _
  var lockedJobDescriptionCompetencies: List[JobDescriptionCompetency] = _

  def setAttributes(lockedJobDescriptionSkills: List[JobDescriptionSkill],
                    lockedJobDescriptionCompetencies: List[JobDescriptionCompetency]): Unit = {

    this.lockedJobDescriptionSkills = lockedJobDescriptionSkills
    this.lockedJobDescriptionCompetencies = lockedJobDescriptionCompetencies

  }

  override def returnCandidatesByJobDescriptionID(jobDescriptionID: Int): List[Candidate] = {

    val matchingQueriesFacade = MatchingQueriesPersistenceFacade

    val oneSkillMatch = matchingQueriesFacade.getMatchingCandidatesOneSkillByJobID(jobDescriptionID).map(a => a._1)

    println(oneSkillMatch)
    var listToReturn: List[Candidate] = Nil


    for (i <- oneSkillMatch) {

      var includeCandidateSkill = false
      var includeCandidateCompetency = false

      if (lockedJobDescriptionSkills.isEmpty && lockedJobDescriptionCompetencies.nonEmpty) {
        includeCandidateSkill = true
      }

      if (lockedJobDescriptionCompetencies.isEmpty && lockedJobDescriptionSkills.nonEmpty) {
        includeCandidateCompetency = true
      }

      val skillsOfCandidate = i.skills
      val competenciesOfCandidate = i.competencies

      // loop and compare skills
      for (j <- lockedJobDescriptionSkills) {

        var helperBoolean = false

        for (k <- skillsOfCandidate) {

          if (j.skillID == k.skillID && j.rating == k.rating) {
            helperBoolean = true
          }
        }


        includeCandidateSkill = helperBoolean

      }


      //loop and compare competencies
      for (j <- lockedJobDescriptionCompetencies) {

        var helperBoolean = false

        for (k <- competenciesOfCandidate) {

          if (j.competencyID == k.competencyID && j.rating == k.rating) {
            helperBoolean = true
          }

        }

        includeCandidateCompetency = helperBoolean

      }

      if (includeCandidateSkill && includeCandidateCompetency) {
        listToReturn = listToReturn :+ i

      }
    }


    listToReturn
  }

}
