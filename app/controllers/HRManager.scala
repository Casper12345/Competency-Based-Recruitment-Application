package controllers

import persistenceAPI.DataBaseConnection.ConnectCompetency.DBCompetency
import persistenceAPI.DataBaseConnection.ConnectJobProfile.{DBJobProfile, DBJobProfileCompetency, DBJobProfileSkill}
import persistenceAPI.DataBaseConnection.ConnectSkill.DBSkill
import persistenceAPI.DataBaseConnection.Objects.JobDescription
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
      val jobDescriptionID: Option[String] = request.getQueryString("jobDescriptionID")

      val db = DBSkill

      if (jobDescriptionID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") =>
            Ok(views.html.hrManager.addSkillHRManager(db.getAllSkills())(jobDescriptionID.get.toInt))
          case Some("SuperUser") =>
            Ok(views.html.hrManager.addSkillHRManager(db.getAllSkills())(jobDescriptionID.get.toInt))
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
      "jobDescriptionID" -> text
    )
  }

  def addSkillJobDescriptionPost() = Action {

    implicit request =>

      val (skillID, rating, jobDescriptionID) = skillAddForm.bindFromRequest().get

      val db = DBJobProfileSkill

      println(skillID + " " + rating + " " + jobDescriptionID)

      db.addJobProfileSkill(skillID.toInt, rating.toInt, jobDescriptionID.toInt)

      Redirect(s"/hrManagerMain/jobDescription?id=$jobDescriptionID")

  }

  def addCompetencyJobDescription() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val jobDescriptionID: Option[String] = request.getQueryString("jobDescriptionID")

      val db = DBCompetency

      if (jobDescriptionID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") =>
            Ok(views.html.hrManager.addCompetencyHRManager
            (db.getAllCompetencies())(jobDescriptionID.get.toInt))
          case Some("SuperUser") =>
            Ok(views.html.hrManager.addCompetencyHRManager
            (db.getAllCompetencies())(jobDescriptionID.get.toInt))
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
      "jobDescriptionID" -> text
    )
  }


  def addCompetencyJobDescriptionPost() = Action {

    implicit request =>

      val (competencyID, rating, jobDescriptionID) = competencyAddForm.bindFromRequest().get

      val db = DBJobProfileCompetency

      db.addJobProfileCompetency(competencyID.toInt, rating.toInt, jobDescriptionID.toInt)

      Redirect(s"/hrManagerMain/jobDescription?id=$jobDescriptionID")

  }

  def matchingMain() = Action {
    implicit request =>
      val priv = request.session.get("privilege")
      val jobDescriptionID: Option[String] = request.getQueryString("jobDescriptionID")

      if (jobDescriptionID.isDefined) {
        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") =>
            Ok(views.html.hrManager.matchingMain())
          case Some("SuperUser") =>
            Ok(views.html.hrManager.matchingMain())
          case _ =>
            Redirect("/")
        }
      }else{
        Forbidden
      }
  }
}
