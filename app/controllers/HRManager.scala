package controllers

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
    implicit request =>
      val userName = request.session.get("username")
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("HRManager") =>
          Ok(views.html.hrManager.hrManagerMain(userName.get))
        case Some("SuperUser") =>
          Ok(views.html.hrManager.hrManagerMain(userName.get))
        case _ =>
          Redirect("/")
      }

  }

  def createJobDescription() = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("HRManager") =>
          Ok(views.html.hrManager.createJobDescription())
        case Some("SuperUser") =>
          Ok(views.html.hrManager.createJobDescription())
        case _ =>
          Redirect("/")
      }

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
    implicit request =>
      val priv = request.session.get("privilege")

      val db = DBJobProfile

      val jobDescriptions: List[JobDescription] = db.getAllJobDescriptions()

      priv match {
        case None =>
          Redirect("/")
        case Some("HRManager") =>
          Ok(views.html.hrManager.viewJobDescription(jobDescriptions))
        case Some("SuperUser") =>
          Ok(views.html.hrManager.viewJobDescription(jobDescriptions))
        case _ =>
          Redirect("/")
      }

  }

  def jobDescription() = Action {

    implicit request =>

      val id: Option[String] = request.getQueryString("id")
      val priv = request.session.get("privilege")

      val db = DBJobProfile

      if (id.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") =>
            Ok(views.html.hrManager.jobDescription(db.getJobProfileByID(id.get.toInt).get))
          case Some("SuperUser") =>
            Ok(views.html.hrManager.jobDescription(db.getJobProfileByID(id.get.toInt).get))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }


  }

  def addSkillJobDescription() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBSkill

      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") =>
            Ok(views.html.hrManager.addSkillHRManager(db.getAllSkills())(candidateID.get.toInt))
          case Some("SuperUser") =>
            Ok(views.html.hrManager.addSkillHRManager(db.getAllSkills())(candidateID.get.toInt))
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
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBCompetency

      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") =>
            Ok(views.html.hrManager.addCompetencyHRManager
            (db.getAllCompetencies())(candidateID.get.toInt))
          case Some("SuperUser") =>
            Ok(views.html.hrManager.addCompetencyHRManager
            (db.getAllCompetencies())(candidateID.get.toInt))
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


  def addCompetencyJobDescriptionPost() = Action {

    implicit request =>

      val (competencyID, rating, candidateID) = competencyAddForm.bindFromRequest().get

      val db = DBJobProfileCompetency

      db.addJobProfileCompetency(competencyID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/hrManagerMain/jobDescription?id=$candidateID")

  }

}
