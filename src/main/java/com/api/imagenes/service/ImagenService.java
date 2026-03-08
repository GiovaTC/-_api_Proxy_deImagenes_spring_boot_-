package com.api.imagenes.service;

import com.api.imagenes.model.Imagen;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ImagenService {

    private final String API_URL =
            "https://jsonplaceholder.typicode.com/photos";

    public List<Imagen> obtenerImagenes() {

        RestTemplate restTemplate = new RestTemplate();

        Imagen[] imagenes = restTemplate.getForObject(API_URL, Imagen[].class);

        return Arrays.asList(imagenes);
    }

    public Imagen obtenerImagenPorId(int id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = API_URL + "/" + id;

        return restTemplate.getForObject(url, Imagen.class);
    }
}
