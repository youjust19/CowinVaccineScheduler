# CowinVaccineScheduler
Steps to use the app

1. Update the user section for user specific preferences
   File: application.properties
2. Build the app using maven: mvn clean install -Dmaven.test.skip=true
3. start the app: java -jar target/CowinVaccineScheduler-0.0.1-SNAPSHOT.jar
4. One available.. a alarm will be raised by application then
   open.. http://localhost:8080/captcha-submit.html?mobilenumber=<mobilenumber>
   -- 10 digit mobile number
   OTP will be sent to your number, enter the otp
4. captcha page will open, enter the captcha and submit.
   Booking will confirm if available else you will get NO CURRENT SESSION AVAILABLE
   
follow my github for more codes: https://github.com/pankajppr
