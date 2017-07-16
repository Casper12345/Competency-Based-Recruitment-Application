package persistenceAPI.DataBaseConnection.Objects

/**
  * Created by Casper on 06/07/2017.
  */
case class Candidate(

                      ID: Int,
                      name: String,
                      surname: String,
                      educationName: String,
                      currentJobTitle: String,
                      educationLevel: String,
                      experienceLevel: String,
                      skills: List[CandidateSkill],
                      competencies: List[CandidateCompetency]
                    )



