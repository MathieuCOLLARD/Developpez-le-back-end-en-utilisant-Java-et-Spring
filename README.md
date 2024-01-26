# Estate

This project was generated with Spring initializr for Spring Boot with this configuration : 
- maven project
- java language
- java 17

## Start the project

Git clone:

> git clone https://github.com/MathieuCOLLARD/Developpez-le-back-end-en-utilisant-Java-et-Spring

Choose com.openclassrooms.api.ApiApplication file in configuration to run the project

## Configure the database 

Download mysql with the installer :

> https://dev.mysql.com/downloads/installer/

cd in the mysql folder or use mysql as global variable and run this command :

> 'mysql -u root -p'

Choose the right database using the command

> 'USE _databasename_'

Use the script file in the angular project to create tables and dependencies :

> ressources/sql/script.sql


## Ressources

### Mockoon env

Download Mockoon here: https://mockoon.com/download/

After installing you could load the environement

> ressources/mockoon/rental-oc.json

directly inside Mockoon 

> File > Open environmement

For launching the Mockoon server click on play bouton

Mockoon documentation: https://mockoon.com/docs/latest/about/

### Postman collection

For Postman import the collection

> ressources/postman/rental.postman_collection.json 

by following the documentation: 

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman


### MySQL

SQL script for creating the schema is available `ressources/sql/script.sql`
