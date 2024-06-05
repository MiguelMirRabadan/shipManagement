------------- COMENTARIOS DEL AUTOR ----------------

El proyecto está planteado utilizando arquitecura hexagonal.

Tiene a su disposición, un diagrama de la estructura del proyecto para facilitar su visualización.

Cal mencionar que por seguridad, faltaría implementar ciertos aspectos, cómo springSecutiry para el acceso al API, con
su respectivo token, y un control de las variables utilizadas para el acceso a BDD u otras configuraciones implementando
la dependencia de spring-cloud-starter-config, entre otras.

Debido al tiempo y evitar más demora, no he podido implementar la librería de control de migraciones de BDD Flyway,
que evitaría ciertos errores al ejecutar el script de carga de películas. Tampoco he podido configurar mejor un sistema de Logs
más claro y preciso ( colores, mayor filtrado de salida en dbug, etc). Detalles que facilitan más el control y comprensión del
aplicativo.

Por otra parte, cal mencionar que para la configuración de la mensagería, he optado por dockerizar un broker kafka para ahorrar tiempo,
entiendo que tal vez se desee evaluar un mayor conocimiento de este servicio pero dadas las circunstancias, con daros un ejemplo
de un publisher y un "consumer" creo que serviría para demostrar un mínimo. No que herido entrar en consumer groups, particiones y
sus duplicados, etc.


---------- INTRUCCIONES ------------

Para montar la BDD MySQL en docker, necesitaremos ejecutar el build:

    docker build -t mysql_shipdb -f Dockerfile .

Ya dejo a manos de cada uno si desea utilizar un puerto u otro. A la hora de montar el contenedor, indicar el puerto 3308:3306
Hay un script con users, aunque aún no lo tengo del todo perfilado, sugiero usar el usuario root como tengo configurado.

Para la dockerización de Kafka, está la propía guía en el apartado.


------------


Y creo que no me dejo nada más, espero que sea de su agrado. Ante cualquier problema, no duden en preguntarme.
miguel_mir.92@hotmail.com



