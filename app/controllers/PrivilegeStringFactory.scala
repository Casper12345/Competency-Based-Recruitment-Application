package controllers

/**
  * Created by Casper on 05/09/2017.
  */
object PrivilegeStringFactory {

  def getPrivilege(priv: Int): String = priv match {
    case 1 => "Recruiter"
    case 2 => "HRManager"
  }

}
