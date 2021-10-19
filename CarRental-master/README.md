

How to run the project: 

1. Run RentalApplication.java

Flow of the Application:

Endpoint: localhost:8181/

Two types of User Access:
1. User
2. Admin

Flow 1: As User
Click on SignUp: Register a new user
Click on SignIn: Login with the credentials
Check "My Rentals" tab: If you are a new user, it should not have any rows
Click on "Place A New Request" tab: Once the request is placed, you will see it in "My Rentals" tab. Also, once this request is made, you will no longer see this car 
when you again click on "Place new request" tab. This is because we cannot rebook a car unless its status has been changed to "Car Returned/Available" by the admin

Flow2: As Admin
Admin user is already created in the database. SQL Insert and create commands are shared in the resource folder. Admin user cannot be created from the UI.
Click on SignIn: Login with the admin credentials
Check "Manage Rental Orders": This should show all the orders placed by the users till now. Admin has the right to update the status of order to "Car Returned/Available" once the user returns the car. Also, once the admin changes the status of car to "Car Returned/Available", the car is then again available for booking by all users.
Once the admin changes the status of the car, the same is reflected when the user logs in with his credentials.
Check "Manage Cars" tab: From there the admin can delete and add new cars as many as required.

Sample user: Username: 'Hitakshi' Password: 'hitakshi1234' 
Sample admin: Username: 'John' Password: 'john1234' 
