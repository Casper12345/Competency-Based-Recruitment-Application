package controllers

import controllers.Recruiter.Ok
import model.DataBaseConnection.ConnectCandidate.DBCandidate
import model.DataBaseConnection.ConnectJobProfile.DBJobProfile
import model.DataBaseConnection.Objects.{Candidate, JobProfile}
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

    val jobDescriptions: List[JobProfile] = db.getAllJobDescriptions()

    Ok(views.html.hrManager.viewJobDescription(jobDescriptions))

  }

  def jobDescription() = Action {

    implicit request =>

      val id: Option[String] = request.getQueryString("id")

      println(id + "more")

      val db = DBJobProfile

      val jobProfile: JobProfile = db.getJobProfileByID(id.get.toInt).get

      Ok(views.html.hrManager.jobDescription(jobProfile))
  }


}
