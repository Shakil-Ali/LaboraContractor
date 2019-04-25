# Group 16 Contractor Software Project


**LABORA - GROUP 16 - SOFWARE PROJECT**

Below is the break down of our contractor application

______________________________________

Getting Started - Dependencies:
You will need to download Android Studio, and either download an emulator or use an android device to run the application(s)

_______________________________________

**Contractorr:**
Contractor consists of 6 activites.

| **CLASS FILE** | **DESCRIPTION** |
| --- | --- |  
| LoginActivity | The LoginActivity is the first page when the application is opened. It has our logo, as well as 2 input fields (username and password) and a submit button (login). This allows already registered service requesters to login. It incorporates Firebase’s ‘FirebaseAuth’ object. |
| RegisterActivity | If the service requester does not have an account, this registration area will allow them to make one. This is the first section of registration, so it asks for a username, and password. Firebase’s ‘FirebaseAuth’ is also incorporated, therefore it will display a toast if the credentials are taken. |
| RegisterActivity2 | If successful after ‘RegisterActivity’, the user will be taken to this page (activity). This is the second part of the registration process. Users will be required to enter their name, address, phone number etc. Once this stage is completed, an account has been registered, and they can now view their personal profile. |
| MainActivity | The profile page opens up when a contractor user has successfully logged in or registered an account. It displays an avatar, as well as menu options. The two options include ‘go online’ (find jobs) and ‘logout’ (logout of then account). The buttons are enlarged, in order to meet accessibility and usability requirements.  |
| MapActivity | The MapActivity file displays a map with the location of the customer. Once the contractor accepts the job it will take them to the map and they can follow the route to the destination of the customer. |
| Done | This is the final page displayed when a contactor finds a service. A tick symbol and confirmation message will appear. It confirms the request has been submitted. |


