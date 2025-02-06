# Ticket API

## Requisitos
- Java 17
- Docker

## Ejecución
1. Clona el repositorio.
2. Ejecuta `docker-compose up` para levantar la base de datos.
3. Ejecuta la aplicación Spring Boot.
4. Prueba los endpoints usando Postman o cURL.

## Endpoints
- `POST /tickets` - Crear un ticket
- `GET /tickets` - Obtener todos los tickets (paginados)
- `GET /tickets/{id}` - Obtener un ticket por ID
- `PUT /tickets/{id}` - Actualizar un ticket
- `DELETE /tickets/{id}` - Eliminar un ticket

##Swagger
- http://localhost:8081/api/swagger-ui/index.html#/

## GraphQL

### Endpoints
- **GraphiQL**: `http://localhost:8080/graphiql`
- **GraphQL Endpoint**: `http://localhost:8080/graphql`

### Ejemplos de consultas y mutaciones
1. Crear un ticket:
   ```graphql
   mutation {
     addTicket(usuario: "Juan", estatus: ABIERTO) {
       id
       usuario
       estatus
     }
   }
2. Actualizar un ticket
  ```graphql
  mutation {
    actualizarTicket(id: 1, usuario: "Juan", estatus: "cerrado") {
      id
      usuario
      estatus
    }
  }
3. Eliminar un ticket
  ```graphql
  mutation {
    eliminarTicket(id: 1)
  }
4. Obtener todos los Tickets
  ```graphql
  query{
  	getAllTickets(page:0, size:10) {
      id
      usuario
      estatus
      fechaCreacion
      fechaActualizacion
    }  
  }
5. Obtener ticket por Id
  ```graphql
  query { 
    getTicketById(id: 2){
      usuario
      estatus
    }
  }
