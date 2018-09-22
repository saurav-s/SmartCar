# SmartCar
This repository is created for smart car challange.

Cloud Endpoint<br/>
The application is currently deployed on aws server on the given location.

http://smartcar.us-east-1.elasticbeanstalk.com  <br/><br/>


Local System Setup<br/>
* Clone git repo
`git clone https://github.com/saurav-s/SmartCar.git`

* How to install and run the application locally?

	* From terminal<br/>
	  Note: Maven should be installed. [How to install Maven](https://maven.apache.org/install.html) <br/>
      Test if maven is installed using the command `mvn -version` <br/>

	* Install the application:<br/>
      Go to `{local project folder path}/SmartCar/`. 
      For example: <br/>
      `cd ~/user/xyz/SmartCar/`<br/>

      	* Make sure pom.xml is present in this location<br/>
      	* maven install:<br/>
        `mvn clean install`</br>

      	* Run tests<br/>
      	`mvn test`<br/>

      	* Run the application:<br/>
      	Go to `target` folder in `{local project folder path}/SmartCar/` which will contain the `war` file generated.<br/>

      	For example:<br/>
      	`cd ~/user/xyz/smartcar/target`<br/>

      	Make sure a war file named `SmartCar-0.0.1-SNAPSHOT.war` is present at this location.<br/>

      	* Finally, run the application using the command: <br/>

      	`java -jar SmartCar-0.0.1-SNAPSHOT.war`<br/>


Accessing EndPoints:
To access the end points of the api use the application base url + /vehicles/... format 
Ex: http://localhost:8080/vehicles/1234  <br/>
    http://smartcar.us-east-1.elasticbeanstalk.com/vehicles/1235/battery . <br/>

Use Rest Clients like postman etc to access the api's.

