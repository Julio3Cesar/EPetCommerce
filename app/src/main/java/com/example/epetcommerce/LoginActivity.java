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

public class LoginActivity extends AppCompatActivity {

    private Customer customer = null;

    private Button btnCreateAccount;
    private Button btnLogin;
    private EditText txtLoginEmail;
    private EditText txtLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccontIntent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(createAccontIntent);
            }
        });

        txtLoginEmail = findViewById(R.id.txtLoginEmail);
        txtLoginPassword = findViewById(R.id.txtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomer(txtLoginEmail.getText().toString(), txtLoginPassword.getText().toString());
                if(customer != null) {
                    Intent loginIntent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                    startActivity(loginIntent);
                }
            }
        });
    }

    private void getCustomer(String email, String password) {
        Retrofit instanceRetrofit = new Retrofit.Builder()
                .baseUrl("https://oficinacordova.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICustomerClient customerClient =
                instanceRetrofit.create(ICustomerClient.class);

        Call<Customer> getCustomerCall = customerClient.getCustomer(new Customer(email, password));

        Callback<Customer> Callback = new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    customer = response.body();
                } else {
                    //Toast toast = Toast.makeText(getApplicationContext(), "Email ou senha inválidos.", Toast.LENGTH_LONG);
                    //toast.show();

                    showDialog("Email ou senha inválidos.", "Erro");
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
                Toast toast = Toast.makeText(getApplicationContext(), "Algo deu errado!", Toast.LENGTH_LONG);
                toast.show();
            }
        };
         getCustomerCall.enqueue(Callback);
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
