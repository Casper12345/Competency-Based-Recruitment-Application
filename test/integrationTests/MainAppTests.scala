package integrationTests

import org.specs2.mutable.{Specification, _}
import play.api.data.Forms.tuple
import play.api.test._
import play.api.test.Helpers.{contentAsString, _}

/**
  * Created by Casper on 07/09/2017.
  */
class MainAppTests extends Specification {

  "MainApp" should {

    /**
      * Tests the authentication system for Recruiter main page.
      * Using Fake Requests.
      */
    "redirect to index if password or user name doesnt correspond" in new WithApplication {

      val withRealPass = route(FakeRequest(POST, "/").
        withFormUrlEncodedBody(
          ("username", "Casper"),
          ("password", "123"))).get


      val withBadPass = route(FakeRequest(POST, "/").
        withFormUrlEncodedBody(
          ("username", "Casper"),
          ("password", "badpass"))).get


      println(withRealPass)
      println(contentAsString(withBadPass))



      status(withoutSession) must equalTo(303)
      status(withInValidSession) must equalTo(303)
      status(withValidSession) must equalTo(200)

      contentAsString(withoutSession) must not contain "<title>Recruiter Main</title>"
      contentAsString(withValidSession) must contain("<title>Recruiter Main</title>")

    }


  }
}
