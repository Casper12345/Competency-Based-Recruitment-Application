package model.persistenceAPIInterface.candidateProfilePersistence

import persistenceAPI.DataBaseConnection.objects.Candidate

/**
  * Trait for candiate persistence facade
  */
trait CandidatePersistenceFacadeTrait {


  /**
    * Method for adding a candidate to the system
    *
    * @param name              name of candidate
    * @param surname           surname of candiate
    * @param educationName     education name
    * @param currentJobTitle   job title
    * @param educationLevelID  level of education 1-6
    * @param experienceLevelID level of experience 1-6
    */
  def addCandidate(name: String, surname: String, educationName: String, currentJobTitle: String,
                   educationLevelID: String, experienceLevelID: String): Unit

  /**
    * Method for getting candidate by ID
    *
    * @param CandidateID candidate id
    * @return Option of type Candidate
    */
  def getCandidateByID(CandidateID: Int): Option[Candidate]

  /**
    * Method for getting all candidates
    *
    * @return list of Candidate
    */
  def getAllCandidates(): List[Candidate]


  /**
    * Deletes candidates by id
    * @param candidateID candidate id
    */
  def deleteCandidate(candidateID: Int): Unit


}
