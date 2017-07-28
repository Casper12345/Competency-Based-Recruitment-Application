package persistenceAPI.DataBaseConnection.Objects

/**
  * Case class to represent job description
  */
case class JobDescription(
                           ID: Int,
                           jobTitle: String,
                           educationName: String,
                           educationLevel: String,
                           experienceLevel: String,
                           skills: List[JobDescriptionSkill],
                           competencies: List[JobDescriptionCompetency]
                         )

