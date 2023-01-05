package dimaster.app.mathcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textInput: TextView? = null
    private var textMinus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInput = findViewById(R.id.result_tv)
        textMinus = findViewById(R.id.min_tv)

    }

    fun onDigit(view: View) {
        if(textInput?.text.toString()=="0"){
            textInput?.text=""
        }

        //Get the view text on Button
        textInput?.append((view as Button).text)

    }
    fun onClear(view: View){
        textInput?.text="0"
        textMinus?.text=""
    }

    fun onDecimalPoint(view: View){
        if(!textInput?.text.toString().contains(".")){
            textInput?.append((view as Button).text)
        }
    }

    fun onOperator(view: View) {
        val textInputSt = textInput?.text.toString()
        if(textInputSt=="0" && (view as Button).text.equals("-")){
            textMinus?.text="-"
            textInput?.text=""
        }
        if (textInputSt.contains("/")
            ||textInputSt.contains("*")
            ||textInputSt.contains("-")
            ||textInputSt.contains("+")
        ){
            return
        } else if(textInputSt != "0") {
            Log.d("TAG", "onOperator: $textInputSt")
            textInput?.append((view as Button).text)
        }
    }

    fun onEqual(view: View) {}
}