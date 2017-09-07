package integrationTests

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers.{contentAsString, _}

/**
  * Created by Casper on 05/09/2017.
  */
class SuperUserTest extends Specification {

  "SuperUser" should {

    /**
      * Tests the authentication system for SuperUser main page.
      * Using Fake Requests.
      */
    "redirect to index if session is invalid or not present" in new WithApplication {

      val withoutSession = route(FakeRequest(GET, "/superUserMain")).get

      val withInValidSession = route(FakeRequest(GET, "/superUserMain")
        .withSession("userID" -> "1",
          "privilege" -> "Recruiter",
          "username" -> "Casper")).get

      val withValidSession = route(FakeRequest(GET, "/superUserMain")
        .withSession("userID" -> "1",
          "privilege" -> "SuperUser",
          "username" -> "Casper")).get


      status(withoutSession) must equalTo(303)
      status(withInValidSession) must equalTo(303)
      status(withValidSession) must equalTo(200)

      contentAsString(withoutSession) must not contain "<title>Super User Main</title>"
      contentAsString(withValidSession) must contain("<title>Super User Main</title>")


    }

    "skill must get added to database and appear in table" in new WithApplication {

      val addSkillPost = route(FakeRequest(POST, "/superUserMain/addSkill")
        .withSession("userID" -> "1",
          "privilege" -> "SuperUser",
          "username" -> "Casper").
        withFormUrlEncodedBody(("name", "Django")))
        .get

      val addSkillGet = route(FakeRequest(GET, "/superUserMain/addSkill")
        .withSession("userID" -> "1",
          "privilege" -> "SuperUser",
          "username" -> "Casper"))
        .get


      contentAsString(addSkillGet) must contain("Django")

    }

  }

}
