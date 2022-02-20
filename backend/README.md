`docker run -d --name postgres -e POSTGRES_DB=postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=secret -p 5432:5432 postgres:alpine`

# Exemple d'appel API

## Créer un utilisateur
`curl -X POST http://localhost:8081/api/v1/users -H 'Content-Type: application/json' -d '{"first_name":"test", "last_name":"test", "email":"ok.ok@toto.fr23"}'`