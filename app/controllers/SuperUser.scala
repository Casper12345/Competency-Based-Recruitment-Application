package controllers

import model.DataBaseConnection.ConnectCompetency.DBCompetency
import model.DataBaseConnection.ConnectSkill.DBSkill
import model.DataBaseConnection.DBMain
import play.api.data.Form
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._

/**
  * Created by Casper on 07/07/2017.
  */
object SuperUser extends Controller {

  def superUserMain = Action {

    Ok(views.html.superUser.superUserMain())
  }

  def addSkill = Action {

    val db = DBSkill

    val allSkill = db.getAllSkills()

    Ok(views.html.superUser.addSkill(allSkill))
  }

  val skillForm = Form(
    "name" -> text
  )

  def submitSkill = Action {

    implicit request =>

      val name = skillForm.bindFromRequest().get

      println(name)

      val db = DBSkill

      db.addSkill(name)

      Redirect("/superUserMain/addSkill")

  }

  val competencyForm = Form(
    "name" -> text
  )

  def addCompetency = Action {

    val db = DBCompetency

    val allCompetency = db.getAllCompetencies()

    Ok(views.html.superUser.addCompetency(allCompetency))
  }

  def submitCompetency = Action {

    implicit request =>

      val name = competencyForm.bindFromRequest().get

      println(name)

      val db = DBCompetency

      db.addCompetency(name)

      Redirect("/superUserMain/addCompetency")

  }

}
