package com.mak.recycler.stepperview.model.core

import com.mak.recycler.stepperview.R


abstract class BaseStepperItem constructor(

    var title: String?,
    var description: String?,

    var successIcon: String? = "",
    //for vertical view
    var successIconRes: Int = R.drawable.tick_icon,
    var successLineRes: Int = R.drawable.success_line,

    var progressIcon: String? = "",

    var progressIconRes: Int = R.drawable.progress_circle,
    var progressLineRes: Int = R.drawable.dotted_line,

    //for horizontal view
    var progressLineHorizontalRes: Int = R.drawable.dotted_line_horizontal,
    var successLineHorizontalRes: Int = R.drawable.sucess_line_horizontal,

    var isSuccess: Boolean = false,

    var isVertical: Boolean = true,

    var isInProgress: Boolean = true

)