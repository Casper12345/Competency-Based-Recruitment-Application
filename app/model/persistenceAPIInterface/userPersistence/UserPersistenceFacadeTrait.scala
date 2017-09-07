package model.persistenceAPIInterface.userPersistence

import persistenceAPI.DataBaseConnection.objects.{HRManagerUser, RecruiterUser}

/**
  * Created by Casper on 07/09/2017.
  */
trait UserPersistenceFacadeTrait {

  /**
    * Inserts a new user into the system.
    *
    * @param userName      Name of user
    * @param passWord      Password of user
    * @param userPrivilege Type of user privilege
    * @return boolean that signifies success
    */
  def insertNewUser(userName: String, passWord: String, userPrivilege: String): Boolean

  /**
    * Checks if password and username corresponds
    *
    * @param userName Name of user
    * @param passWord Password of user
    * @return boolean if password and username corresponds
    */
  def checkUser(userName: String, passWord: String): Boolean

  /**
    * Gets the privilege of the user
    *
    * @param userName Name of user
    * @return Username as string
    */
  def getPrivilegeByName(userName: String): String

  /**
    * Gets the ID of a user by username.
    *
    * @param userName Name of user
    * @return user id as int.
    */
  def getIDByUserName(userName: String): Int

  /**
    * Gets all users of type Recruiter.
    *
    * @return list of RecruiterUser
    */
  def getAllRecruiterUsers(): List[RecruiterUser]

  /**
    * Gets all users of type HR Manager
    *
    * @return list of HRManagerUser
    */
  def getAllHRManagerUsers(): List[HRManagerUser]

}
