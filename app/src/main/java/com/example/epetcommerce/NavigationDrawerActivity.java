package com.example.epetcommerce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.epetcommerce.database.UserData;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtUserName;
    TextView txtUserEmail;

    FrameLayout fragmentContainer;

    RecyclerView container;
    //AdapterProduto adapter;
    RecyclerView.LayoutManager layoutManager;

    private static Context instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fragmentContainer = findViewById(R.id.fragmentContainer);

        instance = this.getBaseContext();


        View headerView = navigationView.getHeaderView(0);

        txtUserEmail = (TextView) headerView.findViewById(R.id.txtuserEmailMenu);
        txtUserName = (TextView) headerView.findViewById(R.id.txtUserNameMenu);

        UserData userData = UserData.getInstance();

        txtUserEmail.setText(userData.getUser().getEmailCliente());
        txtUserName.setText(userData.getUser().getNomeCompletoCliente());
        OpenProductList();
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_produtos) {
            OpenProductList();
        } else if (id == R.id.nav_cart) {
            CartFragment fragCart = new CartFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragCart).commit();

        } else if (id == R.id.nav_about) {
            AboutFragment fragAbout = new AboutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragAbout).commit();
        } else if (id == R.id.nav_logout) {
            Intent logout = new Intent(this, LoginActivity.class);
            startActivity(logout);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void OpenProductList() {
        ProductListFragment fragProduct = new ProductListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragProduct).commit();

    }


}
