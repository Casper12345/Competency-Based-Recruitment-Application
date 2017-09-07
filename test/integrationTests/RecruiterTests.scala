package integrationTests

import org.specs2.mutable.{Specification, _}
import play.api.test._
import play.api.test.Helpers.{contentAsString, _}


/**
  * Created by Casper on 07/09/2017.
  */
class RecruiterTests extends Specification {

  "Recruiter" should {

    /**
      * Tests the authentication system for Recruiter main page.
      * Using Fake Requests.
      */
    "redirect to index if session is invalid or not present" in new WithApplication {

      val withoutSession = route(FakeRequest(GET, "/recruiterMain")).get

      val withInValidSession = route(FakeRequest(GET, "/recruiterMain")
        .withSession("userID" -> "2",
          "privilege" -> "HRManager",
          "username" -> "John")).get

      val withValidSession = route(FakeRequest(GET, "/recruiterMain")
        .withSession("userID" -> "3",
          "privilege" -> "Recruiter",
          "username" -> "Paul")).get


      status(withoutSession) must equalTo(303)
      status(withInValidSession) must equalTo(303)
      status(withValidSession) must equalTo(200)

      contentAsString(withoutSession) must not contain "<title>Recruiter Main</title>"
      contentAsString(withValidSession) must contain("<title>Recruiter Main</title>")


    }

    "name and surname must appear in table" in new WithApplication {

      val addProfilePost = route(FakeRequest(POST, "/recruiterMain/createCandidateProfile")
        .withSession("userID" -> "3",
          "privilege" -> "Recruiter",
          "username" -> "Paul").
        withFormUrlEncodedBody(
          ("name", "Jim"),
          ("surname", "Thompson"),
          ("educationName", "Educated"),
          ("currentJobTitle", "Genius"),
          ("educationLevel", "1"),
          ("experienceLevel", "2"))).get


      val viewCandidate = route(FakeRequest(GET, "/recruiterMain/viewCandidate")
        .withSession("userID" -> "3",
          "privilege" -> "Recruiter",
          "username" -> "Paul")).get


      contentAsString(viewCandidate) must contain("Jim")
      contentAsString(viewCandidate) must contain("Thompson")


    }

  }

}
