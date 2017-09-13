package persistenceAPI.DataBaseConnection.connectUser

/**
  * Decryption for password
  */

import com.github.t3hnar.bcrypt._

object DecryptPassword {

  /**
    * Method for checking the decrypted password string
    *
    * @param password
    * @param encryptedString
    * @return
    */
  def checkPassword(password: String, encryptedString: String): Boolean =
    password.isBcrypted(encryptedString)

}
