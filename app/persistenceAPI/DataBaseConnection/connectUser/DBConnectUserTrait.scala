package persistenceAPI.DataBaseConnection.connectUser

/**
  * Trait for DBConnectUser
  * Data Base API for table Users
  */
trait DBConnectUserTrait {
  /**
    * Checks if table contains user by userName
    * To avoid duplicate users
    *
    * @param userName
    * @return
    */
  def containsUser(userName: String): Boolean

  /**
    * Inserts new user into table User
    *
    * @param userName
    * @param passWord
    * @param userPrivilege
    * @return
    */
  def insertNewUser(userName: String, passWord: String, userPrivilege: String): Boolean

  /**
    * Checks if user exists and password corresponds for login
    *
    * @param userName
    * @param passWord
    * @return
    */
  def checkUser(userName: String, passWord: String): Boolean

  /**
    * Method for getting userPrivilege by userName
    *
    * @param userName
    * @return
    */
  def getPrivilegeByName(userName: String): String

}
