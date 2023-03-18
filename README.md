# Calculator API

API Restful para cálculo de operaciones matemáticas.

# Compilación ejecución y pruebas

Ejecutar los comandos en la carpeta raíz del proyecto.

## Prerequisitos

Antes de ejecutar la aplicación hay que instalar la librería de traza en el repositorio Maven local.

````bash
mvn install:install-file \
-Dfile=lib/tracer-1.0.0.jar \
-DgroupId=io.corp \
-DartifactId=calculator \
-Dversion=1.0.0 -Dpackaging=jar
````



## Ejecución en local

Primero se compila la aplicación con el comando:

````bash
mvn clean install
````

Luego se ejecuta con el perfil 'local' con el comando:

`````bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
`````

## Ejecución en un contenedor

Primero se construye la imagen Docker con el comando:

````bash
docker build -t calculator .
````

Luego se ejecuta la imagen Docker con el comando:

````
docker run -p 8080:80 calculator
````

## Pruebas

Para probar la operación de suma se puede enviar una petición con el comando `curl`:

````bash
curl --location 'localhost:8080/calculator/api/calculate' \
--header 'Content-Type: application/json' \
--data '{
    "operationType": "ADD",
    "operand1": "1",
    "operand2": "2"
}'
````

El resultado debe ser:

````json
{
    "resultValue": "3"
}
````



Para probar la operación de resta se puede enviar una petición con el comando `curl`:

````bash
curl --location 'localhost:8080/calculator/api/calculate' \
--header 'Content-Type: application/json' \
--data '{
    "operationType": "SUBTRACT",
    "operand1": "15",
    "operand2": "4"
}'
````

El resultado debe ser:

````json
{
    "resultValue": "11"
}
````



## Documentación

Una vez arrancado el servicio se puede abrir en el siguiente enlace en el navegador:

http://localhost:8080/calculator/swagger-ui.html

para visualizar la documentación Open-Api Swagger de la API.

# Consideraciones

La api es un ejemplo de api que permite extender su funcionalidad fácilmente, simplemente creando nuevos servicios que implementen nuevas operaciones de calculo con diferente número de parámetros.

Se ha implementado de forma reactiva para permitir que, al implementar operaciones mas complejas, se puedan paralelizar los cálculos de forma simple.

Se han incluido dos tipos de test unitarios: uno que realiza una petición que se procesa por el controlador del recurso y otra que prueba el servicio levantando el contexto de Spring e inyectándolo. La primera prueba se asemeja mas a una prueba de integración del servicio, la segunda es una prueba de una parte concreta. Faltan muchos mas test para lograr una cobertura mas completa del código, los que se han implementado son solo a modo de ejemplo.

La parte del código del paquete `core` podría estar en una librería común que compartiesen distintos proyectos que implementen APIs. Se ha incluido un `Advice` para el tratamiento de excepciones pero podría ampliarse a la propagación automática del `requestId` de las peticiones a las respuestas y la trazabilidad de peticiones y respuestas de forma trasversal y transparente para el código de las APIs.

Entiendo que la librería `tracer-1.0.0.jar` tiene un error de implementación ya que la clase `TracerImpl` no implementa la interfaz `TracerAPI`, por lo tanto en el código se ha usado la implementación directamente. En cuanto se corrigiese el error en la librería debería sustituirse de tal manera que la aplicación no conociese en absoluto la implementación.

