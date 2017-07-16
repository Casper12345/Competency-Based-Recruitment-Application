package persistenceAPI.DataBaseConnection.ConnectUser

/**
  * Trait for DBConnectUser
  */
trait DBConnectUserTrait {

  def connect(): Unit

  def containsUser(userName: String): Boolean

  def getLatestUserId: Int

  def insertNewUser(userName: String, passWord: String, userPrivilege: String): Boolean

  def checkUser(userName: String, passWord: String): Boolean

  def closeConnection(): Unit

}
