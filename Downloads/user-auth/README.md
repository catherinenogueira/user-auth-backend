# User Authentication Backend – Dockerized (Assignment 2)

## Overview
This Spring Boot backend deals with user registration and login functionality.  
The goal of this assignment was to containerize the backend using Docker so that it can be easily built and run on any system.

---

## Build the Docker Image
To build the Docker image, make sure Docker Desktop is running and use the following command in the project root (where the Dockerfile is located):

```bash
docker build -t user-auth-backend .
