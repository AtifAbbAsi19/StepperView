package com.mak.recycler.stepperview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.mak.recycler.stepperview.R
import com.mak.recycler.stepperview.model.core.BaseStepperItem
import com.mak.recycler.stepperview.utils.HORIZONTAL_STATE
import com.mak.recycler.stepperview.utils.VERTICAL_STATE


class StepperAdapter(
    progressContentList: ArrayList<BaseStepperItem>,
    itemAction: ((Any, Int) -> Unit)?,
    isVertical: Boolean = true
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var _itemAction: ((Any, Int) -> Unit)? = itemAction
    private var _isVerticalView = isVertical
    private var _progressContentList: ArrayList<BaseStepperItem> = progressContentList

    init {
        this._itemAction = itemAction
        this._isVerticalView = isVertical
        this._progressContentList = progressContentList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VERTICAL_STATE -> {
                VerticalProgressViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.stepper_vertical_item_view, parent, false)
                )
            }

            else -> {
                HorizontalProgressViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.stepper_horizontal_item_view, parent, false)
                )
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is VerticalProgressViewHolder -> {
                (holder as VerticalProgressViewHolder).bind(_progressContentList[position])
            }

            else -> {
                (holder as HorizontalProgressViewHolder).bind(_progressContentList[position])
            }
        }

    }

    override fun getItemViewType(position: Int): Int =
        when {
            _isVerticalView -> {
                VERTICAL_STATE
            }

            else -> {
                HORIZONTAL_STATE
            }
        }


    //get the size of array
    override fun getItemCount(): Int =
        _progressContentList?.size ?: 0 //elvis operator short if else return default size 0
//    override fun getItemCount(): Int = Int.MAX_VALUE


    @SuppressLint("NotifyDataSetChanged")
    fun updateContent(progressContentList: ArrayList<BaseStepperItem>) {
        this._progressContentList.clear()
        this._progressContentList.addAll(progressContentList)
        notifyDataSetChanged()
    }


    inner class VerticalProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val title = itemView.findViewById<MaterialTextView>(R.id.title)
        val description = itemView.findViewById<MaterialTextView>(R.id.description)


        val progressIcon = itemView.findViewById<AppCompatImageView>(R.id.progress_circle)
        val progressLine = itemView.findViewById<ShapeableImageView>(R.id.progress_line)


        @SuppressLint("ResourceType")
        fun bind(content: BaseStepperItem?) {


            itemView.setOnClickListener {
                _itemAction?.invoke(content as Any, -1)
            }


            when (content?.isSuccess) {
                true -> {//R.id.progress_circle
                    progressIcon.setBackgroundResource(content.successIconRes)
                    progressLine.setBackgroundResource(content.successLineRes)

                }

                else -> {
                    progressIcon.setBackgroundResource(content!!.progressIconRes)
                    progressLine.setBackgroundResource(content.progressLineRes)
                }

            }


            title.text = content!!.title
            description.text = content!!.description


        }

    }


    inner class HorizontalProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<MaterialTextView>(R.id.title)
        val description = itemView.findViewById<MaterialTextView>(R.id.description)


        val progressIcon = itemView.findViewById<AppCompatImageView>(R.id.progress_circle)
        val progressLine = itemView.findViewById<ShapeableImageView>(R.id.progress_line)



        fun bind(content: BaseStepperItem?) {

            itemView.setOnClickListener {
                _itemAction?.invoke(content as Any, -1)
            }

            title.text = content?.title
            description.text = content?.description

            when (content?.isSuccess) {
                true -> {//R.id.progress_circle
                    progressIcon.setBackgroundResource(content.successIconRes)
                    progressLine.setBackgroundResource(content.successLineHorizontalRes)

                }

                else -> {
                    progressIcon.setBackgroundResource(content!!.progressIconRes)
                    progressLine.setBackgroundResource(content.progressLineHorizontalRes)
                }

            }

        }

    }
}