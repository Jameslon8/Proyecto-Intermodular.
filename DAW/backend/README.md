\# Programacion - HuntPMO



\## Resumen



Esta parte del proyecto contiene la aplicacion Java de HuntPMO.  

La aplicación permite gestionar usuarios, agentes, reportes, anomalias y roles usando una base de datos MySQL mediante JDBC.



El objetivo de esta parte es demostrar la conexion entre Java y la base de datos, usando clases, metodos, menus por consola y operaciones CRUD.



\## Tecnologias usadas



\- Java

\- Maven

\- JDBC

\- MySQL

\- MySQL Connector/J



\## Estructura del codigo



El proyecto esta dividido en paquetes para separar responsabilidades:



\- `APP`: contiene la clase principal que inicia el programa.

\- `controller`: contiene los menús y la interacción con el usuario.

\- `service`: contiene la logica intermedia entre el menu y los DAO.

\- `HuntPMODAO`: contiene las consultas SQL y el acceso a la base de datos.

\- `HuntPMOVO`: contiene las clases que representan las tablas de la base de datos.

\- `config`: contiene la clase de conexion JDBC.





\## Funcionalidades implementadas



La aplicacion permite:



\- Registrarse como usuario.



\- Iniciar sesion como usuario.

\- Iniciar sesion como agente.

\- Acceder como director mediante una contraseña.



\- Ver datos personales del usuario o agente.



Dentro de usuario:

\- Crear reportes.

\- Ver reportes propios de un usuario.





Dentro de agente

\- Ver todos los reportes desde el menu de agente.

\- Ver reportes con anomalias.

\- Ver reportes sin anomalias.

\- Relacionar reportes con anomalias.

\- Revisar reportes y cambiar su estado.

\- Ver anomalias.

\- Filtrar anomalias por tipo, peligrosidad o instalacion.

\- Ver la descripcion de una anomalia.

\- Iniciar trabajos sobre anomalias mediante roles de asignacion.

\- Finalizar trabajos y actualizar la descripcion de una anomalia.

\- Eliminar usuarios.



Director:

\- Crear anomalias desde el acceso director.

\- Crear agentes desde el acceso director.





\## Conexion con la base de datos



La conexion se realiza mediante JDBC en la clase:



`config.Conexion`



La aplicación usa la base de datos `HuntPMO` en MySQL.





\## CRUD



El proyecto realiza:



CREATE: insertar usuarios, agentes, reportes, roles, anomalias e instalaciones.

READ: listar usuarios, agentes, reportes, anomalias, roles y relaciones.

UPDATE: actualizar estado de reportes y descripcion de anomalias.

DELETE: eliminar usuarios y agentes.



Ejemplo de URL usada:



```java

jdbc:mysql://localhost:3306/HuntPMO



