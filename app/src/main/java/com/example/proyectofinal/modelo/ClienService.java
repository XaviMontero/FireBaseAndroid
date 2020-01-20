package com.example.proyectofinal.modelo;

import com.example.proyectofinal.modelo.cliente.Cliente;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClienService {

    String API_ROUTE = "Cliente/";

    @GET(API_ROUTE)
    Call<List<Cliente>> getPost();
}