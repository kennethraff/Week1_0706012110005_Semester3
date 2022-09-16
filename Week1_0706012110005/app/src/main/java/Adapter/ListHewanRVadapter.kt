package Adapter

import Database.GlobalVar
import Interface.CardListener
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.week1_0706012110005.DataActivity
import com.example.week1_0706012110005.R
import com.example.week1_0706012110005.databinding.HewanCardBinding
import model.Animal

class ListAnimalRvAdapter(val listUser: ArrayList<Animal>,val cardListener: CardListener):
    RecyclerView.Adapter<ListAnimalRvAdapter.viewHolder>() {
    private var position = -1
    class viewHolder(itemView: View,val cardListener: CardListener): RecyclerView.ViewHolder(itemView) {

        //bind
        val binding = HewanCardBinding.bind(itemView)
        fun setData(data:Animal){
            binding.namaHewanTextViewHewanCard.text= data.nama
            binding.jenisHewanTextViewHewanCard.text=data.jenis
            binding.usiaHewanTextViewHewanCard.text=data.usia
            binding.deletebuttonHewanCard.setOnClickListener {
                cardListener.onCardClick(true, adapterPosition)
            }
            binding.editbuttonHewanCard.setOnClickListener {
                cardListener.onCardClick(false, adapterPosition)
            }
//            itemView.setOnClickListener{
//                cardListener1.onCardClick(adapterPosition)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAnimalRvAdapter.viewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.hewan_card, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: ListAnimalRvAdapter.viewHolder, position: Int) {
        holder.setData(listUser[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}