package controllers

import model.matchingMethodsFacade
import persistenceAPI.DataBaseConnection.ConnectCandidate.DBCandidate
import persistenceAPI.DataBaseConnection.ConnectCompetency.DBCompetency
import persistenceAPI.DataBaseConnection.ConnectJobProfile.{DBJobProfile, DBJobProfileCompetency, DBJobProfileSkill}
import persistenceAPI.DataBaseConnection.ConnectSkill.DBSkill
import persistenceAPI.DataBaseConnection.Objects.JobDescription
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Controller Methods for HR manager part of the Application.
  */
object HRManager extends Controller {

  /**
    * Action for hrManagerMain
    * Renders hrManagerMain template
    *
    */
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

  /**
    * Action for createJobDescription
    * Renders createJobDescription template.
    *
    * @return
    */
  def createJobDescription() = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("HRManager") | Some("SuperUser") =>
          Ok(views.html.hrManager.createJobDescription())
        case _ =>
          Redirect("/")
      }

  }

  /**
    * Form for post request from create jobDescription form
    */
  val jobDescriptionForm = Form {
    tuple("jobTitle" -> text,
      "educationName" -> text,
      "educationLevel" -> text,
      "experienceLevel" -> text
    )
  }

  /**
    * Action for create jobDescription post form
    *
    * @return
    */
  def jobDescriptionPost() = Action {
    implicit request =>

      val (jobTitle, educationName, educationLevel,
      experienceLevel) = jobDescriptionForm.bindFromRequest().get

      val db = DBJobProfile

      db.addJobProfile(jobTitle, educationName, educationLevel, experienceLevel)

      Redirect("/hrManagerMain")

  }

  /**
    * Action for rendering the viewJobDescription template
    *
    * @return
    */
  def viewJobDescription() = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      val db = DBJobProfile

      val jobDescriptions: List[JobDescription] = db.getAllJobDescriptions()

      priv match {
        case None =>
          Redirect("/")
        case Some("HRManager") | Some("SuperUser") =>
          Ok(views.html.hrManager.viewJobDescription(jobDescriptions))
        case _ =>
          Redirect("/")
      }

  }

  /**
    * Action for rendering jobDescription template.
    *
    * @return
    */
  def jobDescription() = Action {

    implicit request =>

      val id: Option[String] = request.getQueryString("id")
      val priv = request.session.get("privilege")

      val db = DBJobProfile

      if (id.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") | Some("SuperUser") =>
            Ok(views.html.hrManager.jobDescription(db.getJobProfileByID(id.get.toInt).get))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }


  }

  /**
    * Action for rendering the addSkillJobDescription template
    *
    * @return
    */
  def addSkillJobDescription() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val jobDescriptionID: Option[String] = request.getQueryString("jobDescriptionID")

      val db = DBSkill

      if (jobDescriptionID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") | Some("SuperUser") =>
            Ok(views.html.hrManager.addSkillHRManager(db.getAllSkills())(jobDescriptionID.get.toInt))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }
  }

  /**
    * Form for post request for addSkillJobDescription
    */
  val skillAddForm = Form {
    tuple("skillID" -> text,
      "rating" -> text,
      "jobDescriptionID" -> text
    )
  }

  /**
    * Action for post request addSkillJobDescription
    *
    * @return
    */
  def addSkillJobDescriptionPost() = Action {

    implicit request =>

      val (skillID, rating, jobDescriptionID) = skillAddForm.bindFromRequest().get

      val db = DBJobProfileSkill

      println(skillID + " " + rating + " " + jobDescriptionID)

      db.addJobProfileSkill(skillID.toInt, rating.toInt, jobDescriptionID.toInt)

      Redirect(s"/hrManagerMain/jobDescription?id=$jobDescriptionID")

  }

  /**
    * Action for rendering addCompetencyJobDescription template.
    *
    * @return
    */
  def addCompetencyJobDescription() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val jobDescriptionID: Option[String] = request.getQueryString("jobDescriptionID")

      val db = DBCompetency

      if (jobDescriptionID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") | Some("SuperUser") =>
            Ok(views.html.hrManager.addCompetencyHRManager
            (db.getAllCompetencies())(jobDescriptionID.get.toInt))
          case _ =>
            Redirect("/")
        }
      } else {
        Forbidden
      }

  }

  /**
    * Form for post request add Competency
    */
  val competencyAddForm = Form {
    tuple("competencyID" -> text,
      "rating" -> text,
      "jobDescriptionID" -> text
    )
  }

  /**
    * Action for addCompetencyJobDescription post form
    *
    * @return
    */
  def addCompetencyJobDescriptionPost() = Action {

    implicit request =>

      val (competencyID, rating, jobDescriptionID) = competencyAddForm.bindFromRequest().get

      val db = DBJobProfileCompetency

      db.addJobProfileCompetency(competencyID.toInt, rating.toInt, jobDescriptionID.toInt)

      Redirect(s"/hrManagerMain/jobDescription?id=$jobDescriptionID")

  }

  /**
    * Action for rendering matchingMain template
    *
    * @return
    */
  def matchingMain() = Action {
    implicit request =>
      val priv = request.session.get("privilege")
      val jobDescriptionID: Option[String] = request.getQueryString("jobDescriptionID")
      val called: Option[String] = request.getQueryString("called")

      if (jobDescriptionID.isDefined) {
        priv match {
          case None =>
            Redirect("/")
          case Some("HRManager") | Some("SuperUser") =>

            called match {

              case None =>
                Ok(views.html.hrManager.matchingMain(Nil)(jobDescriptionID.get.toInt))

              case Some("allCapped") =>
                val matching = matchingMethodsFacade
                val matchingCandidates =
                  matching.getListOfMachingCandidatesFromDB(jobDescriptionID.get.toInt)
                Ok(views.html.hrManager.matchingMain(matchingCandidates)(jobDescriptionID.get.toInt))

              case Some("unCapped") =>
                val matching = matchingMethodsFacade
                val matchingCandidates =
                  matching.getListOfMachingCandidatesFromDB(jobDescriptionID.get.toInt)
                Ok("unCapped")

              case Some("individuallyCapped") =>
                val matching = matchingMethodsFacade
                val matchingCandidates =
                  matching.getListOfMachingCandidatesFromDB(jobDescriptionID.get.toInt)
                Ok("indviduallyCapped")

            }
          case _ =>
            Redirect("/")
        }
      } else {
        Forbidden
      }
  }

  /**
    * form for sending jobDescriptionID
    */
  val matchingMainForm = Form {
    tuple(
      "jobDescriptionID" -> text,
      "actionType" -> text
    )
  }

  /**
    * Action for matchingMain post request.
    *
    * @return
    */
  def matchingMainPost() = Action {
    implicit request =>

      val (jobDescriptionsID, actionType) = matchingMainForm.bindFromRequest().get


      Redirect(s"/hrManagerMain/" +
        s"jobDescription/matchingMain?jobDescriptionID=$jobDescriptionsID&called=$actionType")

  }

  /**
    * Action for rendering viewMatchingCandidates template.
    *
    * @return
    */
  def viewMatchedCandidates() = Action {

    implicit request =>
      val priv = request.session.get("privilege")

      val id: Option[String] = request.getQueryString("id")
      val d1: Option[String] = request.getQueryString("d1")
      val d2: Option[String] = request.getQueryString("d2")
      val d3: Option[String] = request.getQueryString("d3")
      val jobDescriptionID: Option[String] = request.getQueryString("jobDescriptionID")


      val db = DBCandidate

      priv match {
        case None =>
          Redirect("/")
        case Some("HRManager") | Some("SuperUser") =>
          Ok(views.html.hrManager
            .viewMatchedCandidate(db.getCandidateByID(id.get.toInt).get)
            (d1.get.toDouble)(d2.get.toDouble)(d3.get.toDouble)(jobDescriptionID.get.toInt))
        case _ =>
          Redirect("/")
      }

  }

}
