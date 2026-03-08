package com.api.imagenes.controller;

import com.api.imagenes.model.Imagen;
import com.api.imagenes.service.ImagenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    private final ImagenService service;

    public ImagenController(ImagenService service) {
        this.service = service;
    }

    @GetMapping
    public List<Imagen> listarImagenes() {
        return service.obtenerImagenes();
    }

    @GetMapping("/{id}")
    public Imagen obtenerImagen(@PathVariable int id) {
        return service.obtenerImagenPorId(id);
    }
}   
