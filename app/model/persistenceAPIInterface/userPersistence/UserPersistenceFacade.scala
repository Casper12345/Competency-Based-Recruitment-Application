package model.persistenceAPIInterface.userPersistence

import persistenceAPI.DataBaseConnection.connectUser.DBConnectUser
import persistenceAPI.DataBaseConnection.objects.{HRManagerUser, RecruiterUser}

/**
  * Extends UserPersistenceFacadeTrait.
  *
  */
object UserPersistenceFacade extends UserPersistenceFacadeTrait {

  /**
    * Composition to DataBase API
    */
  val connectUser = DBConnectUser

  /**
    * Inserts a new user into the system.
    *
    * @param userName      Name of user
    * @param passWord      Password of user
    * @param userPrivilege Type of user privilege
    * @return boolean that signifies success
    */
  override def insertNewUser(userName: String, passWord: String, userPrivilege: String): Boolean =
    connectUser.insertNewUser(userName, passWord, userPrivilege)

  /**
    * Checks if password and username corresponds
    *
    * @param userName Name of user
    * @param passWord Password of user
    * @return boolean if password and username corresponds
    */
  override def checkUser(userName: String, passWord: String): Boolean =
    connectUser.checkUser(userName, passWord)

  /**
    * Gets the privilege of the user
    *
    * @param userName Name of user
    * @return Username as string
    */
  override def getPrivilegeByName(userName: String): String =
    connectUser.getPrivilegeByName(userName)

  /**
    * Gets the ID of a user by username.
    *
    * @param userName Name of user
    * @return user id as int.
    */
  override def getIDByUserName(userName: String): Int =
    connectUser.getIDByUserName(userName)

  /**
    * Gets all users of type Recruiter.
    *
    * @return list of RecruiterUser
    */
  override def getAllRecruiterUsers(): List[RecruiterUser] =
    connectUser.getAllRecruiterUsers()

  /**
    * Gets all users of type HR Manager
    *
    * @return list of HRManagerUser
    */
  override def getAllHRManagerUsers(): List[HRManagerUser] =
    connectUser.getAllHRManagerUsers()
}
