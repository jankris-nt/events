# events

###USED
- Java;
- ElasticSearch;
- Spring;
- Maven;

- Rest Assured tests;
- JSON Web Token(JWT) authentication;
- docker-compose.yml;
    -- Java 11;
    -- Elasticsearch 7.9.3;

###SETTINGS:
    First ask token.
    http://localhost:8080/authenticate POST query.
    ####Request body.
      {
      "username" : "testtask",
      "password" : "password"
      }

    ####Answer style
    {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dGFzayIsImV4cCI6MTYxNzAxODk1NSwiaWF0IjoxNjE3MDAwOTU1fQ.fyK9V3_u0-dDssNcVq1iNvYJP2iFRfWPayitbkLsgysSo3TyeXfUMOYBGdei5aFlJUCn2qq8DZ-XAvQKUp4stw"
    }

    #### Put token into the Header.
    - Key: Authorization
    - Value: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dGFzayIsImV4cCI6MTYxNzAxODk1NSwiaWF0IjoxNjE3MDAwOTU1fQ.fyK9V3_u0-dDssNcVq1iNvYJP2iFRfWPayitbkLsgysSo3TyeXfUMOYBGdei5aFlJUCn2qq8DZ-XAvQKUp4stw
    Bearer and space are important.

    Used: Context-Type application/json.
    
###QUERIES:
    - POST /event - create event (conference);
    Body (raw, JSON)
        {
          "id" : "2",
          "name" : "ruum22",
          "seats" : "14",
          "status" : "active"
        }
    (If id is not specified it is set automatically "id": "HcBYe3gBirsCmiC6Zz3Q")
    - GET /event/{id}/status - status info;
    - GET /event/{id}/free - free places;
    - PUT /event/{id}/status/{newStatus} - change status;
    - GET /event - all events;
    - GET /event/{id} - find event;
    - DELETE /eventdelete/{id} - delete event;
    - POST /event/{id}/person - add person to event;
        {
            "id" : "2",
            "name" : "Mati"
        }
    - DELETE /event/{id}/person/{name} - remove person from event.

###TESTING:
    Go to the container cmd.
    - docker exec -it rf-app /bin/bash
    - mvn test
    OR some kind IDE like Eclipse Version: 2020-09 (4.17.0).
