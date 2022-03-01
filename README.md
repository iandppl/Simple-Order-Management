# SIMPLE ORDER MANAGEMENT API

Software Requirement:

&#8226; Spring boot version: 2.6.4 and above

&#8226; Java version: 11 and above

&#8226; Postgres version: 14 and above

&#8226; Docker

Installation:

&#8226; Open terminal and run these commands:
1) docker pull postgres:alpine (To download the docker image of postgres)
2) docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine (run the postgres docker image locally)
3) docker exec -it postgres-spring bash (execute the image using bash command)
4) psql -U postgres (use super user to enter to postgres command prompt)
5) CREATE DATABASE simpleordermanagement (create the database that is required for this application to run)

&#8226; Run "mvn clean install" to install the application

&#8226; Run "mvn spring-boot:run" to run the application

APIs:
1) Get "/api/item" (get all items)
2) Post "/api/item" (create new item) and takes in ItemDto object, example:{
   "id": 1,
   "size": "UK 30",
   "price": 12.5,
   "weight": 5.5
   }
3) Get "/api/order/{orderId}" (get order using orderId)
4) Post "/api/item" (create new order) and takes in OrderDto object, example:{
   "orderId":1,
   "customer":"John",
   "paymentType":"Master Card",
   "updatedBy":"Jenny",
   "orderQuantityDtoList":[
   {
   "orderQuantityId":13,
   "quantity":5,
   "itemDto":{
   "id": 1,
   "size": "UK 30",
   "price": 12.5,
   "weight": 5.5
   }
   }
   ]
   }
5) Put "/api/item" (update existing order) and takes in OrderDto object, example:{
   "orderId":1,
   "customer":"John",
   "paymentType":"Master Card",
   "updatedBy":"Jenny",
   "orderQuantityDtoList":[
   {
   "orderQuantityId":13,
   "quantity":5,
   "itemDto":{
   "id": 1,
   "size": "UK 30",
   "price": 12.5,
   "weight": 5.5
   }
   }
   ]
   }
6) Delete "/api/order/{orderId}" (delete order using orderId)