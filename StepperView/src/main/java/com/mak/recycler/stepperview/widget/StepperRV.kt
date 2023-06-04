package com.mak.recycler.stepperview.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mak.recycler.stepperview.R
import com.mak.recycler.stepperview.adapter.StepperAdapter
import com.mak.recycler.stepperview.model.core.BaseStepperItem


class StepperRV : ConstraintLayout {

    //Content view
    private var contentRecyclerView: RecyclerView? = null

    //adapter for stepper view
    private lateinit var adapter: StepperAdapter

    //item click
    private lateinit var onItemClick: (Any, Int?) -> Unit

    //by default view orientation is vertical
    private var isVertical = true


    private var REVERSE_LAYOUT = false

    //default span count
    private var GRID_SPAN_COUNT = 2

    //rows in vertical flow (for grid scroll)
    private var NUMBER_OF_ROWS = 1

    private var isEnabledVerticalRow = false

    //Primary Constructor
    constructor(context: Context) : super(context)

    //Secondary Constructor
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        try {
            initView(context, attrs) //init view
        } catch (ignored: Exception) {
            ignored.message
        }
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        //inflating view
        inflate(context, R.layout.stepper_view_rv, this)
        // setting background to transparent
        setBackgroundColor(Color.TRANSPARENT)

        try {
            initAttrs(attrs)
        } finally {
            initUi(context)
        }
    }

    //getting attributes from XML
    @SuppressLint("CustomViewStyleable")
    private fun initAttrs(attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StepperViewRV)

        isVertical = typedArray.getBoolean(R.styleable.StepperViewRV_srv_isVertical, isVertical)

        isEnabledVerticalRow =
            typedArray.getBoolean(
                R.styleable.StepperViewRV_srv_Enable_Vertical_Row,
                isEnabledVerticalRow
            )

        if (!isVertical)
            GRID_SPAN_COUNT =
                typedArray.getInt(R.styleable.StepperViewRV_srv_spanCount, GRID_SPAN_COUNT)

        if (isEnabledVerticalRow)
            NUMBER_OF_ROWS =
                typedArray.getInt(R.styleable.StepperViewRV_srv_Vertical_Row, NUMBER_OF_ROWS)

        typedArray.recycle()

    }

    //ui rendering
    private fun initUi(context: Context) {

        contentRecyclerView = findViewById(R.id.stepper_rview)
        contentRecyclerView?.setHasFixedSize(true) //for optimization
        contentRecyclerView.apply {
            this?.layoutManager = if (!isVertical) {
                //GridLayoutManager(context, gridSpanCount)
                object : GridLayoutManager(
                    context,
                    NUMBER_OF_ROWS,
                    GridLayoutManager.HORIZONTAL,
                    REVERSE_LAYOUT
                ) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                        // force height of viewHolder here, this will override layout_height from items xml
                        lp!!.width = width / GRID_SPAN_COUNT
                        return true
                    }
                }
            } else {

                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, REVERSE_LAYOUT)
            }
        }


        if (!::adapter.isInitialized) {

            adapter = StepperAdapter(
                arrayListOf(), { item, position ->
                    onItemClick.invoke(item, position)
                },
                isVertical
            )
            contentRecyclerView?.adapter = adapter
        }

    }

    /**
     * to setMargin and Padding or any property to UI
     */
    fun getStepperView(): RecyclerView? {
        return contentRecyclerView
    }

    // dynamic adapter for view
    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        contentRecyclerView?.adapter = adapter
    }

    //to check adapter configs
    fun getAdapter(): RecyclerView.Adapter<*>? {
        return contentRecyclerView?.adapter
    }

    // to check is adapter is not null
    fun isAdapterSet(): Boolean {
        return contentRecyclerView?.adapter != null
    }

    //on click listener
    fun setItemAction(action: (Any?, Int?) -> Unit) {
        this.onItemClick = action
    }

    //dynamic list for content
    fun setContent(progressContentList: ArrayList<BaseStepperItem>) {
        if (::adapter.isInitialized) {
            adapter.updateContent(progressContentList)
        }
    }

}