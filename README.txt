Generate Tomcat (default) without change configuration.properties and without copy resources
   mvn clean verify

Generate Tomcat with embedded resources
   mvn clean verify -Pembedded-resources,build-tomcat

Generate Tomcat with generated resources
   mvn clean verify -Pgenerated-resources,build-tomcat

