# Calculator API

API Restful para cálculo de operaciones matemáticas.

# Compilación y ejecución

Ejecutar los comandos en la carpeta raíz del proyecto.

Para instalar la librería de traza en el repositorio maven local

````bash
mvn install:install-file -Dfile=lib/tracer-1.0.0.jar -DgroupId=io.corp -DartifactId=calculator -Dversion=1.0.0 -Dpackaging=jar
````

Para compilar

````bash
mvn clean install
````

Para ejecutar en local con el perfil 'local'.

`````bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
`````

Para construir una imagen docker

````bash
docker build -t calculator .
````

Para ejecutar la imagen docker

````
docker run -p 8080:80 calculator
````



Consideraciones

La api es un ejemplo de api que permite extender su funcionalidad fácilmente, simplemente creando nuevos servicios o se implementan nuevas operaciones de calculo con diferente número de parámetros.

La parte del código del paquete `core` podría estar en una librería común que compartiesen distintos proyectos que implementen APIs. Se ha incluido un `Advice` para el tratamiento de excepciones pero podría ampliarse a la propagación automática del `requestId` de las peticiones a las respuestas y la trazabilidad de peticiones y respuestas de forma trasversal y transparente para el código de las APIs.

Entiendo que la librería `tracer-1.0.0.jar` tiene un error de implementación ya que la clase `TracerImpl` no implementa la interfaz `TracerAPI`, por lo tanto en el código se ha usado la implementación directamente. En cuanto se corrigiese el error en la librería debería sustituirse de tal manera que la aplicación no conociese en absoluto la implementación.
