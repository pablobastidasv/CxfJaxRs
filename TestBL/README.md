To use Dockerfile's file of the project, first that all, you have to create the image provided into 
the *docker* folder, to do that, you have to launch the next command in a console with the prompt 
this folder (docker) 

    docker build -t tomcat8_java8 .

For the connection to mongodb, you have to start a mongo docker container, for this use the next command
    
    docker run -d --name mongodb mongo

Once you have the image, you need to create the container, you can do it with the next command.

    docker run --rm -ti -p 8080:8080 -p 1043:1043 --link mongodb:mongodb -v $PWD/webapps:/opt/tomcat/webapps --name egresos_bl tomcat8_java8

With this command, you going to have the Tomcat 8 started, the *webapp* folder into *docker* directory prepared 
to make deploys on tomcat server and the log console will be showing to us.

We also added a task on Gradle for to copy the generated .war into the *webapps* folder, the task is named 
*deployOnDocker*, when this task is ran it the war file is generated and copied into the *webapps* folder.