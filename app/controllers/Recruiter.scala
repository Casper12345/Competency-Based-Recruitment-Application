package controllers

import model.DataBaseConnection.ConnectCandidate.{DBCandidate, DBCandidateCompetency, DBCandidateSkill}
import model.DataBaseConnection.ConnectCompetency.DBCompetency
import model.DataBaseConnection.ConnectSkill.DBSkill
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

  def addSkillCandidate() = Action {

    implicit request =>

      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBSkill

      val skills = db.getAllSkills()

      Ok(views.html.recruiter.addSkillRecruiter(skills)(candidateID.get.toInt))


  }

  val skillAddForm = Form {
    tuple("skillID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }


  def addSkillCandidatePost() = Action {

    implicit request =>

      val (skillID, rating, candidateID) = skillAddForm.bindFromRequest().get

      val db = DBCandidateSkill

      println(skillID + " " + rating + " " + candidateID)

      db.addCandidateSkill(skillID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/recruiterMain/candidate?id=$candidateID")

  }


  def addCompetencyCandidate() = Action {

    implicit request =>

      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBCompetency

      val competencies = db.getAllCompetencies()

      Ok(views.html.recruiter.addCompetencyRecruiter(competencies)(candidateID.get.toInt))


  }

  val competencyAddForm = Form {
    tuple("competencyID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }


  def addCompetencyCandidatePost() = Action {

    implicit request =>

      val (competencyID, rating, candidateID) = competencyAddForm.bindFromRequest().get

      val db = DBCandidateCompetency

      println(competencyID + " " + rating + " " + candidateID)

      db.addCandidateCompetency(competencyID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/recruiterMain/candidate?id=$candidateID")

  }


}
