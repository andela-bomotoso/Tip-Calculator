package com.example.bukola_omotoso.mytipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billAmount = 0.0;
    private double percent = 0.15;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView tipAmountTextView;
    private TextView totalAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView = (TextView)findViewById(R.id.amount_textView);
        tipTextView = (TextView)findViewById(R.id.tip_percent_textView);
        tipAmountTextView = (TextView)findViewById(R.id.tip_amount_textView);
        totalAmountTextView = (TextView)findViewById(R.id.total_textView);
        tipAmountTextView.setText(currencyFormat.format(0));
        totalAmountTextView.setText(currencyFormat.format(0));

        EditText amountEditText = (EditText)findViewById(R.id.amount_editText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        SeekBar tipSeekBar = (SeekBar)findViewById(R.id.tip_seekbar);
        tipSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private void calculate()    {

        tipTextView.setText(percentFormat.format(percent));
        double tip = billAmount * percent;
        double total = billAmount + tip;

        tipAmountTextView.setText(currencyFormat.format(tip));
        totalAmountTextView.setText(currencyFormat.format(total));
    }

    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress/100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                billAmount = Double.parseDouble(s.toString()) / 100.00;
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e) {
                amountTextView.setText("");
                billAmount = 0.0;
            }
            calculate();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
