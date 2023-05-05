package sg.edu.rp.c346.id22014093.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText Pax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPay;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup cashpaynow;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editAmount);
        Pax = findViewById(R.id.editPax);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);
        svs = findViewById(R.id.toggleButtonSVS);
        gst = findViewById(R.id.toggleButtonGST);
        totalBill = findViewById(R.id.ttlBill);
        eachPay = findViewById(R.id.Each);
        discount = findViewById(R.id.editDiscount);
        cashpaynow = findViewById(R.id.radioGroupPayment);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() != 0 && Pax.getText().toString().trim().length() != 0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }

                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(Pax.getText().toString());
                    if (numPerson != 1)
                        eachPay.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                    else
                        eachPay.setText("Each Pays: $" + newAmt);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                Pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });



    }
}
