package com.mak.recycler.stepperview.model.core

import com.mak.recycler.stepperview.R


abstract class BaseStepperItem constructor(

    var title: String?,
    var description: String?,

    //for vertical view
    var successIcon: Int = R.drawable.tick_icon,
    var successLine: Int = R.drawable.success_line,

    var icon: Int = R.drawable.progress_circle,
    var progressLine: Int = R.drawable.dotted_line,

    //for horizontal view
    var progressLineHorizontal: Int = R.drawable.dotted_line_horizontal,
    var successLineHorizontal: Int = R.drawable.sucess_line_horizontal,

    var isSuccess: Boolean = false,

    var isVertical: Boolean = true,

    var isInProgress: Boolean = true

)