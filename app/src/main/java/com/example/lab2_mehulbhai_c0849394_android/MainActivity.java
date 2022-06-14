package com.example.lab2_mehulbhai_c0849394_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBHelperClass dbHelperClass;
    View dataView;
    TextView insertProduct, updateProduct, deleteProduct, viewAllProduct;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataView = findViewById(R.id.view2);
        insertProduct = findViewById(R.id.txtInsertNewProduct);
        updateProduct = findViewById(R.id.txtUpdateProduct);
        deleteProduct = findViewById(R.id.txtDeleteProduct);
        viewAllProduct = findViewById(R.id.txtViewAllProduct);

        dbHelperClass = new DBHelperClass(MainActivity.this);

        insertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataDialog();
            }
        });

        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDataDialog();
            }
        });

        updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataDialog();
            }
        });
    }

    public void insertDataDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.insert_data_dialog_layout, null);

        EditText productID = view.findViewById(R.id.edtInsertPId);
        EditText productName = view.findViewById(R.id.edtInsertPName);
        EditText productPrice = view.findViewById(R.id.edtInsertPPrice);
        EditText productDesc = view.findViewById(R.id.edtInsertPDesc);
        Button insertButton = view.findViewById(R.id.btnInsertDB);

        alertDialog.setView(view);
        AlertDialog alertDialog1 = alertDialog.show();

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelClass modelClass = new ModelClass();

                modelClass.setProduct_id(productID.getText().toString());
                modelClass.setProduct_name((productName.getText().toString()));
                modelClass.setProduct_price(productPrice.getText().toString());
                modelClass.setProduct_description(productDesc.getText().toString());

                dbHelperClass.AddProduct(modelClass);
                alertDialog1.dismiss();
            }
        });
    }

    public void deleteDataDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.delete_data_dialog_layout, null);
        alertDialog.setView(view);

        EditText inputPid = view.findViewById(R.id.edtDeleteData);
        Button delete_btn = view.findViewById(R.id.btnDeleteRec);
        AlertDialog alertDialog1 = alertDialog.show();

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelperClass.DeleteProductItem(inputPid.getText().toString());
                alertDialog1.dismiss();
            }
        });
    }

    public void updateDataDialog() {
        AlertDialog.Builder alerrDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dataid_fetch_dialog_layout, null);
        alerrDialog.setView(view);
        EditText fetchedId = view.findViewById(R.id.edtFetchId);
        Button fetch_btn = view.findViewById(R.id.btnFetchData);
        AlertDialog alertDialog1 = alerrDialog.show();

        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateRecordDialog(fetchedId.getText().toString());
                alertDialog1.dismiss();
            }
        });
    }

    public void showUpdateRecordDialog(String id) {
        ModelClass modelClass = dbHelperClass.getProductRecord(Integer.parseInt(id));

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.update_data_dialog_layout, null);

        EditText productID = view.findViewById(R.id.edtUpdatePId);
        EditText productName = view.findViewById(R.id.edtUpdatePName);
        EditText productDesc = view.findViewById(R.id.edtUpdatePDesc);
        EditText productPrice = view.findViewById(R.id.edtUpdatePPrice);
        Button update_btn = view.findViewById(R.id.btnUpdateDB);

        alertDialog.setView(view);

        productID.setText(modelClass.getProduct_id());
        productName.setText(modelClass.getProduct_name());
        productDesc.setText(modelClass.getProduct_description());
        productPrice.setText(modelClass.getProduct_price());

        AlertDialog alertDialog1 = alertDialog.show();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelClass modelClass1 = new ModelClass();
                modelClass1.setProduct_id(productID.getText().toString());
                modelClass1.setId(id);
                modelClass1.setProduct_name(productName.getText().toString());
                modelClass1.setProduct_description(productDesc.getText().toString());
                modelClass1.setProduct_price(productPrice.getText().toString());
                dbHelperClass.updateProductRecord(modelClass1);
                alertDialog1.dismiss();
            }
        });
    }
}