package controllers

import persistenceAPI.DataBaseConnection.ConnectCandidate.DBCandidate
import persistenceAPI.DataBaseConnection.ConnectUser.DBConnectUser
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._


object MainApp extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  val form = Form(
    tuple("username" -> text,
      "password" -> text

    )
  )

  def submit = Action {

    implicit request =>

      val (username, password) = form.bindFromRequest().get

      val db = DBConnectUser

      if (db.checkUser(username, password)) {

        val privilege = db.getPrivilegeByName(username)

        privilege match {
          case "Recruiter" =>
            Redirect("/recruiterMain").withSession(
              "privilege" -> privilege,
              "username" -> username)

          case "HRManager" =>
            Redirect("/hrManagerMain").withSession(
              "privilege" -> privilege,
              "username" -> username)

          case "SuperUser" =>
            Redirect("/superUserMain").withSession(
              "privilege" -> privilege,
              "username" -> username)

          case _ =>
            NotFound
        }

      } else {
        Redirect("/")
      }
  }

  def logout = Action{

    Redirect("/").withNewSession

  }

  def helper = Action {

    val db = DBCandidate

    Ok(views.html.Helper(db.getCandidateByID(1).get))

  }


}