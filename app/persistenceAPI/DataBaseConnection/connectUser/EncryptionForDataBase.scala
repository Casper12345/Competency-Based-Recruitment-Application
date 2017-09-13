package persistenceAPI.DataBaseConnection.connectUser

import com.github.t3hnar.bcrypt._

/**
  * Encryption for data base using bcrypt
  */
object EncryptionForDataBase {

  /**
    * Method for encryption password string.
    *
    * @param password
    * @return
    */
  def encryptPassword(password: String): String = password.bcrypt

}
