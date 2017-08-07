package controllers

import persistenceAPI.DataBaseConnection.connectCandidate.{DBCandidate, DBCandidateCompetency, DBCandidateSkill}
import persistenceAPI.DataBaseConnection.connectChatMessage.DBChatMessage
import persistenceAPI.DataBaseConnection.connectCompetency.DBCompetency
import persistenceAPI.DataBaseConnection.connectSkill.DBSkill
import persistenceAPI.DataBaseConnection.connectUser.{DBConnectUser, DBUserRecievedMessage, DBUserSentMessage}
import play.api.data.Form
import play.api.data.Forms.tuple
import play.api.mvc.{Action, Controller}
import play.api.data.Forms._


/**
  * Controller methods for the recruiter part of the application
  */

object Recruiter extends Controller {

  /**
    * Actopn for rendering recruiterMain template
    *
    * @return
    */
  def recruiterMain = Action {
    implicit request =>
      val userName = request.session.get("username")
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>
          Ok(views.html.recruiter.recruiterMain())
        case _ =>
          Redirect("/")
      }

  }

  /**
    * Action for rendering createCandidateProfile template
    *
    * @return
    */
  def createCandidateProfile = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>
          Ok(views.html.recruiter.createCandidateProfile())
        case _ =>
          Redirect("/")
      }
  }

  /**
    * Form for sending candidate post request
    */
  val candidateForm = Form(
    tuple(
      "name" -> text,
      "surname" -> text,
      "educationName" -> text,
      "currentJobTitle" -> text,
      "educationLevel" -> text,
      "experienceLevel" -> text
    )
  )

  /**
    * Action for form request Candidate
    *
    * @return
    */
  def candidateFormSubmit = Action {
    implicit request =>

      val (name, surname, educationName, currentJobTitle,
      educationLevel, experienceLevel) = candidateForm.bindFromRequest().get

      val db = DBCandidate

      db.addCandidate(name, surname, educationName, currentJobTitle, educationLevel, experienceLevel)

      Redirect("/recruiterMain")

  }

  /**
    * Action for rendering viewCandidate template
    *
    * @return
    */
  def viewCandidate() = Action {
    implicit request =>
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>

          val db = DBCandidate

          Ok(views.html.recruiter.viewCandiate(db.getAllCandidates()))
        case _ =>
          Redirect("/")
      }
  }

  /**
    * Action for rendering candidate template
    *
    * @return
    */
  def candidate() = Action {
    implicit request =>
      val priv = request.session.get("privilege")
      val id: Option[String] = request.getQueryString("id")

      if (id.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") | Some("SuperUser") =>

            val db = DBCandidate

            Ok(views.html.recruiter.candidate(db.getCandidateByID(id.get.toInt).get))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }
  }

  /**
    * Action for rendering addSkillCandidate template
    *
    * @return
    */
  def addSkillCandidate() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")


      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") | Some("SuperUser") =>

            val db = DBSkill

            Ok(views.html.recruiter.addSkillRecruiter(db.getAllSkills())(candidateID.get.toInt))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }
  }

  /* Form for addSkill post request
   */
  val skillAddForm = Form {
    tuple("skillID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }

  /**
    * Action for addSkillCandidate post request
    *
    * @return
    */
  def addSkillCandidatePost() = Action {

    implicit request =>

      val (skillID, rating, candidateID) = skillAddForm.bindFromRequest().get

      val db = DBCandidateSkill

      println(skillID + " " + rating + " " + candidateID)

      db.addCandidateSkill(skillID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/recruiterMain/candidate?id=$candidateID")

  }

  /**
    * Action for rendering addCompetencyCandidate template
    *
    * @return
    */
  def addCompetencyCandidate() = Action {

    implicit request =>
      val priv = request.session.get("privilege")
      val candidateID: Option[String] = request.getQueryString("CandidateID")

      if (candidateID.isDefined) {

        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") | Some("SuperUser") =>

            val db = DBCompetency

            Ok(views.html.recruiter.addCompetencyRecruiter(db.getAllCompetencies())(candidateID.get.toInt))
          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }

  }

  /**
    * Form for addCompetency post request
    */
  val competencyAddForm = Form {
    tuple("competencyID" -> text,
      "rating" -> text,
      "candidateID" -> text
    )
  }

  /**
    * addCompetencyCandidate post request
    *
    * @return
    */
  def addCompetencyCandidatePost() = Action {

    implicit request =>

      val (competencyID, rating, candidateID) = competencyAddForm.bindFromRequest().get

      val db = DBCandidateCompetency

      db.addCandidateCompetency(competencyID.toInt, rating.toInt, candidateID.toInt)

      Redirect(s"/recruiterMain/candidate?id=$candidateID")
  }

  /**
    * Check db if there are any messages
    *
    * @return
    */
  def inbox() = Action {

    implicit request =>
      val userID = request.session.get("userID")
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>

          val dbUserReceivedMessage = DBUserRecievedMessage

          Ok(views.html.recruiter.recruiterInbox(
            dbUserReceivedMessage.
              getAllUserReceivedMessageByID(userID.get.toInt))
          (dbUserReceivedMessage.countUnreadMessagesByUserID(userID.get.toInt)))


        case _ =>
          Redirect("/")
      }

  }

  /**
    *
    * @return
    */
  def sentInbox() = Action {

    implicit request =>
      val userID = request.session.get("userID")
      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>

          val dbUserSentMessage = DBUserSentMessage
          val dbUserReceivedMessage = DBUserRecievedMessage

          Ok(views.html.recruiter.
            recruiterSentInbox(dbUserSentMessage.
              getAllUserSentMessageByID(userID.get.toInt))
            (dbUserReceivedMessage.countUnreadMessagesByUserID(userID.get.toInt)))

        case _ =>
          Redirect("/")
      }


  }

  /**
    *
    * @return
    */
  def readMessage() = Action {

    implicit request =>

      val priv = request.session.get("privilege")
      val chatMessageID: Option[String] = request.getQueryString("ChatMessageID")
      val userID = request.session.get("userID")

      if (chatMessageID.isDefined) {
        priv match {
          case None =>
            Redirect("/")
          case Some("Recruiter") | Some("SuperUser") =>

            val db = DBChatMessage

            val dbUserReceivedMessage = DBUserRecievedMessage

            db.setReadToTrueByID(chatMessageID.get.toInt)

            Ok(views.html.recruiter.
              recruiterReadMessage(db.getChatMessageByID(chatMessageID.get.toInt).get)
              (dbUserReceivedMessage.countUnreadMessagesByUserID(userID.get.toInt)))

          case _ =>
            Redirect("/")
        }

      } else {
        Forbidden
      }

  }

  /**
    *
    * @return
    */
  def sendMessage() = Action {
    implicit request =>

      val priv = request.session.get("privilege")

      priv match {
        case None =>
          Redirect("/")
        case Some("Recruiter") | Some("SuperUser") =>

          val db = DBConnectUser

          Ok(views.html.recruiter.recruiterSendMessage(db.getAllHRManagerUsers()))

        case _ =>
          Redirect("/")
      }
  }


  val sendMessageForm = Form {
    tuple(
      "subject" -> text,
      "messageBody" -> text,
      "receiverID" -> text

    )
  }

  /**
    * Test with fake request !!
    *
    * @return
    */
  def sendMessagePost() = Action {

    // TODO: test with fake request

    implicit request =>

      val senderUserID = request.session.get("userID").get

      val (subject, messageBody, receiverID) = sendMessageForm.bindFromRequest().get

      print(subject + messageBody + receiverID)

      val db = DBChatMessage

      val dbUserReceivedMessage = DBUserRecievedMessage

      db.createSentNewChatMessage(senderUserID.toInt, receiverID.toInt, subject, messageBody)

      Redirect("inbox")
  }

}
