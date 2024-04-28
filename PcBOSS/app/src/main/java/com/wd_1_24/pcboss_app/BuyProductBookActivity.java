package com.wd_1_24.pcboss_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyProductBookActivity extends AppCompatActivity {

    EditText edName,edAddress,edPincode,edContact;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product_book);

        edName=findViewById(R.id.editTextOrderFullname);
        edAddress=findViewById(R.id.editTextOrderAddress);
        edPincode=findViewById(R.id.editTextOrderPincode);
        edContact=findViewById(R.id.editTextOrderContact);
        btnOrder=findViewById(R.id.buttonOrder);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharedPreferences = getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                SharedPreferences sharedPref = getSharedPreferences("sharedprefitems", Context.MODE_PRIVATE);
                String products = sharedPref.getString("items","").toString();

                Database db = new Database(getApplicationContext(),"pcboss_app.db",null,1);
                db.addOrder(username,edName.getText().toString(),products,edAddress.getText().toString(),edContact.getText().toString(),
                        edPincode.getText().toString(),Float.parseFloat(price[1]));
                db.clearCart(username);
                Toast.makeText(getApplicationContext(),"Order Placed Successfully",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(BuyProductBookActivity.this,HomeActivity.class));

            }
        });




    }
}