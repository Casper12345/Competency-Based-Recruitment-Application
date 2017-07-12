package controllers

import model.DataBaseConnection.ConnectCandidate.{DBCandidate, DBCandidateCompetency, DBCandidateSkill}
import model.DataBaseConnection.ConnectCompetency.DBCompetency
import model.DataBaseConnection.ConnectSkill.DBSkill
import play.api.data.Form
import play.api.data.Forms.tuple
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._


/**
  * Created by Casper on 08/07/2017.
  */

object Recruiter extends Controller {

  def recruiterMain = Action {
    implicit request =>
      val userName = request.session.get("username")
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") =>
          Ok(views.html.recruiter.recruiterMain())
        case Some("SuperUser") =>
          Ok(views.html.recruiter.recruiterMain())
        case _ =>
          Redirect("/")
      }

  }


  def createCandidateProfile = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") =>
          Ok(views.html.recruiter.createCandidateProfile())
        case Some("SuperUser") =>
          Ok(views.html.recruiter.createCandidateProfile())
        case _ =>
          Redirect("/")
      }
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
    implicit request =>
      val priv = request.session.get("privilege")

      val db = DBCandidate

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") =>
          Ok(views.html.recruiter.viewCandiate(db.getAllCandidates()))
        case Some("SuperUser") =>
          Ok(views.html.recruiter.viewCandiate(db.getAllCandidates()))
        case _ =>
          Redirect("/")
      }
  }

  def candidate() = Action {
    implicit request =>
      val priv = request.session.get("privilege")
      val id: Option[String] = request.getQueryString("id")

      val db = DBCandidate

      if (id.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") =>
            Ok(views.html.recruiter.candidate(db.getCandidateByID(id.get.toInt).get))
          case Some("SuperUser") =>
            Ok(views.html.recruiter.candidate(db.getCandidateByID(id.get.toInt).get))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }
  }

  def addSkillCandidate() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBSkill

      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") =>
            Ok(views.html.recruiter.addSkillRecruiter(db.getAllSkills())(candidateID.get.toInt))
          case Some("SuperUser") =>
            Ok(views.html.recruiter.addSkillRecruiter(db.getAllSkills())(candidateID.get.toInt))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }
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
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBCompetency

      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") =>
            Ok(views.html.recruiter.addCompetencyRecruiter(db.getAllCompetencies())(candidateID.get.toInt))
          case Some("SuperUser") =>
            Ok(views.html.recruiter.addCompetencyRecruiter(db.getAllCompetencies())(candidateID.get.toInt))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }

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
