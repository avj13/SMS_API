# SMS_API
Springboot Application for SMS in/out bound SMS check.

A micro service API server that exposes the following 2 APIs that accept JSON data as input to POST requests.
Service used to check the incoming and outgoing SMS(Short Message Service) and check if end user wants to end the Subcription.

  1. Authentication done in place : Basic Authentication mapped to the Postgres Database and role based access.
                                    If auth fails, returns error 4xx.
  
  2. API list:
             Function Name     :    uri                 :   HTTP Method
       
    SMSController
    a. SMS API's
        i.  inapi()            : "/api/inbound/sms"     :     POST
        2.  outapi()           : "/api/outbound/sms"    :     POST
        
    b. CRED operation API found in Controller Packages:  
    AccountController
         i. getAllAccount()   : "/getall"               :     GET
        ii. getAccountById()  : "/account/{id}"         :     GET
       iii. addAccount()      : "/account/add/{acc}"    :     POST
        iv. updateAccount()   : "/account/upd/{acc}"    :     PUT
         v. deleteAccount()   : "account/del/{id}"      :     DELETE
      
    PhoneNumberController
         i. getAllPhones()     : "/phone/getall"        :     GET
        ii. getPhoneById()     : "/phone/{id}"          :     GET
       iii. addPhone()         : "/phone/add/{phn}"     :     POST
        iv. updPhone()         : "/phone/upd/{phn}"     :     PUT
         v. delPhone()         : "/phone/del/{id}"      :     DELETE
     
    3. API Rules:
      
      a. inapi() 
         Input Parameteres :  <to> , <from> , <text>
         
         Input validation  :  length check, presence check, invalidity check, text value check
                                -   Returns error if any one of it fails.
                                
         Behaviour         :    -   If <text> has "STOP", <to> + <from> pair is stored in a cache for 4 hours.
                                -   If <to> is not found in database for the current account login session, return error.
      b. outapi()
         
         input Parameteres :  <to> , <from> , <text> .
         
         Input validation  :  length check, presence check, invalidity check, text value check.
                                -   Returns error if any one of it fails.
                                
         Behaviour         :    -   If <to> + <from> pair is found under <text> == "STOP"  case, return error.
                                -   Not allow more than 50 API request using the same <from> number in 24 hours,
                                    from the first use of the <from> and reset counter after 24 hours. Return error in case of limit reached.
                                -   If <from> is not found in database for the current account login session, return error.
                                
     4. Tech Stack :
        a. Spring boot Appication (STS)
        b. Postgres db
        c. Redis cache
        d. Postman
