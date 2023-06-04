package com.mak.recycler.steppeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mak.recycler.stepperview.adapter.StepperAdapter
import com.mak.recycler.stepperview.model.StepperItem
import com.mak.recycler.stepperview.widget.StepperRV

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val stepperView = findViewById<StepperRV>(R.id.stepper_view)


        val title = StepperItem(
            title = "First Step",
            description = "",
        )


        val description = StepperItem(
            description = "Dispached"
        )




        stepperView.setAdapter(
            StepperAdapter(
                arrayListOf(
                    title,
                    description,
                    title,
                    description,
                    title,
                    description
                ),
                { item, position ->

                },
                false
            )
        )





    }
}