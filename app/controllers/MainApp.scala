package controllers

import model.DataBaseConnection.ConnectUser.DBConnectUser
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

    Ok(views.html.Helper(List[String]("a","b","c","d","e","f","g","h")))

  }

  val helperForm = Form{
    tuple("skillID" -> text,
          "skillLevel" -> text
    )
  }

  def helperPost = Action {
    implicit request =>

      val (skillID, skillLevel) = helperForm.bindFromRequest().get

      println(skillID +" " + skillLevel)
      Ok(views.html.Helper(List[String]("a","b","c","d","e","f","g","h")))

  }


}