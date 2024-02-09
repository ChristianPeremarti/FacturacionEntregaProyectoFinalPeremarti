README - [FacturacionEntregaProyectoFinalPeremarti]
Descripción
El proyecto consiste en desarrollar una aplicación que permita administrar las ventas de un comercio. Posee tres actores: cliente, producto y comprobante. 

Requisitos
Se utilizó la versión 17 de Java y la versión 3.6.3 de Maven.
La versión de Sprigboot utilizada fue 3.2.2
Las librerías exterias que se incorporaron al proyecto fueron:
- Lombok
- Spring Web
- Spring Data JPA
- MySQL Driver

Instalación
Para instalar y configurar el código se utilizó maven clean install y la opción "run" de Springboot

Uso
Cuando el código compila se puede realizar el GET y el POST en POSTMAN para ver su funcionamiento

Estructura del Proyecto
Se crearon 4 carpetas: controller, models, repository, service.

El controller actúa como intermediario entre la interfaz de usuario y el models. Cada uno de los models posee su controlador. Dentro del controller se encuentra los comandos Get y Post para poder interactuar con POSTMAN.
El service posee la lógica de la aplicación. El service del comprobante posee gran parte de la lógica correspondiente a:
- Validar cliente existente
- Validar productos existentes
- Chequear la cantidad de productos solicitados sea menor o igual que el stock del producto
- Reducir el stock en la cantidad de unidades vendidas
- Consultar el servicio rest "wordclock" para obtener la fecha en el comprobante
- Calcular el precio total de la venta

Las querys para armar la base de datos en "MySql workBench" se crearon dentro de "resources" en el archivo schema.sql
Para poder gestionar la base de datos se utilizó el software "XAMPP"

Dentro de la carpeta repository se encapsula la lógica para recuperar, almacenar, actualizar y eliminar objetos del almacenamiento de datos, lo que permite una gestión de los datos en la aplicación

Por último se realizaron pruebas en POSTMAN para comprobar su funcionalidad y se almacenó la collection en la cartea "postman"
