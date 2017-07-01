package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }


  val form = Form(
    tuple("username" -> text,
      "password" -> text

    )
  )
  def submit = Action{

    implicit request =>

      val (username, password) = form.bindFromRequest().get

      print(username +" "+ password )

      if(username == "Dadel"){
        Redirect("/login")
      }else{
        Redirect("/loginFailure")
      }

  }

  def loginFailure = Action{
    Ok(views.html.loginFailure())
  }


  def login = Action{
    Ok(views.html.login("NotOK")("Really"))
  }



}