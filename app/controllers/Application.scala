package controllers

import model.DataBaseConnection.DBConnectUser
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


  def recruiterMain = Action {
    Ok(views.html.recruiter.recruiterMain("Welcome")("Really"))
  }


  def createCandidateProfile = Action {
    Ok(views.html.createCandidateProfile())
  }


  def helper = Action {
    Ok(views.html.Helper(List[String]("a","b","c","d","e","f","g")))
  }


  val candidateForm = Form(
    tuple(
      "name" -> text,
      "education" -> text,
      "currentJobTitle" -> text,
      "subject" -> text

    )
  )

  def candidateFormSubmit = Action {
    implicit request =>
      val (name, education, currentJobTitle, subject) = candidateForm.bindFromRequest().get
      print(name, education)
      Redirect("/recruiterMain")
  }

  def myTemplate = Action {

    Ok(views.html.MyTemplate())
  }



}