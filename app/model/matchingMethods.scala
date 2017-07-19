package model

import persistenceAPI.DataBaseConnection.ConnectJobProfile.DBJobProfile
import persistenceAPI.DataBaseConnection.Objects._
import persistenceAPI.DataBaseConnection.SqlQueries.DBQueries


/**
  * Created by Casper on 16/07/2017.
  */
class matchingMethods(jobDescriptionID: Int) {


  var candidatesByOneSkill: List[Candidate] = matchingOnOneSkill(jobDescriptionID)
  var jobProfile: JobDescription = getJobDescriptionByID(jobDescriptionID)


  def matchingOnOneSkill(jobDescriptionID: Int): List[Candidate] = {

    val db = DBQueries

    db.getMatchingCandidatesOneSkillByJobID(jobDescriptionID).map(a => a._1)

  }


  def getJobDescriptionByID(jobDescriptionID: Int): JobDescription = {

    val db = DBJobProfile

    db.getJobProfileByID(jobDescriptionID).get

  }


  def doesJobProfilContainSkill: Boolean =
    jobProfile.skills.nonEmpty

  def doesJobProfilContainCompetencies: Boolean =
    jobProfile.competencies.nonEmpty



  def duplicatesRemovedFromJobDescriptionSkills: List[JobDescriptionSkill] = {
    jobProfile.skills.groupBy(_.skillID)
      .map(_._2.head).toList.sortWith(_.skillID < _.skillID)
  }

  def duplicatesRemovedFromJobDescriptionCompetencies: List[JobDescriptionCompetency] = {
    jobProfile.competencies.groupBy(_.competencyID)
      .map(_._2.head).toList.sortWith(_.competencyID < _.competencyID)

  }

  def skillMatching(): (List[(Int, Double)], List[Int], List[(Int, List[Int])]) = {


    val candidateSkillVectors: List[(Int, List[CandidateSkill])] =
      candidatesByOneSkill.map(a => (a.ID, a.skills))


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
          candidateSkillVectorSub = candidateSkillVectorSub :+
            (jobDescriptionVectorReady(j) + 4)
        }

      }

      candidateSkillVectorsReady = candidateSkillVectorsReady :+ (i._1, candidateSkillVectorSub)
    }

    var listToReturn: List[(Int, Double)] = Nil

    if (candidateSkillVectorsReady.nonEmpty) {

      for (i <- candidateSkillVectorsReady) {
        val similarity = similarityAlgorithm.distSim(
          jobDescriptionVectorReady, i._2)

        listToReturn = listToReturn :+ (i._1, similarity)
      }
    }


    (listToReturn, jobDescriptionVectorReady, candidateSkillVectorsReady)
  }


  def competencyMatching(): (List[(Int, Double)], List[Int], List[(Int, List[Int])]) = {

    val candidateCompetencyVectors: List[(Int, List[CandidateCompetency])] =
      candidatesByOneSkill.map(a => (a.ID, a.competencies))


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
          candidateCompetencyVectorSub = candidateCompetencyVectorSub :+
            (jobDescriptionVectorReady(j) + 4)
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
    println(doesJobProfilContainCompetencies)
    */

    var listToReturn: List[(Int, Double)] = Nil

    if (candidateCompetencyVectorsReady.nonEmpty) {

      for (i <- candidateCompetencyVectorsReady) {
        val similarity = similarityAlgorithm.distSim(
          jobDescriptionVectorReady, i._2)

        listToReturn = listToReturn :+ (i._1, similarity)
      }
    }


    (listToReturn, jobDescriptionVectorReady, candidateCompetencyVectorsReady)

  }


  def combineCandidateVectors
  (candidateSkillsVector: List[(Int, List[Int])],
   candidateCompetenciesVector: List[(Int, List[Int])]): List[(Int, List[Int])] ={

    var toReturn: List[(Int, List[Int])] = Nil

    for(i <- candidateSkillsVector.indices){

      toReturn = toReturn :+ (candidateSkillsVector(i)._1,
        candidateSkillsVector(i)._2 ++ candidateCompetenciesVector(i)._2)

    }

    toReturn
  }


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
        val similarity = similarityAlgorithm.distSim(
          combinedJobDescriptionVector, i._2)

        listToReturn = listToReturn :+ (i._1, similarity)
      }
    }

    listToReturn

  }

  def returnAllResults(): List[(Int, Double, Double, Double)] ={

    var matchingBySkill = skillMatching()
    var matchingByCompetency = competencyMatching()
    var matchingOverAll = overAllMatching()

    var toReturn: List[(Int, Double, Double, Double)] = Nil
    if(doesJobProfilContainCompetencies) {
      for (i <- matchingOverAll.indices) {
        toReturn = toReturn :+ (matchingOverAll(i)._1, matchingOverAll(i)._2, matchingBySkill._1(i)._2,
          matchingByCompetency._1(i)._2)

      }
    }else{

      for (i <- matchingOverAll.indices) {
        toReturn = toReturn :+ (matchingOverAll(i)._1, matchingOverAll(i)._2, matchingBySkill._1(i)._2, 0.0)

      }

    }

    toReturn.sortWith(_._2 > _._2)

  }


}

