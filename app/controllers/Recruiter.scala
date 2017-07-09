package controllers

import model.DataBaseConnection.DBMain
import play.api.data.Form
import play.api.data.Forms.tuple
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._


/**
  * Created by Casper on 08/07/2017.
  */

object Recruiter extends Controller{

  def recruiterMain = Action {
    Ok(views.html.recruiter.recruiterMain())
  }


  def createCandidateProfile = Action {
    Ok(views.html.recruiter.createCandidateProfile())
  }


  val candidateForm = Form(
    tuple(
      "name" -> text,
      "surname" -> text,
      "education" -> text,
      "currentJobTitle" -> text,
      "educationLevel"  -> text,
      "experienceLevel"  -> text
    )
  )

  def candidateFormSubmit = Action {
    implicit request =>

      val (name, surname, education, currentJobTitle,
      educationLevel, experienceLevel) = candidateForm.bindFromRequest().get


      //print(ed, education)

      val db = DBMain

      //db.addCandidate()

      Redirect("/recruiterMain")
  }

}
