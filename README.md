# nagarro-test

NOTE: please use db provided with the project as it has users configured.


1- database is available in the root project of the repository, you will have to place it somwewhere and provide database path in 
    applicaton.properties for the app to connect to the databaase
2- two users are currently configured in the db with encrypted password
    a. username: user
        password: user
        id : 1
    b. username: admin
       password: admin
        id : 2
        

3. LOGIN API       
http://localhost:8091/api/auth/login

REQUEST BODY : 

{
"username":"admin",
"password":"admin"
}

Login Instructions:

    a. hit the above api.
    b. if you receive a "user already logged in message", please logout using logout api
    c. if loggin is successful, a JWT will be set in the cookie automatically, nothing is required explicitly to set this token.
    d. after login, you may test the account/statement api
    
4. ACCOUNT STATEMENT API 

http://localhost:8091/account/statement?account-id=1&from-date=14.10.2020&to-date=14.10.2020&from-amount=10&to-amount=197
    a. please supply dates in dd.MM.yyyy format 
    b. api gives presendence to date filters and then amount filters
    
5. LOGOUT API 

http://localhost:8091/api/auth/logout/1   -- user
http://localhost:8091/api/auth/logout/2   -- admin






