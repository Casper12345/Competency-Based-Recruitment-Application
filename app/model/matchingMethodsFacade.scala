package model

import persistenceAPI.DataBaseConnection.ConnectCandidate.DBCandidate
import persistenceAPI.DataBaseConnection.Objects.Candidate

/**
  * Methods for matching using a facade pattern.
  */
object matchingMethodsFacade {

  /**
    * Method that returns all matching candidates from Matching methods
    *
    * @param jobDescriptionID
    * @return List of matching by overall, skill, competency with candidate ID's
    */
  def pullMatchingCandidatesByJobDescriptionID(jobDescriptionID: Int):
  List[(Int, Double, Double, Double)] = {

    val machingMethods = new matchingMethods(jobDescriptionID)

    machingMethods.returnAllResults()

  }

  /**
    * Method for getting matching candidates from the DB
    * Method replaces candidate ID with candidate object pulled from the DB
    *
    * @param jobDescriptionID
    * @return
    */
  def getListOfMachingCandidatesFromDB(jobDescriptionID: Int):
  List[(Candidate, Double, Double, Double)] = {

    val matchingCandidates =
      pullMatchingCandidatesByJobDescriptionID(jobDescriptionID)

    var toReturn: List[(Candidate, Double, Double, Double)] = Nil

    var db = DBCandidate

    for (i <- matchingCandidates) {

      toReturn = toReturn :+ (db.getCandidateByID(i._1).get, i._2, i._3, i._4)

    }

    toReturn
  }

}
