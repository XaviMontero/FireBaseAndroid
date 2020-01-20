package com.example.proyectofinal.modelo;

import com.example.proyectofinal.modelo.producto.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {

    String API_ROUTE = "Producto/";

    @GET(API_ROUTE)
    Call<List<Producto>> getPost();
}
