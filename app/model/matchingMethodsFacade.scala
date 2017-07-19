package model

import persistenceAPI.DataBaseConnection.ConnectCandidate.DBCandidate
import persistenceAPI.DataBaseConnection.Objects.Candidate

/**
  * Created by Casper on 19/07/2017.
  */
object matchingMethodsFacade {


  def pullMatchingCandidatesByJobDescriptionID(jobDescriptionID: Int):
  List[(Int, Double, Double, Double)] = {

    val machingMethods = new matchingMethods(jobDescriptionID)

    machingMethods.returnAllResults()

  }

  def getListOfMachingCandidatesFromDB(jobDescriptionID: Int):
  List[(Candidate, Double, Double, Double)] = {

    val matchingCandidates =
      pullMatchingCandidatesByJobDescriptionID(jobDescriptionID)

    var toReturn: List[(Candidate, Double, Double, Double)] = Nil

    var db = DBCandidate

    for (i <- matchingCandidates){

      toReturn = toReturn :+ (db.getCandidateByID(i._1).get, i._2, i._3, i._4)

    }

    toReturn
  }

}
