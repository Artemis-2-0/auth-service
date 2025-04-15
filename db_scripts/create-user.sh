use auth-service;

db.createUser({
    user: "authserviceadmin",
    pwd: "password",
    roles: [
        { role: "readWrite", db: "auth-service" }
    ]
});