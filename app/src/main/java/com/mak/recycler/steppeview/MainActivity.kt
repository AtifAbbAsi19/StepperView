package com.mak.recycler.steppeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mak.recycler.stepperview.adapter.StepperAdapter
import com.mak.recycler.stepperview.model.StepperItem
import com.mak.recycler.stepperview.widget.StepperRView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val stepperView = findViewById<StepperRView>(R.id.stepper_view)


        val title = StepperItem(
            title = "First Step",
            description = "",
        )


        val description = StepperItem(
            description = "Dispached"
        )


        val stepperAdapter = StepperAdapter(
            arrayListOf(
                title,
                description,
                title,
                description,
                title,
                description
            ),
            { item, position ->
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            },
            false
        )

        stepperView.setAdapter(stepperAdapter)


    }
}