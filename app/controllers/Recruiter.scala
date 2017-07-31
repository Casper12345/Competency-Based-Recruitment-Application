package controllers

import persistenceAPI.DataBaseConnection.connectCandidate.{DBCandidate, DBCandidateCompetency, DBCandidateSkill}
import persistenceAPI.DataBaseConnection.connectCompetency.DBCompetency
import persistenceAPI.DataBaseConnection.connectSkill.DBSkill
import play.api.data.Form
import play.api.data.Forms.tuple
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._


/**
  * Controller methods for the recruiter part of the application
  */

object Recruiter extends Controller {

  /**
    * Actopn for rendering recruiterMain template
    *
    * @return
    */
  def recruiterMain = Action {
    implicit request =>
      val userName = request.session.get("username")
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>
          Ok(views.html.recruiter.recruiterMain())
        case _ =>
          Redirect("/")
      }

  }

  /**
    * Action for rendering createCandidateProfile template
    *
    * @return
    */
  def createCandidateProfile = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>
          Ok(views.html.recruiter.createCandidateProfile())
        case _ =>
          Redirect("/")
      }
  }

  /**
    * Form for sending candidate post request
    */
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

  /**
    * Action for form request Candidate
    *
    * @return
    */
  def candidateFormSubmit = Action {
    implicit request =>

      val (name, surname, educationName, currentJobTitle,
      educationLevel, experienceLevel) = candidateForm.bindFromRequest().get


      print(educationLevel, educationName)

      val db = DBCandidate

      db.addCandidate(name, surname, educationName, currentJobTitle, educationLevel, experienceLevel)

      Redirect("/recruiterMain")

  }

  /**
    * Action for rendering viewCandidate template
    *
    * @return
    */
  def viewCandidate() = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      val db = DBCandidate

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>
          Ok(views.html.recruiter.viewCandiate(db.getAllCandidates()))
        case _ =>
          Redirect("/")
      }
  }

  /**
    * Action for rendering candidate template
    *
    * @return
    */
  def candidate() = Action {
    implicit request =>
      val priv = request.session.get("privilege")
      val id: Option[String] = request.getQueryString("id")

      val db = DBCandidate

      if (id.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") | Some("SuperUser") =>
            Ok(views.html.recruiter.candidate(db.getCandidateByID(id.get.toInt).get))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }
  }

  /**
    * Action for rendering addSkillCandidate template
    *
    * @return
    */
  def addSkillCandidate() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBSkill

      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") | Some("SuperUser") =>
            Ok(views.html.recruiter.addSkillRecruiter(db.getAllSkills())(candidateID.get.toInt))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }
  }

  /* Form for addSkill post request
   */
  val skillAddForm = Form {
    tuple("skillID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }

  /**
    * Action for addSkillCandidate post request
    *
    * @return
    */
  def addSkillCandidatePost() = Action {

    implicit request =>

      val (skillID, rating, candidateID) = skillAddForm.bindFromRequest().get

      val db = DBCandidateSkill

      println(skillID + " " + rating + " " + candidateID)

      db.addCandidateSkill(skillID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/recruiterMain/candidate?id=$candidateID")

  }

  /**
    * Action for rendering addCompetencyCandidate template
    *
    * @return
    */
  def addCompetencyCandidate() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")

      val db = DBCompetency

      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") | Some("SuperUser") =>
            Ok(views.html.recruiter.addCompetencyRecruiter(db.getAllCompetencies())(candidateID.get.toInt))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }

  }

  /**
    * Form for addCompetency post request
    */
  val competencyAddForm = Form {
    tuple("competencyID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }

  /**
    * addCompetencyCandidate post request
    *
    * @return
    */
  def addCompetencyCandidatePost() = Action {

    implicit request =>

      val (competencyID, rating, candidateID) = competencyAddForm.bindFromRequest().get

      val db = DBCandidateCompetency

      println(competencyID + " " + rating + " " + candidateID)

      db.addCandidateCompetency(competencyID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/recruiterMain/candidate?id=$candidateID")
  }


}
