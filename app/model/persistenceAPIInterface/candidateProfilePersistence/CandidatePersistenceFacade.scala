package model.persistenceAPIInterface.candidateProfilePersistence

import persistenceAPI.DataBaseConnection.connectCandidate.DBCandidate
import persistenceAPI.DataBaseConnection.objects.Candidate

/**
  * Implements CandidatePersistenceFacadeTrait
  */
object CandidatePersistenceFacade extends CandidatePersistenceFacadeTrait {

  val candidate = DBCandidate

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
  override def addCandidate(name: String,
                            surname: String,
                            educationName: String,
                            currentJobTitle: String,
                            educationLevelID: String,
                            experienceLevelID: String): Unit = {
    candidate.addCandidate(name,
      surname,
      educationName,
      currentJobTitle,
      educationLevelID,
      experienceLevelID)
  }

  /**
    * Method for getting candidate by ID
    *
    * @param CandidateID candidate id
    * @return Option of type Candidate
    */
  override def getCandidateByID(CandidateID: Int): Option[Candidate] =
    candidate.getCandidateByID(CandidateID)

  /**
    * Method for getting all candidates
    *
    * @return list of Candidate
    */
  override def getAllCandidates(): List[Candidate] =
    candidate.getAllCandidates()
}
