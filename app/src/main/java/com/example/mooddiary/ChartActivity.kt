package com.example.mooddiary

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.DatePicker

class ChartActivity : AppCompatActivity(), DatePickerFragment.OnDateSetListener {

    private lateinit var editTextStartDate: EditText
    private lateinit var editTextEndDate: EditText
    private var isStartDatePicker: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val chartView = findViewById<ChartView>(R.id.chartView)
        val buttonShowChart = findViewById<Button>(R.id.buttonShowChart)
        editTextStartDate = findViewById(R.id.editTextStartDate)
        editTextEndDate = findViewById(R.id.editTextEndDate)

        val moodEntries = listOf(
            MoodEntry(date = "01.05.2023", time = "12:00", mood = "Радость", description = "Описание"),
            MoodEntry(date = "02.05.2023", time = "12:00", mood = "Стресс", description = "Описание"),
            MoodEntry(date = "03.05.2023", time = "12:00", mood = "Восторженное", description = "Описание"),
            MoodEntry(date = "04.05.2023", time = "12:00", mood = "Скучное", description = "Описание"),
            MoodEntry(date = "05.05.2023", time = "12:00", mood = "Грусть", description = "Описание")
        )

        buttonShowChart.setOnClickListener {
            chartView.setMoodEntries(moodEntries)
        }

        editTextStartDate.setOnClickListener {
            isStartDatePicker = true
            showDatePickerDialog()
        }

        editTextEndDate.setOnClickListener {
            isStartDatePicker = false
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment(this)
        newFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val selectedDate = "${day.toString().padStart(2, '0')}.${(month + 1).toString().padStart(2, '0')}.${year}"
        if (isStartDatePicker) {
            editTextStartDate.setText(selectedDate)
        } else {
            editTextEndDate.setText(selectedDate)
        }
    }
}
