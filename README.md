# CONFIG STARTUP

To launch the whole infrastructure use

Compile the backend to speed up

```bash
cd backend_src
mvn clean package
```

Launch docker compose

```bash
sudo docker compose up -d
```

The frontend uses the reverse proxy of nginx in the port 80

