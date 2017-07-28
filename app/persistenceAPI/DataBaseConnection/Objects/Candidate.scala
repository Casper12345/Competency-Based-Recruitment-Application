package persistenceAPI.DataBaseConnection.Objects

/**
  * Case class for representation of candidate
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



