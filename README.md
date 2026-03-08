# -_api_Proxy_deImagenes_spring_boot_- :. 
📡 API Proxy de Imágenes – Spring Boot:

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/17f8a314-8f5a-47d2-919f-d689cc2d036d" />        

<img width="2551" height="1038" alt="image" src="https://github.com/user-attachments/assets/ed3f71a0-a5e0-4b41-809d-f268ccb68df7" />            

Este proyecto implementa una API propia en Spring Boot que actúa como Proxy / Gateway hacia una API publica en Internet .

```

La API:

- Consume datos desde una API externa
- Recupera datos + imágenes
- Expone endpoints propios
- Puede probarse con Postman

* Para evitar autenticación y facilitar pruebas se utiliza la API pública:

JSONPlaceholder

https://jsonplaceholder.typicode.com/photos

Esta API contiene aproximadamente 5000 imágenes con metadatos:
- title
- url
- thumbnailUrl
- albumId
- id

🏗 Arquitectura del Proyecto:
API EXTERNA (Internet)
      │
      │  GET https://jsonplaceholder.typicode.com/photos
      ▼
API PROPIA (Spring Boot)
      │
      ▼
POSTMAN / CLIENTES

* La aplicación funciona como un API Gateway / Proxy REST.

🧱 Tecnologías Utilizadas:
- Tecnología	Descripción
- Java 21	Lenguaje principal
- Spring Boot	Framework backend
- Maven	Gestión de dependencias
- REST API	Arquitectura de servicios
- Postman	Pruebas de endpoints
- JSONPlaceholder	API pública de imágenes

📁 Estructura del Proyecto:
api-imagenes
│
├── controller
│     ImagenController.java
│
├── service
│     ImagenService.java
│
├── model
│     Imagen.java
│
└── ApiImagenesApplication.java

1️⃣ Crear Proyecto Spring Boot:
Crear el proyecto en Spring Initializr:
https://start.spring.io

* Dependencias
- Spring Web
- Lombok (opcional)

2️⃣ Modelo de Datos:
📁 model/Imagen.java

package com.api.imagenes.model;

public class Imagen {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Imagen() {}

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

3️⃣ Servicio que Consume la API Externa:
📁 service/ImagenService.java

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

        Imagen[] imagenes =
                restTemplate.getForObject(API_URL, Imagen[].class);

        return Arrays.asList(imagenes);
    }

    public Imagen obtenerImagenPorId(int id) {

        RestTemplate restTemplate = new RestTemplate();

        String url = API_URL + "/" + id;

        return restTemplate.getForObject(url, Imagen.class);
    }
}

* Este servicio:
- Realiza llamadas HTTP GET
- Consume la API pública
- Convierte el JSON en objetos Java

4️⃣ Controlador REST:
📁 controller/ImagenController.java

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

* Endpoints disponibles:
- Método	Endpoint	Descripción
- GET	/api/imagenes	Lista todas las imágenes
- GET	/api/imagenes/{id}	Obtiene una imagen específica

5️⃣ Clase Principal:
📁 ApiImagenesApplication.java

package com.api.imagenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiImagenesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiImagenesApplication.class, args);
    }
}

🚀 Ejecutar la API:
Desde IntelliJ IDEA ejecutar:
Run ApiImagenesApplication

Servidor local:
http://localhost:8080

🔎 Endpoints de la API:
Obtener todas las imágenes
GET
http://localhost:8080/api/imagenes

Respuesta
[
 {
  "albumId": 1,
  "id": 1,
  "title": "accusamus beatae ad facilis",
  "url": "https://via.placeholder.com/600/92c952",
  "thumbnailUrl": "https://via.placeholder.com/150/92c952"
 }
]

Obtener imagen por ID
GET
http://localhost:8080/api/imagenes/10

Respuesta:
imagen + url + thumbnail

🧪 Pruebas en Postman
1️⃣ Obtener todas las imágenes:
Request
GET
http://localhost:8080/api/imagenes

Resultado esperado:
200 OK

Body:
JSON con imágenes

2️⃣ Obtener imagen por ID:
Request
GET
http://localhost:8080/api/imagenes/5

Respuesta esperada:
Imagen + URL + Thumbnail

📊 Flujo Completo del Sistema
POSTMAN
   │
   ▼
API SPRING BOOT
   │
   ▼
API INTERNET (JSONPLACEHOLDER)
   │
   ▼
IMÁGENES + DATOS
   │
   ▼
RESPUESTA JSON

📦 Mejoras que Puedes Agregar (Muy Buenas para Universidad):

✔ Guardar imágenes en Oracle 19c
✔ Descargar imágenes automáticamente
✔ Crear documentación con Swagger / OpenAPI
✔ Implementar paginación
✔ Crear API Gateway real
✔ Dockerizar la API
✔ Implementar cache con Redis / .
