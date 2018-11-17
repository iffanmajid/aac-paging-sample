package iffanmajid.aac.pagingsample

import androidx.recyclerview.widget.DiffUtil
import iffanmajid.aac.pagingsample.data.Job

/**
 * Created by Iffan-mbp13 on 17/11/18.
 * ilutfimajid@gmail.com
 */
class JobDiffUtilCallback : DiffUtil.ItemCallback<Job>() {

    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.endDate == newItem.endDate
        && oldItem.organizationName == newItem.organizationName
        && oldItem.positionTitle == newItem.positionTitle
        && oldItem.startDate == newItem.startDate
    }
}