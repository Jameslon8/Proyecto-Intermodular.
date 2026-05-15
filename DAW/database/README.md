\# Base de Datos - HuntPMO



\## Resumen



En esta carpeta esta toda la parte de base de datos del proyecto HuntPMO.



HuntPMO es una organización ficticia que se encarga de registrar reportes de usuarios, detener e investigar anomalías y asignar agentes para  trabajar sobre ellas.



La base de datos sirve para guardar toda la información que después se usa en la aplicación de Java con JDBC.



\## Archivos de la carpeta



\- `CreacionTabla.sql`: crea la base de datos y todas las tablas.

\- `InsercionDatos.sql`: contiene datos de ejemplo para poder probar la aplicación.

\- `Diagrama.drawio`: diagrama E/R de la base de datos.



\## Tablas principales



Las tablas principales de la base de datos son estas:



\- `Persona`: guarda los datos personales.

\- `Usuario`: guarda los usuarios.

\- `Agente`: guarda los agentes.

\- `Reporte`: guarda los reportes escritos.

\- `Anomalia`: guarda las anomalías registradas.

\- `Instalacion`: guarda las instalaciones.

\- `Rol`: guarda las acciones que realizan los agentes.

\- `RolRevision`: guarda las revisiones de reportes.

\- `RolAsignacion`: guarda las asignaciones de agentes a anomalías.

\- `ReportaAnomalia`: relaciona reportes con anomalias.



\##Consultas:



Forman parte del VO del programa:



Ver reportes, usuarios, roles y anomalías.

Ver reportes de datos en específicos.

Crear reportes, usuarios, roles, anomalías.

Cambiar anomalías y reportes.

Eliminar usuarios.





\##Objetivo



El objetivo de esta parte es demostrar que se crear una base de datos relacionada con el proyecto, usando claves primarias, claves foráneas, relaciones entre tablas, datos de prueba y consultas SQL reales.

