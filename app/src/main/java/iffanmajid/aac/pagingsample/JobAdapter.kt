package iffanmajid.aac.pagingsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import iffanmajid.aac.pagingsample.data.Job
import iffanmajid.aac.pagingsample.utils.toLocalDate
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Iffan-mbp13 on 18/11/18.
 * ilutfimajid@gmail.com
 */
class JobAdapter : PagedListAdapter<Job, JobViewHolder>(JobDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val item = getItem(position)

        holder.itemView.apply {
            position_title.text = item?.positionTitle
            organization_name.text = item?.organizationName
            date_start.text = item?.startDate?.toLocalDate()
            date_end.text = item?.endDate?.toLocalDate()
        }
    }
}