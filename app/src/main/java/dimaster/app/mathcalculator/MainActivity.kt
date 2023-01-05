package dimaster.app.mathcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var textInput: TextView? = null
    private var textMinus: TextView? = null
    private var lastNum: Boolean = false

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
        lastNum = true

    }
    fun onClear(view: View){
        textInput?.text="0"
        textMinus?.text=""
        lastNum = false
    }

    fun onDecimalPoint(view: View){
        if(!textInput?.text.toString().contains(".")){
            textInput?.append((view as Button).text)
            lastNum = false
        }
    }

    fun onOperator(view: View) {
        val textInputSt = textInput?.text.toString()
        if(textInputSt=="0" && (view as Button).text.equals("-")){
            textMinus?.text="-"
            textInput?.text=""
            lastNum = false
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
            lastNum = false
        }
    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View) {
        if(lastNum){
            var tvValue = textInput?.text.toString()
            try{
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    var resultDouble = one.toDouble() - two.toDouble()
                    if(resultDouble>=0 && textMinus?.text=="") {
                        textInput?.text = removeZeroAfterDote((resultDouble).toString())
                    } else if(resultDouble<0 && textMinus?.text==""){
                        textMinus?.text="-"
                        textInput?.text = removeZeroAfterDote((resultDouble*(-1.0)).toString())
                    } else if(textMinus?.text=="-"){
                        textInput?.text = removeZeroAfterDote((one.toDouble() + two.toDouble()).toString())
                    } else if(resultDouble==0.0){
                        textMinus?.text="-"
                    }
                } else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    var resultDouble = one.toDouble() + two.toDouble()
                    if(resultDouble<0 && textMinus?.text==""){
                        textMinus?.text="-"
                        textInput?.text = removeZeroAfterDote((resultDouble*(-1.0)).toString())
                    } else if(resultDouble>=0 && textMinus?.text==""){
                        textInput?.text = removeZeroAfterDote((resultDouble).toString())
                    } else if(resultDouble>=0 && textMinus?.text=="-"){
                        textInput?.text = removeZeroAfterDote(((one.toDouble() - two.toDouble())*(-1.0)).toString())
                        textMinus?.text=""
                    } else if(resultDouble==0.0){
                        textMinus?.text="-"
                    }
                } else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    var resultDouble = one.toDouble() * two.toDouble()
                    if(resultDouble<0 && textMinus?.text==""){
                        textMinus?.text="-"
                        textInput?.text = removeZeroAfterDote((resultDouble*(-1.0)).toString())
                    } else if(resultDouble>=0 && textMinus?.text==""){
                        textInput?.text = removeZeroAfterDote((resultDouble).toString())
                    } else if(resultDouble>=0 && textMinus?.text=="-"){
                        textInput?.text = removeZeroAfterDote(((one.toDouble() * two.toDouble())).toString())
                        textMinus?.text="-"
                    }
                } else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    var resultDouble = one.toDouble() / two.toDouble()
                    if(resultDouble<0 && textMinus?.text==""){
                        textMinus?.text="-"
                        textInput?.text = removeZeroAfterDote((resultDouble*(-1.0)).toString())
                    } else if(resultDouble>=0 && textMinus?.text==""){
                        textInput?.text = removeZeroAfterDote((resultDouble).toString())
                    } else if(resultDouble>=0 && textMinus?.text=="-"){
                        textInput?.text = removeZeroAfterDote(((one.toDouble() / two.toDouble())).toString())
                        textMinus?.text="-"
                    }
                }
            }catch (e: ArithmeticException){
            }
        }
    }

    private fun removeZeroAfterDote(result: String):String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length - 2)
            return value
        }
    }
}