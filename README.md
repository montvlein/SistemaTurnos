# SistemaTurnos
___

Proyecto final para la materia Backend 01 de la carrera Certified Tech Developer

## SETUP
___
Las instrucciones son para construir y levantar el proyecto con Docker.
Seguir las instrucciones parado sobre la ruta inicial del proyecto.
 `$ cd ../[nombreProyecto]`

### construir la imagen

 ```bash
 $ docker build -t turnera .
 ```

#### posibles problemas
si surge un problema en el build con mvnw, limpiar 'los espacios en blanco' del archivo corriendo desde consola:
```bash
$ sed -i 's/\r$//' mvnw
```

### corriendo por primera vez el contenedor
```bash
$ docker run -p 8080:8080 --name SistemaTurnos turnera
```
### Parar el contenedor
```bash
$ docker container stop SistemaTurnos
```
### Iniciar el contenedor
```bash
$ docker container start SistemaTurnos
```