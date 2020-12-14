package com.vidamrr.basededatosejemplo

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jass.proyecto.ClickListener
import com.jass.proyecto.LongClickListener
import com.jass.proyecto.R
import com.jass.proyecto.Registro


class AdaptadorCustom(items:ArrayList<Registro>, var listener:ClickListener, var longClickListener: LongClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {


    var items: ArrayList<Registro>? = null
    var multiSeleccion = false

    var itemsSeleccionados:ArrayList<Int>? = null
    var viewHolder:ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_registro,parent,false)
        viewHolder = ViewHolder(vista, listener, longClickListener)

        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)

        holder.NombreProducto?.text = item?.NombreProducto
        holder.Id?.text = item?.Id
        holder.Precio?.text = item?.Precio
        holder.Cantidad?.text = item?.Cantidad
        holder.Total?.text = item?.Total

        if(itemsSeleccionados?.contains(position)!!){
            holder.vista.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    fun iniciarActionMode(){
        multiSeleccion = true
    }

    fun destruirActionMode(){
        multiSeleccion = false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode(){
        // eliminar elementos seleccionados
        for(item in itemsSeleccionados!!){
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun seleccionarItem(index:Int){
        if(multiSeleccion){
            if(itemsSeleccionados?.contains(index)!!){
                itemsSeleccionados?.remove(index)
            }else{
                itemsSeleccionados?.add(index)
            }

            notifyDataSetChanged()
        }
    }

    fun obtenerNumeroElementosSeleccionados():Int{
        return itemsSeleccionados?.count()!!
    }

    fun eliminarSeleccionados(){
        if(itemsSeleccionados?.count()!! > 0){
            var itemsEliminados = ArrayList<Registro>()

            for(index in itemsSeleccionados!!){
                itemsEliminados.add(items?.get(index)!!)
            }

            items?.removeAll(itemsEliminados)
            itemsSeleccionados?.clear()
        }
    }


    class ViewHolder(vista: View, listener: ClickListener, longClickListener: LongClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener{

        var vista = vista

        var NombreProducto: TextView? = null
        var Id: TextView?= null
        var Precio: TextView?= null
        var Total: TextView?= null
        var Cantidad: TextView?= null
        var listener: ClickListener? = null
        var longListener: LongClickListener? = null

        init {

            Id = vista.findViewById(R.id.editId)
            NombreProducto = vista.findViewById(R.id.editNombreProducto)
            Precio = vista.findViewById(R.id.editPrecio)
            Cantidad = vista.findViewById(R.id.editCantidad)
            Total = vista.findViewById(R.id.editTotal)


            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!, adapterPosition)
            return true
        }



    }


}