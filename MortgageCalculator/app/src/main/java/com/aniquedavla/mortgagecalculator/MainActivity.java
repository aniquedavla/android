package com.aniquedavla.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    EditText homeValue, downPayemnt, interestRate, propertyTaxRate;
    Spinner termYearsSpinner;
    int termYearsSelected;
    double calculatedMonthlyPayment;
    Button calculateBtn, resetBtn;

    TextView monthlyPaymentView, totalInterestPaidView, totalPropertyTaxPaidView, payOffDateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //input fields
        homeValue = findViewById(R.id.homeValueInput);
        downPayemnt = findViewById(R.id.downPaymentInput);
        interestRate = findViewById(R.id.interestRateInput);
        termYearsSpinner = (Spinner) findViewById(R.id.termsInput);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.termYears, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        termYearsSpinner.setAdapter(adapter);
        termYearsSpinner.setOnItemSelectedListener(this);
        propertyTaxRate = findViewById(R.id.propertyTaxRateInput);
        calculateBtn = findViewById(R.id.calculateBtn);
        resetBtn = findViewById(R.id.resetBtn);

        //display fields
        monthlyPaymentView  = findViewById(R.id.monthlyPayment);
        totalInterestPaidView = findViewById(R.id.totalInterestPaid);
        totalPropertyTaxPaidView = findViewById(R.id.totalPropertyTax);
        payOffDateView = findViewById(R.id.payOffDate);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //data from fields
                double homeValueData =  Double.valueOf(homeValue.getText().toString());
                double downPaymentData = Double.valueOf(downPayemnt.getText().toString());
                double interestRateData = Double.valueOf(interestRate.getText().toString());
                double propertyTaxRateData = Double.valueOf(propertyTaxRate.getText().toString());

                System.out.println(homeValueData + "\n" + downPaymentData + "\n" + interestRateData + "\n" + propertyTaxRateData);
                double principal = homeValueData - downPaymentData;

                double r = (interestRateData / 100)/12;
                int n = 12 * termYearsSelected;

                System.out.println(principal + "\n" + r + "\n" + n);

                calculatedMonthlyPayment = calculateMonthlyPayment(principal, r, n);
                System.out.println("Calculated Monthly Payment: " + calculatedMonthlyPayment);

                //display payment data
                monthlyPaymentView.setText("Monthly Payment: $" + calculatedMonthlyPayment);
                double totalInterestPaid = (calculatedMonthlyPayment * n) - principal;
                totalInterestPaidView.setText("Total interest paid: $" + totalInterestPaid);
                double totalPropertyTax = ((propertyTaxRateData / 100) / 12) * calculatedMonthlyPayment * n;
                totalPropertyTaxPaidView.setText("Total property tax paid: $" + totalPropertyTax);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int daysTillTerm = termYearsSelected * 365;
                calendar.add(Calendar.DATE, daysTillTerm);
                Date payOffDate = calendar.getTime();

                payOffDateView.setText("Pay off date: " + payOffDate);

            }

        });

    }

    //r is the monthly interest rate, calculated by dividing your annual interest rate by 12.
    //n is your number of payments (the number of months you will be paying the loan)
    private double calculateMonthlyPayment(double principal, double r,int n){
        double monthlyPayement;
        double num = r * (Math.pow((1 + r), n));
        double deno = (Math.pow((1 + r), n)) - 1;
        monthlyPayement = principal * (num/deno);
        return  monthlyPayement;
    }

    //for OnItemSelectedlistener
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // the item that was selected.
        termYearsSelected = Integer.parseInt((String) parent.getItemAtPosition(pos));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
