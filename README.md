# franchise_management
Franchise Management System - README
Descripción del Proyecto
API reactiva para la gestión de franquicias, sucursales y productos, desarrollada con:
Spring Boot WebFlux (Programación Reactiva)
R2DBC (Acceso reactivo a bases de datos)
PostgreSQL
Docker
Pruebas unitarias
Prerrequisitos
Java 17 o superior
Maven 3.8+
Docker y Docker Compose
PostgreSQL (opcional si no se usa Docker)
Configuración del Entorno Local
1. Clonar el repositorio
bash
git clone [URL_DEL_REPOSITORIO]
cd franchise-management
2. Configuración de la base de datos
Opción A: Usar Docker (recomendado)
bash
docker-compose up -d
Esto levantará:
PostgreSQL en el puerto 5432
La aplicación en el puerto 8080
Opción B: Base de datos local
Instalar PostgreSQL
Crear una base de datos llamada franchisedb
Configurar las credenciales en application.properties
3. Configuración de la aplicación
El archivo application.properties debe contener:
properties
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/franchisedb
spring.r2dbc.username=postgres
spring.r2dbc.password=password
spring.sql.init.mode=always
Construcción y Ejecución
Construir la aplicación
bash
mvn clean package
Ejecutar la aplicación
Opción A: Con Maven
bash
mvn spring-boot:run
Opción B: Con el JAR construido
bash
java -jar target/franchise-management-*.jar
Opción C: Con Docker
Construir la imagen:
bash
docker build -t franchise-management .
Ejecutar el contenedor:
bash
docker run -p 8080:8080 franchise-management
Endpoints Disponibles
Franquicias
POST /api/franchises - Crear nueva franquicia
GET /api/franchises - Listar todas las franquicias
GET /api/franchises/{id} - Obtener franquicia por ID
PUT /api/franchises/{id}/name - Actualizar nombre de franquicia
POST /api/franchises/{franchiseId}/branches - Agregar sucursal a franquicia
Sucursales
POST /api/branches/{branchId}/products - Agregar producto a sucursal
GET /api/branches/{branchId}/products - Listar productos de sucursal
Productos
DELETE /api/branches/{branchId}/products/{productId} - Eliminar producto
PATCH /api/branches/{branchId}/products/{productId}/stock - Actualizar stock
PATCH /api/branches/{branchId}/products/{productId}/name - Actualizar nombre
GET /api/branches/{branchId}/products/highest-stock - Producto con mayor stock
Ejemplos de Requests
Crear franquicia
bash
curl -X POST -H "Content-Type: application/json" \
-d '{"name":"Mi Franquicia"}' \
http://localhost:8080/api/franchises
Agregar sucursal
bash
curl -X POST \
http://localhost:8080/api/franchises/1/branches?branchName=Sucursal%20Central
Agregar producto
bash
curl -X POST -H "Content-Type: application/json" \
-d '{"name":"Producto 1", "stock":100}' \
http://localhost:8080/api/branches/1/products
Pruebas
Ejecutar pruebas unitarias
bash
mvn test
Ejecutar pruebas con cobertura
bash
mvn clean verify
Despliegue en la Nube
Para desplegar en AWS u otro proveedor cloud, consulta los archivos de Terraform incluidos en el proyecto.
Estructura del Proyecto

franchise-management/
├── src/
│   ├── main/
│   │   ├── java/com/franchise/management/
│   │   │   ├── config/           # Configuraciones
│   │   │   ├── controller/       # Controladores reactivos
│   │   │   ├── dto/              # Objetos de transferencia
│   │   │   ├── exception/        # Manejo de excepciones
│   │   │   ├── model/            # Entidades
│   │   │   ├── repository/       # Repositorios reactivos
│   │   │   ├── service/          # Servicios reactivos
│   │   │   └── FranchiseManagementApplication.java
│   │   └── resources/            # Archivos de configuración
│   └── test/                     # Pruebas unitarias
├── docker/                       # Configuraciones Docker
├── terraform/                    # Infraestructura como código
├── Dockerfile                    # Definición de imagen Docker
├── docker-compose.yml            # Orquestación de contenedores
└── pom.xml                       # Configuración Maven

Licencia
Este proyecto está bajo la licencia MIT.






