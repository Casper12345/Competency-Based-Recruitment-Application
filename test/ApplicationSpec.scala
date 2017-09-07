import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "MainApp" should {
    /*
    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/wew")) must beNone
    }
    */
    "render the index page" in new WithApplication{

      val suMain = route(FakeRequest(GET, "/superUserMain")).get

      val home = route(FakeRequest(GET, "/superUserMain")
        .withSession("userID" -> "1",
          "privilege" -> "SuperUser",
          "username" -> "Casper")).get





      status(suMain) must equalTo(303)
      status(home) must equalTo(200)

      //println(suMain)
      println(contentAsString(home))

      //status(suMain) must equalTo()

      //contentType(home) must beSome.which(_ == "text/html")
      //contentAsString(home) must contain ("<head>")
    }


  }
}
