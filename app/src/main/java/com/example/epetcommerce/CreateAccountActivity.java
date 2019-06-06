package com.example.epetcommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epetcommerce.clients.ICustomerClient;
import com.example.epetcommerce.models.Customer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAccountActivity extends AppCompatActivity {

    private Button btnSendCreateAccount;
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private EditText txtCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtName = findViewById(R.id.txtCpf);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtLoginPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtCpf = findViewById(R.id.txtCpf);

        btnSendCreateAccount = findViewById(R.id.btnSendCreateAccount);
        btnSendCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString()))
                {
                    setCustomer();
                }
                else{
                    showDialog("A senha deve ser igual a senha de confirmação", "Error");
                }
            }
        });

    }

    private void setCustomer() {
        Retrofit instanceRetrofit = new Retrofit.Builder()
                .baseUrl("https://oficinacordova.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICustomerClient customerClient =
                instanceRetrofit.create(ICustomerClient.class);

        Customer customer = new Customer(txtName.getText().toString(), txtEmail.getText().toString(), txtPassword.getText().toString(), txtCpf.getText().toString());

        Call<Customer> setCustomerCall = customerClient.setCustomer(customer);

        Callback<Customer> Callback = new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                String msg = response.message();
                if (response.isSuccessful() && response.body() != null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_LONG);
                    toast.show();

                    Intent goLogin = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(goLogin);
                } else {
                    showDialog("Erro ao cadastrar.", "Erro");
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(), "Algo deu errado!", Toast.LENGTH_LONG);
                toast.show();
            }
        };
        setCustomerCall.enqueue(Callback);

    }



    private void showDialog(String message, String title) {
        //Declara e instancia uma fábrica de construção de diálogos
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Configura o corpo da mensagem
        builder.setMessage(message);
        //Configura o título da mensagem
        builder.setTitle(title);
        //Impede que o botão seja cancelável (possa clicar
        //em voltar ou fora para fechar)
        builder.setCancelable(false);
        //Configura um botão de OK para fechamento (um
        //outro listener pode ser configurado no lugar do "null")
        builder.setPositiveButton("OK", null);
        //Cria efetivamente o diálogo
        AlertDialog dialog = builder.create();
        //Faz com que o diálogo apareça na tela
        dialog.show();
    }

}
