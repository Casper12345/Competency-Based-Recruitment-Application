package model.DataBaseConnection.Objects

/**
  * Created by Casper on 11/07/2017.
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

