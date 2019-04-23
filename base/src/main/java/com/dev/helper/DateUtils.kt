package com.dev.helper

import android.text.format.DateFormat
import android.text.format.DateUtils
import java.util.*

class DateUtils {

    companion object {
//        fun getResolvedDate(timeInMillis: Long){
//            val isToday = DateUtils.isToday(timeInMillis)
//            if(isToday){
//
//            }else if(if)
//        }

        fun getSmsTodayYestFromMilli(msgTimeMillis: Long): String {

            val messageTime = Calendar.getInstance()
            messageTime.timeInMillis = msgTimeMillis
            // get Currunt time
            val now = Calendar.getInstance()

            val strTimeFormate = "h:mm aa"
            val strDateFormate = "dd/MM/yyyy\nh:mm aa"

            return if (now.get(Calendar.DATE) == messageTime.get(Calendar.DATE)
                &&
                now.get(Calendar.MONTH) == messageTime.get(Calendar.MONTH)
                &&
                now.get(Calendar.YEAR) == messageTime.get(Calendar.YEAR)
            ) {

                "Today\n" + DateFormat.format(strTimeFormate, messageTime)

            } else if (now.get(Calendar.DATE) - messageTime.get(Calendar.DATE) == 1
                &&
                now.get(Calendar.MONTH) == messageTime.get(Calendar.MONTH)
                &&
                now.get(Calendar.YEAR) == messageTime.get(Calendar.YEAR)
            ) {
                "Yesterday\n " + DateFormat.format(strTimeFormate, messageTime)
            } else {
                DateFormat.format(strDateFormate, messageTime).toString()
            }
        }

    }
}