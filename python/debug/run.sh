#!/bin/bash

docker build -t container_name .

docker run -d --name container -p 8001:8001 -p 5678:5678  container_name
