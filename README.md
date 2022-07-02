# SistemaTurnos

docker build -t turnera
docker run -p 8080:8080 --name SistemaTurnos turnera

#si surge un problema en el build con mvnw ejecutar
#sed -i 's/\r$//' mvnw
