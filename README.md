
# **When reading the code, it may be ~~_slightly different_~~ from the report!**

#Config before run/build:
    - backend:
        cd src/main/resources/application.properties
        edit config [database]
    -   frontend:
        //TODO        

#languages, technologies, dependency:
    - backend:
        + language: JAVA
        + frameworks, libs: spring, hibernate, Lombok
        + ide: intellij
    
    -   frontend:
        + language: javascript
        + frameworks, libs: react, redux, material ui,...
        + ide: intellij
             
#build production:

    - backend:
        
        // TODO
        
    - frontend:
        cd project_location
       
        "config file package.json"
        
        npm run build
        
#how to run:

    - backend:
        cd project_location  
        mvn spring-boot:run
        
         
    - frontend:
        cd project_location
        npm start

#data_sample:
    you can using it for quickly test
    -location: resources/data_sample_db.sql
    
    -login:
        admin account:
            username: admin
            password: admin
        user account:
            username:chi
            password:123
#
    updating...
    
# **Thanks you!**
    
    
    
