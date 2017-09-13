package controllers

/**
  * String factory for defining privilege as a string.
  */
object PrivilegeStringFactory {

  def getPrivilege(priv: Int): String = priv match {
    case 1 => "Recruiter"
    case 2 => "HRManager"
  }

}
