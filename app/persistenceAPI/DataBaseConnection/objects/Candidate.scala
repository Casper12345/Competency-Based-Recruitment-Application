package persistenceAPI.DataBaseConnection.objects

/**
  * Case class for representation of candidate
  * The creation of objects should belong to a factory
  *
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



