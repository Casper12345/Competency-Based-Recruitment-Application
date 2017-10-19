# Competency-Based-Recruitment-Application

### About:
A  web-based recruitment application that allows HR managers to upload competency-based job description and match these against candidate profiles uploaded by recruiters. The algorithm matches vectors of weighted skills and competencies against skill and competency requirements using Euclidean similarity metrics. The application has three types of user domains. A super user that can add skills and competencies as well as other users. A recruitment agent user that can add candidate profiles and rates their skills and competencies and a HR Manager user that can add job descriptions and match them against candidate profiles in the data base. The application has an internal messaging system that allows users to message each other internally through the system. 

### Software stack:
The application is build using Scala version 2.12
SBT was used as built tool.
The Play2 framework was used. 
The Twirl template engine was used for rendering views. 
Bootstrap 3 and 4, D3.js, JQuery, CSS and Java Script was used at the front end.

### How to Run the Program:
The application is run using the following command: 

$ SBT run

### Tests:
The application is build using TDD and unit tests are created with JUnit3 and integration tests with Selenium.

Test are run with: 

$ SBT test
