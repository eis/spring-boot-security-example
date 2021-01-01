Simple Spring Boot security example
===================================

<a href="https://travis-ci.org/eis/spring-boot-security-example" title="Build Status"><img src="https://api.travis-ci.org/eis/spring-boot-security-example.svg?branch=mvc"></a>

Answers to [http://localhost:8080/login](http://localhost:8080/login). Login is admin/123456.

Docker stuff
------------

```bash
# build container from Dockerfile
docker build -t spring-boot-security-example .

# run container
docker run -ti -p 0.0.0.0:8080:8080 --rm spring-boot-security-example

# call your container
curl `docker-machine ip default`:8080

# stop your container
docker stop CONTAINERNAME

# debug something
docker ps
```
