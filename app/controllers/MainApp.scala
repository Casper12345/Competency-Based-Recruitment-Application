package controllers

import persistenceAPI.DataBaseConnection.connectUser.DBConnectUser
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._


/**
  * Controller methods for mainApp entrance point
  */
object MainApp extends Controller {

  /**
    * Action for rendering Index template
    *
    * @return
    */
  def index = Action {
    Ok(views.html.index())
  }

  /**
    * Form request for sending login form
    */
  val form = Form(
    tuple("username" -> text,
      "password" -> text

    )
  )

  /**
    * Action for submitting login form.
    *
    * @return
    */
  def submit = Action {

    implicit request =>

      val (username, password) = form.bindFromRequest().get

      val db = DBConnectUser

      if (db.checkUser(username, password)) {

        val privilege = db.getPrivilegeByName(username)
        val userID = db.getIDByUserName(username)

        privilege match {
          case "Recruiter" =>
            Redirect("/recruiterMain").withSession(
              "userID" -> userID.toString,
              "privilege" -> privilege,
              "username" -> username
            )

          case "HRManager" =>
            Redirect("/hrManagerMain").withSession(
              "userID" -> userID.toString,
              "privilege" -> privilege,
              "username" -> username)

          case "SuperUser" =>
            Redirect("/superUserMain").withSession(
              "userID" -> userID.toString,
              "privilege" -> privilege,
              "username" -> username)

          case _ =>
            NotFound
        }

      } else {
        Redirect("/")
      }
  }

  /**
    * Action for rendering logout template
    * Deletes  session cookies
    *
    * @return
    */
  def logout = Action {

    Redirect("/").withNewSession

  }


  def helper = Action {

    Ok(views.html.Helper())

  }


}