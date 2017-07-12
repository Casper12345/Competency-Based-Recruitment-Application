package controllers

import model.DataBaseConnection.ConnectCompetency.DBCompetency
import model.DataBaseConnection.ConnectSkill.DBSkill
import play.api.data.Form
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._

/**
  * Created by Casper on 07/07/2017.
  */
object SuperUser extends Controller {

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

  val skillForm = Form(
    "name" -> text
  )

  def submitSkill = Action {

    implicit request =>

      val name = skillForm.bindFromRequest().get

      val db = DBSkill

      db.addSkill(name)

      Redirect("/superUserMain/addSkill")

  }

  val competencyForm = Form(
    "name" -> text
  )

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

  def submitCompetency = Action {

    implicit request =>

      val name = competencyForm.bindFromRequest().get

      val db = DBCompetency

      db.addCompetency(name)

      Redirect("/superUserMain/addCompetency")

  }

}
