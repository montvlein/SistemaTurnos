# SistemaTurnos

# construir la imagen
docker build -t turnera

# si surge un problema en el build con mvnw, limpiar 'los espacios en blanco' del archivo corriendo desde consola:
# sed -i 's/\r$//' mvnw

# corriendo por primera vez el contenedor
docker run -p 8080:8080 --name SistemaTurnos turnera

# para parar el contenedor
docker container stop SistemaTurnos

# para iniciar el contenedor
docker container start SistemaTurnos
