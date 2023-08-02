package com.example.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.models.ListParckModel
import com.example.parkingresarvation.R
import com.google.android.material.imageview.ShapeableImageView

class ParckListAdapteur (val listData : List<ListParckModel>, val cliclistener: ClickListner) : RecyclerView.Adapter<ParckListAdapteur.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParckListAdapteur.MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listData[position]

        holder.commune.text= currentItem.commune
        holder.nom.text=currentItem.nom
        holder.distance.text= currentItem.distance
        holder.temps.text=currentItem.temps
        holder.taux.text= currentItem.taux
        holder.etat.text=currentItem.etat
        holder.titleImage.setImageResource(currentItem.titleImage)

        holder.itemView.setOnClickListener{
            cliclistener.onItemClick(currentItem)
        }

    }

    override fun getItemCount(): Int {
       return listData.size
    }
    interface ClickListner {
        fun onItemClick(listData:ListParckModel ){

        }
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        var titleImage: ShapeableImageView
        var etat : TextView
        var taux : TextView
        var nom : TextView
        var commune : TextView
        var distance : TextView
        var temps : TextView

            init {
                titleImage = view.findViewById(R.id.image)
                etat = view.findViewById(R.id.etat)
                taux =view.findViewById(R.id.taux)
                nom = view.findViewById(R.id.nom)
                commune = view.findViewById(R.id.commune)
                distance= view.findViewById(R.id.distance)
                temps = view.findViewById(R.id.temps)
            }
    }
}