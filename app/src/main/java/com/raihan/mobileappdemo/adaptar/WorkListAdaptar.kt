package com.raihan.mobileappdemo.adaptar

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.raihan.mobileappdemo.R
import com.raihan.mobileappdemo.model.Movie
import com.raihan.mobileappdemo.model.Work
import com.raihan.mobileappdemo.room.RoomViewModel
import java.lang.Exception
import java.util.ArrayList

class WorkListAdaptar(
    private var movieList: ArrayList<Work>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<Work>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = movieList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: Work?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_movie_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val menu_name: TextView? = holder.itemView.findViewById(R.id.menu_name)
        val menu_desc: TextView? = holder.itemView.findViewById(R.id.menu_desc)
        val menu_watch: TextView? = holder.itemView.findViewById(R.id.menu_watch)
        val menu_icon: ImageView? = holder.itemView.findViewById(R.id.menu_icon)

        menu_name!!.text = currentItem.title
        menu_desc!!.text = currentItem.description
        currentItem.imageId?.let { menu_icon?.setImageResource(it) }


        menu_name.setOnClickListener {
            val selectedList: Work = currentItem
            listener.onItemClick(selectedList)

        }
    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        // notifyDataSetChanged()
        // Log.e("requestFilterList--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = movieList
                } else {

                    val resultList = ArrayList<Work>()
                    for (row in movieList) {
                        if (
                            row.title.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.description.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                )
                        ) {
                            resultList.add(row)
                        }
                    }
                    requestFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = requestFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<Work>
                notifyDataSetChanged()
            }

        }
    }
}