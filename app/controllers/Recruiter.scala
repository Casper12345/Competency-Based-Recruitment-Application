package controllers

import controllers.MainApp.Ok
import model.DataBaseConnection.ConnectCandidate.DBCandidate
import model.DataBaseConnection.ConnectSkill.DBSkill
import model.DataBaseConnection.DBMain
import model.DataBaseConnection.Objects.Candidate
import play.api.data.Form
import play.api.data.Forms.tuple
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._


/**
  * Created by Casper on 08/07/2017.
  */

object Recruiter extends Controller {

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
      "educationName" -> text,
      "currentJobTitle" -> text,
      "educationLevel" -> text,
      "experienceLevel" -> text
    )
  )

  def candidateFormSubmit = Action {
    implicit request =>

      val (name, surname, educationName, currentJobTitle,
      educationLevel, experienceLevel) = candidateForm.bindFromRequest().get


      print(educationLevel, educationName)

      val db = DBCandidate

      db.addCandidate(name, surname, educationName, currentJobTitle, educationLevel, experienceLevel)

      Redirect("/recruiterMain")

  }

  def viewCandidate() = Action {

    val db = DBCandidate

    val candidates: List[Candidate] = db.getAllCandidates()

    Ok(views.html.recruiter.viewCandiate(candidates))

  }

  def candidate() = Action {

    implicit request =>

      val id: Option[String] = request.getQueryString("id")

      println(id + "more")

      val db = DBCandidate

      val candidate: Candidate = db.getCandidateByID(id.get.toInt).get

      Ok(views.html.recruiter.candidate(candidate))
  }

  var addSkillID: Option[(Int, Int)] = None

  def addSkillCandidate() = Action {

    implicit request =>

      val candidateID: Option[String] = request.getQueryString("CandidateID")
      val skillID: Option[String] = request.getQueryString("SkillID")

      val db = DBSkill

      val skills = db.getAllSkills()

      if (skillID.isEmpty) {
        Ok(views.html.recruiter.addSkillRecruiter(skills)(candidateID.get.toInt)(0))
      } else {

        println(skillID)

        Ok(views.html.recruiter.addSkillRecruiter(skills)(candidateID.get.toInt)(skillID.get.toInt))
      }

  }

}
