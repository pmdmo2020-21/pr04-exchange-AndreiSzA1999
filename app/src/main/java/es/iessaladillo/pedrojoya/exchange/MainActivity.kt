package es.iessaladillo.pedrojoya.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.exchange.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private var idFromCurrency = R.id.rdoEuro
    private var idToCurrency = R.id.rdoDollar2
    private var valorIntroducido = 0.0
    private var simboloFrom = Currency.EURO.symbol
    private var simboloTo = Currency.DOLLAR.symbol

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupviews()
        initialstate()


    }

    private fun initialstate() {

        binding.rdoEuro2.setEnabled(false)
        binding.rdoDollar.setEnabled(false)
        binding.imageFirstCurrency.setImageResource(Currency.EURO.drawableResId)
        binding.imageSecondCurrency.setImageResource(Currency.DOLLAR.drawableResId)
        binding.txtAmount.setText("0")

    }

    private fun setupviews() {

        buttons()
        datos()
        binding.btnExchange.setOnClickListener { calculate() }

    }

    private fun datos() {
        binding.txtAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.txtAmount.text.isBlank()) {
                    binding.txtAmount.text = toEditable("0")
                    binding.txtAmount.selectAll()
                }

                valorIntroducido = binding.txtAmount.text.toString().toDouble()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        })
    }

    private fun toEditable(s: String): Editable? {
        return Editable.Factory.getInstance().newEditable(s)
    }

    private fun calculate() {
        var resulado = 0.0

        var dollar = Currency.DOLLAR.toDollar(valorIntroducido)
        when (idFromCurrency) {
            R.id.rdoDollar -> dollar = Currency.DOLLAR.toDollar(valorIntroducido)
            R.id.rdoEuro -> dollar = Currency.EURO.toDollar(valorIntroducido)
            R.id.rdoPound -> dollar = Currency.POUND.toDollar(valorIntroducido)
        }

        when (idToCurrency) {
            R.id.rdoDollar2 -> resulado = Currency.DOLLAR.fromDollar(dollar)
            R.id.rdoEuro2 -> resulado = Currency.EURO.fromDollar(dollar)
            R.id.rdoPound2 -> resulado = Currency.POUND.fromDollar(dollar)
        }
        binding.txtAmount.onEditorAction(EditorInfo.IME_ACTION_DONE)
        Toast.makeText(
            this,
            String.format("%.2f", valorIntroducido).plus(simboloFrom).plus(" = ")
                .plus(String.format("%.2f", resulado)).plus(simboloTo),
            Toast.LENGTH_SHORT
        ).show()

    }

    private fun buttons() {
        binding.rdoEuro.setOnClickListener { rdobutton(binding.rdoEuro) }
        binding.rdoDollar.setOnClickListener { rdobutton(binding.rdoDollar) }
        binding.rdoPound.setOnClickListener { rdobutton(binding.rdoPound) }
        binding.rdoEuro2.setOnClickListener { rdobutton(binding.rdoEuro2) }
        binding.rdoDollar2.setOnClickListener { rdobutton(binding.rdoDollar2) }
        binding.rdoPound2.setOnClickListener { rdobutton(binding.rdoPound2) }
    }

    private fun rdobutton(rdoButton: RadioButton) {
        when (rdoButton) {
            binding.rdoEuro -> {
                (binding.rdoEuro2.setEnabled(false))
                binding.rdoPound2.setEnabled(true)
                binding.rdoDollar2.setEnabled(true)
                binding.imageFirstCurrency.setImageResource(Currency.EURO.drawableResId)
                idFromCurrency = R.id.rdoEuro;
                simboloFrom = Currency.EURO.symbol

            }

            binding.rdoDollar -> {
                (binding.rdoEuro2.setEnabled(true))
                binding.rdoPound2.setEnabled(true)
                binding.rdoDollar2.setEnabled(false)
                binding.imageFirstCurrency.setImageResource(Currency.DOLLAR.drawableResId)
                idFromCurrency = R.id.rdoDollar;
                simboloFrom = Currency.DOLLAR.symbol

            }
            binding.rdoPound -> {
                (binding.rdoEuro2.setEnabled(true))
                binding.rdoPound2.setEnabled(false)
                binding.rdoDollar2.setEnabled(true)
                binding.imageFirstCurrency.setImageResource(Currency.POUND.drawableResId)
                idFromCurrency = R.id.rdoPound;
                simboloFrom = Currency.POUND.symbol
            }
            binding.rdoEuro2 -> {
                (binding.rdoEuro.setEnabled(false))
                binding.rdoPound.setEnabled(true)
                binding.rdoDollar.setEnabled(true)
                binding.imageSecondCurrency.setImageResource(Currency.EURO.drawableResId)
                idToCurrency = R.id.rdoEuro2;
                simboloTo = Currency.EURO.symbol
            }
            binding.rdoDollar2 -> {
                (binding.rdoEuro.setEnabled(true))
                binding.rdoPound.setEnabled(true)
                binding.rdoDollar.setEnabled(false)
                binding.imageSecondCurrency.setImageResource(Currency.DOLLAR.drawableResId)
                idToCurrency = R.id.rdoDollar2;
                simboloTo = Currency.DOLLAR.symbol
            }
            binding.rdoPound2 -> {
                (binding.rdoEuro.setEnabled(true))
                binding.rdoPound.setEnabled(false)
                binding.rdoDollar.setEnabled(true)
                binding.imageSecondCurrency.setImageResource(Currency.POUND.drawableResId)
                idToCurrency = R.id.rdoPound2;
                simboloTo = Currency.POUND.symbol
            }

        }
    }


}