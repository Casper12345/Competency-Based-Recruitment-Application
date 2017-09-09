package persistenceAPI.DataBaseConnection.connectUser

/**
  * Decryption for password
  */

import com.github.t3hnar.bcrypt._

object DecryptPassword {

  def checkPassword(password: String, encryptedString: String): Boolean =
    password.isBcrypted(encryptedString)

}
