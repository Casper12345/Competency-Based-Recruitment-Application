package integrationTests

import org.specs2.mutable.{Specification, _}
import play.api.test._
import play.api.test.Helpers.{contentAsString, _}

/**
  * Created by Casper on 07/09/2017.
  */
class HRManagerTests extends Specification {

  "HRManager" should {

    /**
      * Tests the authentication system for HRManager main page.
      * Using Fake Requests.
      */
    "redirect to index if session is invalid or not present" in new WithApplication {

      val withoutSession = route(FakeRequest(GET, "/hrManagerMain")).get

      val withInValidSession = route(FakeRequest(GET, "/hrManagerMain")
        .withSession("userID" -> "3",
          "privilege" -> "Recruiter",
          "username" -> "Paul")).get

      val withValidSession = route(FakeRequest(GET, "/hrManagerMain")
        .withSession("userID" -> "2",
          "privilege" -> "HRManager",
          "username" -> "John")).get

      status(withoutSession) must equalTo(303)
      status(withInValidSession) must equalTo(303)
      status(withValidSession) must equalTo(200)

      contentAsString(withoutSession) must not contain "<title>HR Manager Main</title>"
      contentAsString(withValidSession) must contain("<title>HR Manager Main</title>")


    }

    "job title must appear in table" in new WithApplication {

      val addDescriptionPost = route(FakeRequest(POST, "/hrManagerMain/createJobDescription")
        .withSession("userID" -> "2",
          "privilege" -> "HRManager",
          "username" -> "John").
        withFormUrlEncodedBody(
          ("jobTitle", "Manager"),
          ("educationName", "Biology"),
          ("educationLevel", "1"),
          ("experienceLevel", "2"))).get


      val viewDescription = route(FakeRequest(GET, "/hrManagerMain/viewJobDescription")
        .withSession("userID" -> "2",
          "privilege" -> "HRManager",
          "username" -> "John")).get


      contentAsString(viewDescription) must contain("Manager")


    }

  }

}
