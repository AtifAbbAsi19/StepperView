package com.mak.recycler.stepperview.model

import android.content.Context
import android.util.AttributeSet
import com.mak.recycler.stepperview.model.core.BaseStepperItem


class StepperItem(
    title: String? = "",
    description: String = " \t \t \t \n \n \n ",
    isSuccess: Boolean? = false
) : BaseStepperItem(title = title, description = description) {


    init {
        this.title = title ?: ""
        this.description = description ?: " \t \t \t \n \n \n "
        this.isSuccess = isSuccess ?: false
    }


    //Secondary Constructor
   // constructor(title: String, description: String) : super(title ?: "", description ?: "")


}