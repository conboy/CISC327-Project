# base image for docker (the machine it runs on)
FROM adoptopenjdk/openjdk11

# Directory in which the following tasks are performed
WORKDIR /home/qbnb
# Copies all files from the root of the project to the WORKDIR
COPY . .

EXPOSE 8080
EXPOSE 8025

CMD ["./start.sh"]

