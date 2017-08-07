/**
 * Created by Casper on 07/08/2017.
 */


function checkUser() {

  var recipient = $("#receiverID").attr("value");
  var subject = document.forms["messageForm"]["subject"].value;
  var message = document.forms["messageForm"]["messageBody"].value;

  if (recipient == 0) {
    alert("Recipient Missing");
  } else if (subject == "") {
    alert("Subject Missing")
  } else if (message == "") {
    alert("Message Missing")
  } else {
    alert("Message Sent");
    $('#messageForm').submit();
  }

}

function chooseUser(userID) {
  $("#receiverID").attr("value", userID);
}