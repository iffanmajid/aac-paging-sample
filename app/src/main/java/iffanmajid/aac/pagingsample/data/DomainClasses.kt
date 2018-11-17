package iffanmajid.aac.pagingsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Iffan-mbp13 on 17/11/18.
 * ilutfimajid@gmail.com
 */
@Entity
data class Job(@PrimaryKey val id: String,
               @SerializedName("position_title") val positionTitle: String,
               @SerializedName("organization_name") val organizationName: String,
               @SerializedName("start_date") val startDate: String,
               @SerializedName("end_date") val endDate: String)