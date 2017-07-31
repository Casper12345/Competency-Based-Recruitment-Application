package controllers

import persistenceAPI.DataBaseConnection.connectCompetency.DBCompetency
import persistenceAPI.DataBaseConnection.connectSkill.DBSkill
import play.api.data.Form
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._

/**
  * Controller methods for superUser part of the application.
  */
object SuperUser extends Controller {

  /**
    * Action for rendering superUserMain template
    *
    * @return
    */
  def superUserMain = Action {
    implicit request =>

      val userName = request.session.get("username")
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("SuperUser") =>
          Ok(views.html.superUser.superUserMain())
        case _ =>
          Redirect("/")
      }

  }

  /**
    * Action for rendering addSkill
    *
    * @return
    */
  def addSkill = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      val db = DBSkill

      priv match {
        case None =>
          Redirect("/")
        case Some("SuperUser") =>
          Ok(views.html.superUser.addSkill(db.getAllSkills()))
        case _ =>
          Redirect("/")
      }

  }

  /**
    * Form for addSkill post request
    */
  val skillForm = Form(
    "name" -> text
  )

  /**
    * post request for addSkill
    *
    * @return
    */
  def submitSkill = Action {

    implicit request =>

      val name = skillForm.bindFromRequest().get

      val db = DBSkill

      db.addSkill(name)

      Redirect("/superUserMain/addSkill")

  }

  /**
    * Form for addCompetency request
    *
    */
  val competencyForm = Form(
    "name" -> text
  )

  /**
    * Action for rendering addCompetency template
    *
    * @return
    */
  def addCompetency = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      val db = DBCompetency

      priv match {
        case None =>
          Redirect("/")
        case Some("SuperUser") =>
          Ok(views.html.superUser.addCompetency(db.getAllCompetencies()))
        case _ =>
          Redirect("/")
      }

  }

  /**
    * Form request for addCompetency.
    *
    * @return
    */
  def submitCompetency = Action {

    implicit request =>

      val name = competencyForm.bindFromRequest().get

      val db = DBCompetency

      db.addCompetency(name)

      Redirect("/superUserMain/addCompetency")

  }

}
