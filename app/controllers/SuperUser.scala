package controllers

import controllers.Application.Redirect
import model.DataBaseConnection.{DBConnectUser, DBMain}
import play.api.data.Form
import play.api.data.Forms.tuple
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._

/**
  * Created by Casper on 07/07/2017.
  */
object SuperUser extends Controller{

  def superUserMain = Action {

    Ok(views.html.superUser.superUserMain())
  }

  def addSkill = Action {

    val db = DBMain

    val allSkill = db.getAllSkills()

    Ok(views.html.superUser.addSkill(allSkill))
  }

  val form = Form(
    "name" -> text
  )

  def submit = Action {

    implicit request =>

      val name = form.bindFromRequest().get

      println(name)

      val db = DBMain

      db.addSkill(name)

      Redirect("/superUserMain")



  }


}
