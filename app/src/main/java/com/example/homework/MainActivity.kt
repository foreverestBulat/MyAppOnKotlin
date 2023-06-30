package com.example.homework

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var clickButton: Button? = null
    var count = 0
    private var key: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton = findViewById(R.id.button)

        var textView = findViewById<TextView>(R.id.textView)
        var editTextName = findViewById<EditText>(R.id.editTextText)
        var editTextAge = findViewById<EditText>(R.id.editTextText2)
        var editTextWeight = findViewById<EditText>(R.id.editTextText3)
        var editTextHeight = findViewById<EditText>(R.id.editTextNumber)

        editTextName.text.clear()
        editTextHeight.text.clear()
        editTextWeight.text.clear()
        editTextAge.text.clear()
        textView.text = ""

        clickButton?.setOnClickListener {
            var str = checkCorrectnessOfEnteredData(
                editTextName.text.toString(),
                editTextHeight.text.toString(),
                editTextWeight.text.toString(),
                editTextAge.text.toString())

            if (key){
                textView.setTextColor(Color.parseColor("#FF0000"))
                textView.text = str
            }
            else{
                textView.setTextColor(Color.parseColor("#000000"))
                textView.text = str
            }
        }
    }

    // я сделал ограничение на количество символов в name на constrait поэтому не вижу смысла здесь проверять на 50 символов
    // в полях height, weight, age можно записывать только цифры, а в weight еще и точку чтобы можно было записать double

    fun checkCorrectnessOfEnteredData(name: String, heightString: String, weightString: String, ageString: String): String {

        var res = "Data entered incorrectly:\n"

        try{
            var height = heightString.toInt()
            var weight = weightString.toDouble()
            var age = ageString.toInt()


            if (name == ""){
                res = res + "the name should not be empty;\n"
            }
            if ((250 < height) or (0 > height)){
                res = res + "the height must be greater than 0 and less than 250;\n"
            }
            if ((250 < weight) or (0 > weight)){
                res = res + "the weight must be greater than 0 and less than 250;\n"
            }
            if ((age > 150) or (age < 0)) {
                res = res + "the age must be greater than 0 and less than 150;\n"
            }

            if (res.length > 30){
                key = true
            } else {
                key = false
                var amountCalories = getAmountOfCalories(height, weight)
                var result = "To achieve the desired weight and with moderate activity, you need to consume calories daily in the amount of $amountCalories"

                return result
            }
            return res

        } catch(e: NumberFormatException) {
            key = true
            return res
        }
    }

    // Количество калорий = вес (в кг) * 1.375 * 0.85
    fun getAmountOfCalories(height: Int, weight: Double): Double {
        return weight * 1.375 * 0.85
    }
}

