package persistenceAPI.DataBaseConnection.connectUser

import com.github.t3hnar.bcrypt._

/**
  * Encryption for data base using bcrypt
  */
object EncryptionForDataBase {

  def encryptPassword(password: String): String = password.bcrypt

}
