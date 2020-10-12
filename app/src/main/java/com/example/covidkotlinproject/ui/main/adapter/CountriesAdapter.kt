package com.example.covidkotlinproject.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.utils.NumberUtils
import com.example.covidkotlinproject.utils.NumberUtils.getCountryFlag
import kotlinx.android.synthetic.main.item_list_countries.view.*
import kotlinx.android.synthetic.main.item_list_countries.view.flag
import kotlinx.android.synthetic.main.item_list_countries.view.tv_active
import kotlinx.android.synthetic.main.item_list_countries.view.tv_deaths
import kotlinx.android.synthetic.main.item_list_countries.view.tv_recovered
import kotlinx.android.synthetic.main.item_list_countries.view.tv_total

class CountriesAdapter(
    private val context: Context?,
    private val list: List<CountriesModelEntity>,
    private val filter : Int
): RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: CountriesModelEntity, position: Int, filter: Int) {

            itemView.country_name.text = item.title
            itemView.tv_total.text = NumberUtils.numberFormat(item.total_cases)
            itemView.tv_active.text = NumberUtils.numberFormat(item.total_active_cases)
            itemView.tv_recovered.text = NumberUtils.numberFormat(item.total_recovered)
            itemView.tv_deaths.text = NumberUtils.numberFormat(item.total_deaths)
            itemView.tv_new_cases.text = "+ " + NumberUtils.numberFormat(item.total_new_cases_today)
            itemView.tv_new_deaths.text = "+ " + NumberUtils.numberFormat(item.total_new_deaths_today)
            itemView.tv_percent.text = "%.2f".format(((item.total_recovered!! + item.total_deaths!!)*100.toDouble())/item.total_cases!!) + " %"

            Glide.with(itemView.context)
                .load(getCountryFlag(item.code!!))
                .placeholder(android.R.color.transparent)
                .centerCrop()
                .into(itemView.flag)



            itemView.setOnClickListener {
                NumberUtils.setLastFilter(itemView.context, filter)
                NumberUtils.setLastPosition(itemView.context,position)

                var bundle = bundleOf("countryCode" to item.code)
                it.findNavController()
                    .navigate(R.id.action_navigation_home_to_country_detail, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val position = position
        val filter = filter
        holder.bindView(item,position,filter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var initMode : String? = NumberUtils.getMode(context!!)
        var itemView : View

        itemView = if(initMode == "night"){
            LayoutInflater.from(context).inflate(R.layout.item_list_countries, parent, false)
        }else{
            LayoutInflater.from(context).inflate(R.layout.item_list_countries_day, parent, false)
        }
        return ViewHolder(itemView)
    }

}