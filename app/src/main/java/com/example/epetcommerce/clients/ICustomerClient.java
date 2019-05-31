package com.example.epetcommerce.clients;

import com.example.epetcommerce.models.Customer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICustomerClient {

    @POST("/android/rest/cliente")
    Call<Customer> getCustomer(@Body Customer customer);
}
