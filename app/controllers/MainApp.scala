package controllers

import model.DataBaseConnection.DBConnectUser
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

      print(username + " " + password)

      if (db.checkUser(username, password)) {

        Redirect("/recruiterMain")

      } else {
        Redirect("/")
      }
  }

  def helper = Action {

    implicit request =>

      val name: Option[String] = request.getQueryString("name")
      val age: Option[String] = request.getQueryString("age")

      println(name.get + age.get)

      Ok(views.html.Helper())

  }



}