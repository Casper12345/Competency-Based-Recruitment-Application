package model.messageSystemLogic

import java.util.Calendar

/**
  * Class for getting dateTime
  */
object DateTimeGet {

  /**
    * Method for getting dateTime
    *
    * @return Timestamp for SQL
    */
  def getTimeAsTimeStamp: java.sql.Timestamp = {

    val now = Calendar.getInstance()

    new java.sql.Timestamp(now.getTimeInMillis)

  }

}


