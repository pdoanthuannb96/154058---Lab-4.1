package id20210822.class154058.lab41

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentNumber: String = ""
    private var previousNumber: String = ""
    private var operation: String = ""
    private var isNewOp: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvResult = findViewById(R.id.tvResult)

        val buttons = listOf<Button>(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        for (button in buttons) {
            button.setOnClickListener {
                onDigitPress((it as Button).text.toString())
            }
        }

        findViewById<Button>(R.id.buttonAdd).setOnClickListener { onOperatorPress("+") }
        findViewById<Button>(R.id.buttonSub).setOnClickListener { onOperatorPress("-") }
        findViewById<Button>(R.id.buttonMul).setOnClickListener { onOperatorPress("*") }
        findViewById<Button>(R.id.buttonDiv).setOnClickListener { onOperatorPress("/") }

        // Special Buttons
        findViewById<Button>(R.id.buttonEqual).setOnClickListener { onEqualPress() }
        findViewById<Button>(R.id.buttonClear).setOnClickListener { onClearPress() }
        findViewById<Button>(R.id.buttonDel).setOnClickListener { onBackspacePress() }
        findViewById<Button>(R.id.buttonDot).setOnClickListener { onDotPress() }
        findViewById<Button>(R.id.buttonNegate).setOnClickListener { onNegatePress() }


    }

    private fun onDigitPress(digit: String) {
        if (isNewOp) {
            currentNumber = ""
            isNewOp = false
        }
        currentNumber += digit
        tvResult.text = currentNumber
    }

    private fun onOperatorPress(op: String) {
        if (currentNumber.isEmpty()) return
        previousNumber = currentNumber
        currentNumber = ""
        operation = op
        isNewOp = true
    }

    private fun onEqualPress() {
        if (previousNumber.isEmpty() || currentNumber.isEmpty()) return
        val result = when (operation) {
            "+" -> previousNumber.toDouble() + currentNumber.toDouble()
            "-" -> previousNumber.toDouble() - currentNumber.toDouble()
            "*" -> previousNumber.toDouble() * currentNumber.toDouble()
            "/" -> previousNumber.toDouble() / currentNumber.toDouble()
            else -> 0.0
        }

        tvResult.text = result.toInt().toString()
        currentNumber = result.toString()
        isNewOp = true
    }

    private fun onClearPress() {
        currentNumber = ""
        previousNumber = ""
        operation = ""
        tvResult.text = "0"
        isNewOp = true
    }

    private fun onBackspacePress() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            tvResult.text = currentNumber
        }
    }

    private fun onDotPress() {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
            tvResult.text = currentNumber
        }
    }

    private fun onNegatePress() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = if (currentNumber.startsWith("-")) {
                currentNumber.drop(1)
            } else {
                "-$currentNumber"
            }
            tvResult.text = currentNumber
        }
    }
}