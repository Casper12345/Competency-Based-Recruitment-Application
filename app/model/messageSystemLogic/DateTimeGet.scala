package model.messageSystemLogic

import java.util.Calendar

/**
  * Created by Casper on 07/08/2017.
  */
object DateTimeGet {

  def getTimeAsTimeStamp: java.sql.Timestamp = {

    val now = Calendar.getInstance()

    new java.sql.Timestamp(now.getTimeInMillis)

  }

}


