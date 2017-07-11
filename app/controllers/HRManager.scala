package controllers

import controllers.Recruiter.{Ok, Redirect}
import model.DataBaseConnection.ConnectCandidate.{DBCandidate, DBCandidateCompetency, DBCandidateSkill}
import model.DataBaseConnection.ConnectCompetency.DBCompetency
import model.DataBaseConnection.ConnectJobProfile.{DBJobProfile, DBJobProfileCompetency, DBJobProfileSkill}
import model.DataBaseConnection.ConnectSkill.DBSkill
import model.DataBaseConnection.Objects.JobDescription
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Created by Casper on 11/07/2017.
  */
object HRManager extends Controller {

  def hrManagerMain = Action {
    Ok(views.html.hrManager.hrManagerMain())
  }

  def createJobDescription() = Action {
    Ok(views.html.hrManager.createJobDescription())

  }

  val jobDescriptionForm = Form {
    tuple("jobTitle" -> text,
      "educationName" -> text,
      "educationLevel" -> text,
      "experienceLevel" -> text
    )
  }

  def jobDescriptionPost() = Action {
    implicit request =>

      val (jobTitle, educationName, educationLevel,
      experienceLevel) = jobDescriptionForm.bindFromRequest().get

      val db = DBJobProfile

      db.addJobProfile(jobTitle, educationName, educationLevel, experienceLevel)

      Redirect("/hrManagerMain")

  }

  def viewJobDescription() = Action {

    val db = DBJobProfile

    val jobDescriptions: List[JobDescription] = db.getAllJobDescriptions()

    Ok(views.html.hrManager.viewJobDescription(jobDescriptions))

  }

  def jobDescription() = Action {

    implicit request =>

      val id: Option[String] = request.getQueryString("id")

      println(id + "more")

      val db = DBJobProfile

      val jobProfile: JobDescription = db.getJobProfileByID(id.get.toInt).get

      Ok(views.html.hrManager.jobDescription(jobProfile))
  }

  def addSkillJobDescription() = Action {

    implicit request =>

      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBSkill

      val skills = db.getAllSkills()

      Ok(views.html.hrManager.addSkillHRManager(skills)(candidateID.get.toInt))

  }

  val skillAddForm = Form {
    tuple("skillID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }

  def addSkillJobDescriptionPost() = Action {

    implicit request =>

      val (skillID, rating, candidateID) = skillAddForm.bindFromRequest().get

      val db = DBJobProfileSkill

      println(skillID + " " + rating + " " + candidateID)

      db.addJobProfileSkill(skillID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/hrManagerMain/jobDescription?id=$candidateID")

  }

  def addCompetencyJobDescription() = Action {

    implicit request =>

      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBCompetency

      val competencies = db.getAllCompetencies()

      Ok(views.html.hrManager.addCompetencyHRManager(competencies)(candidateID.get.toInt))


  }

  val competencyAddForm = Form {
    tuple("competencyID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }


  def addCompetencyJobDescriptionPost() = Action {

    implicit request =>

      val (competencyID, rating, candidateID) = competencyAddForm.bindFromRequest().get

      val db = DBJobProfileCompetency

      println(competencyID + " " + rating + " " + candidateID)

      db.addJobProfileCompetency(competencyID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/hrManagerMain/jobDescription?id=$candidateID")

  }


}
