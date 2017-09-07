package model.matchingLogic

import model.persistenceAPIInterface.matchingPersistence.MatchingQueriesPersistenceFacade
import persistenceAPI.DataBaseConnection.connectJobProfile.DBJobProfile
import persistenceAPI.DataBaseConnection.objects._
import persistenceAPI.DataBaseConnection.sqlQueries.DBQueries


/**
  * Methods for creating the vectors and using the matching algorithms.
  *
  * class takes job description as field.
  *
  * Using manual constructor based dependency injection
  *
  * Val jobDescription = Int
  */
class MatchingMethods(jobDescriptionID: Int, matchingMethod: SimilarityFacade, candidates: List[Candidate]) {

  var jobProfile: JobDescription = getJobDescriptionByID(jobDescriptionID)

  /**
    * Method for getting all candidates that match on one skill.
    *
    * @param jobDescriptionID
    * @return
    */
  def matchingOnOneSkill(jobDescriptionID: Int): List[Candidate] = {

    val matchingQueriesFacade = MatchingQueriesPersistenceFacade

    matchingQueriesFacade.getMatchingCandidatesOneSkillByJobID(jobDescriptionID).map(a => a._1)

  }

  /**
    * Method for getting jobDescription by ID from Data Base API.
    *
    * @param jobDescriptionID
    * @return
    */
  def getJobDescriptionByID(jobDescriptionID: Int): JobDescription = {

    val db = DBJobProfile

    db.getJobProfileByID(jobDescriptionID).get

  }

  /**
    * Method for checking if JobProfile contains skill
    *
    * @return boolean
    */
  def doesJobProfileContainSkill: Boolean =
    jobProfile.skills.nonEmpty

  /**
    * Method for checking if JobProfile contains competencies
    *
    * @return
    */
  def doesJobProfileContainCompetencies: Boolean =
    jobProfile.competencies.nonEmpty

  /**
    * Method for removing possible duplicates from list of JobDescription Skills
    *
    * @return
    */
  def duplicatesRemovedFromJobDescriptionSkills: List[JobDescriptionSkill] = {
    jobProfile.skills.groupBy(_.skillID)
      .map(_._2.head).toList.sortWith(_.skillID < _.skillID)
  }

  /**
    * Method for removing possible duplicates from list of JobDescription Competencies
    *
    * @return
    */
  def duplicatesRemovedFromJobDescriptionCompetencies: List[JobDescriptionCompetency] = {
    jobProfile.competencies.groupBy(_.competencyID)
      .map(_._2.head).toList.sortWith(_.competencyID < _.competencyID)

  }

  /**
    * Method for matching candidate profile skills with jobDescription skills.
    *
    * @return tuple with list of (canidateID, Percentage match)
    *         list of (prepared vector of skills jobDescription)
    *         list of (prepared vectors of skills candidate by candidate ID )
    */
  def skillMatching(): (List[(Int, Double)], List[Int], List[(Int, List[Int])]) = {


    val candidateSkillVectors: List[(Int, List[CandidateSkill])] =
      candidates.map(a => (a.ID, a.skills))


    val duplicatesRemovedJobDescription: List[JobDescriptionSkill] = {
      duplicatesRemovedFromJobDescriptionSkills
    }


    val duplicatesRemovedCandidateSkillVectors: List[(Int, List[CandidateSkill])] =
      candidateSkillVectors.map(a => (a._1, a._2.groupBy(_.skillID)
        .map(_._2.head).toList.sortWith(_.skillID < _.skillID)))


    var preparedVectors: List[(Int, List[CandidateSkill])] = Nil

    for (j <- duplicatesRemovedCandidateSkillVectors) {

      var preparedSub: List[CandidateSkill] = Nil


      for (i <- duplicatesRemovedJobDescription) {
        for (k <- j._2) {

          if (i.skillID == k.skillID) {
            preparedSub = preparedSub :+ CandidateSkill(k.skillID, k.name, k.rating)
          }

        }
      }

      preparedVectors = preparedVectors :+ (j._1, preparedSub)

    }

    val jobDescriptionVectorReady: List[Int] = duplicatesRemovedJobDescription.map(a => a.rating)

    var candidateSkillVectorsReady: List[(Int, List[Int])] = Nil

    for (i <- preparedVectors) {

      var candidateSkillVectorSub: List[Int] = Nil

      for (j <- jobDescriptionVectorReady.indices) {
        if (i._2.size - 1 >= j) {

          candidateSkillVectorSub = candidateSkillVectorSub :+ i._2(j).rating

        } else {
          candidateSkillVectorSub = candidateSkillVectorSub :+ 0
        }

      }

      candidateSkillVectorsReady = candidateSkillVectorsReady :+ (i._1, candidateSkillVectorSub)
    }

    /*
    println(duplicatesRemovedJobDescription)
    println(duplicatesRemovedCandidateSkillVectors)
    println(jobDescriptionVectorReady)
    println(candidateSkillVectorsReady)
    println(doesJobProfileContainCompetencies)
    */

    var listToReturn: List[(Int, Double)] = Nil

    if (candidateSkillVectorsReady.nonEmpty) {

      for (i <- candidateSkillVectorsReady) {

        // dependency injection

        val similarity =
          matchingMethod.similarity(jobDescriptionVectorReady, i._2)

        //SimilarityAlgorithm.cappedDistSimilarity(
        //jobDescriptionVectorReady, i._2)

        listToReturn = listToReturn :+ (i._1, similarity)
      }
    }


    (listToReturn, jobDescriptionVectorReady, candidateSkillVectorsReady)
  }

  /**
    * Method for matching candidate profile competencies with jobDescription competencies.
    *
    * @return tuple with list of (candidateID, Percentage match)
    *         list of (prepared vector of competencies  jobDescription)
    *         list of (prepared vectors of competencies candidate by candidate ID )
    */
  def competencyMatching(): (List[(Int, Double)], List[Int], List[(Int, List[Int])]) = {

    val candidateCompetencyVectors: List[(Int, List[CandidateCompetency])] =
      candidates.map(a => (a.ID, a.competencies))


    val duplicatesRemovedJobDescription: List[JobDescriptionCompetency] = {
      duplicatesRemovedFromJobDescriptionCompetencies
    }

    val duplicatesRemovedCandidateCompetencyVectors: List[(Int, List[CandidateCompetency])] =
      candidateCompetencyVectors.map(a => (a._1, a._2.groupBy(_.competencyID)
        .map(_._2.head).toList.sortWith(_.competencyID < _.competencyID)))


    var preparedVectors: List[(Int, List[CandidateCompetency])] = Nil

    for (j <- duplicatesRemovedCandidateCompetencyVectors) {

      var preparedSub: List[CandidateCompetency] = Nil


      for (i <- duplicatesRemovedJobDescription) {
        for (k <- j._2) {

          if (i.competencyID == k.competencyID) {
            preparedSub = preparedSub :+ CandidateCompetency(k.competencyID, k.name, k.rating)
          }

        }
      }

      preparedVectors = preparedVectors :+ (j._1, preparedSub)

    }

    val jobDescriptionVectorReady: List[Int] = duplicatesRemovedJobDescription.map(a => a.rating)
    var candidateCompetencyVectorsReady: List[(Int, List[Int])] = Nil

    for (i <- preparedVectors) {

      var candidateCompetencyVectorSub: List[Int] = Nil

      for (j <- jobDescriptionVectorReady.indices) {
        if (i._2.size - 1 >= j) {

          candidateCompetencyVectorSub = candidateCompetencyVectorSub :+ i._2(j).rating

        } else {
          candidateCompetencyVectorSub = candidateCompetencyVectorSub :+ 0
        }

      }

      candidateCompetencyVectorsReady =
        candidateCompetencyVectorsReady :+ (i._1, candidateCompetencyVectorSub)
    }

    /*
    println(candidateCompetencyVectorsReady)
    println(duplicatesRemovedJobDescription)
    println(duplicatesRemovedCandidateCompetencyVectors)
    println(jobDescriptionVectorReady)
    println(candidateCompetencyVectorsReady)
    println(doesJobProfileContainCompetencies)
    */

    var listToReturn: List[(Int, Double)] = Nil

    if (candidateCompetencyVectorsReady.nonEmpty) {

      for (i <- candidateCompetencyVectorsReady) {
        // dependency injection
        val similarity =
          matchingMethod.similarity(jobDescriptionVectorReady, i._2)

        //SimilarityAlgorithm.cappedDistSimilarity(
        //jobDescriptionVectorReady, i._2)

        listToReturn = listToReturn :+ (i._1, similarity)
      }
    }


    (listToReturn, jobDescriptionVectorReady, candidateCompetencyVectorsReady)

  }

  /**
    * Method creates combined vector of skills and competencies
    *
    * Auxiliary method for overAll Matching Method
    *
    * @param candidateSkillsVector
    * @param candidateCompetenciesVector
    * @return list of skills and competency vectors by candidate ID
    */
  def combineCandidateVectors
  (candidateSkillsVector: List[(Int, List[Int])],
   candidateCompetenciesVector: List[(Int, List[Int])]): List[(Int, List[Int])] = {

    var toReturn: List[(Int, List[Int])] = Nil

    for (i <- candidateSkillsVector.indices) {

      toReturn = toReturn :+ (candidateSkillsVector(i)._1,
        candidateSkillsVector(i)._2 ++ candidateCompetenciesVector(i)._2)

    }

    toReturn
  }

  /**
    * Method for matching the combined skills and competency vectors of candidates.
    *
    * @return list of matches by candidate ID's
    */
  def overAllMatching(): List[(Int, Double)] = {

    val skillsToMatch = skillMatching()
    val competenciesToMatch = competencyMatching()

    val jobDescriptionVectorSkills = skillsToMatch._2
    val jobDescriptionVectorCompetencies = competenciesToMatch._2

    val candidateSkillsVector = skillsToMatch._3
    val candidateCompetenciesVector = competenciesToMatch._3

    val combinedJobDescriptionVector =
      jobDescriptionVectorSkills ++ jobDescriptionVectorCompetencies

    val combinedCandidateVector =
      combineCandidateVectors(candidateSkillsVector, candidateCompetenciesVector)

    var listToReturn: List[(Int, Double)] = Nil

    if (combinedCandidateVector.nonEmpty) {

      for (i <- combinedCandidateVector) {
        // dependency injection

        val similarity =
          matchingMethod.similarity(combinedJobDescriptionVector, i._2)

        //SimilarityAlgorithm.cappedDistSimilarity(
        //combinedJobDescriptionVector, i._2)

        listToReturn = listToReturn :+ (i._1, similarity)
      }
    }

    listToReturn

  }

  /**
    * Method for creating matches on skills, competencies and the combined vector
    *
    * @return List of the three mathing values by candidate ID,
    *         Sorted by overAll matching percentage
    */
  def returnAllResults(): List[(Int, Double, Double, Double)] = {

    val matchingBySkill = skillMatching()
    val matchingByCompetency = competencyMatching()
    val matchingOverAll = overAllMatching()

    var toReturn: List[(Int, Double, Double, Double)] = Nil
    if (doesJobProfileContainCompetencies) {
      for (i <- matchingOverAll.indices) {
        toReturn = toReturn :+ (matchingOverAll(i)._1, matchingOverAll(i)._2, matchingBySkill._1(i)._2,
          matchingByCompetency._1(i)._2)

      }
    } else {

      for (i <- matchingOverAll.indices) {
        toReturn = toReturn :+ (matchingOverAll(i)._1, matchingOverAll(i)._2, matchingBySkill._1(i)._2, 0.0)

      }

    }

    toReturn.sortWith(_._2 > _._2)

  }


}

