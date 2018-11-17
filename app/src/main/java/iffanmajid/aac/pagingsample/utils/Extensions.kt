package iffanmajid.aac.pagingsample.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Iffan-mbp13 on 17/11/18.
 * ilutfimajid@gmail.com
 */
fun String.toLocalDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}
