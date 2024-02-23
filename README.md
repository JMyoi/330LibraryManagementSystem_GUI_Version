<h1>330 Library Management System GUI version</h1>
<p>330 Library Management System is a software application and platform designed to automate and 
  streamline various tasks and processes involved in managing a library's resources. Its primary purpose is to
  efficiently organize, catalog, circulate, and track library materials while providing easy access to library users.</p>
  
<p>This GUI version of this project is the sequel to the terminal version which can be accessed
  <a href = "https://github.com/JMyoi/330_LMS_Terminal_Version">HERE</a></p>
  

<h4>Technology used</h4>
<ul>
  <li>Java</li>
    <li>JavaFX</li>
  <li>CSS</li>
</ul>

<p>This application caters to two types of users found in the library environment the librarian and the member. Core functionalities are given
  for each type of user providing an easy and satisfying process. </p>
  
<h2>User Stories</h2>
As a User
<ul>
  <li> As a user, I want to be able to create a new account
</li>
  <li> As a user, I want to be able to log out.
</li>
</ul>
As a Member
<ul>
  <li>As a Member, I want to see the books I have borrowed</li>
    <li>As a Member, I want a user interface to view and borrow books.</li>
  <li>As a Member, I want to be able to remove a book I have borrowed</li>

</ul>
As a Librarian
<ul>
  <li>As a librarian, I want to see all the books in the library and their
information.</li>
  <li>As a librarian, I want to be able to view all the transactions from the
members.</li>
  <li>As a librarian, I want to be able to add a new book to the library.</li>
  <li>As a Librarian, I want to be able to view all the users in the library.
</li>
</ul>

<h3>Adversity; Persistence and Object Reinitialization Bug</h3>
<p>
A significant challenge we encountered during the GUI development was the reinitialization of
the library object upon switching between scenes. The issue surfaced when, upon switching
scenes, the library object underwent reinitialization, resulting in the loss of data
modifications made in previous scenes such as adding a new user. To address this issue, I
researched and implemented a communication mechanism between scenes. Each controller
class, responsible for managing individual scenes, utilized a loader to receive and maintain the
library object. This ensured the persistence of data across scene transitions. While this solution
required extensive code restructuring and external internet assistance, it effectively resolved the
data persistence issue and enhanced code clarity. By assigning dedicated controller classes to
each scene, we achieved modularity and improved code maintainability. Note that this frustrating bug could have been avoided if given 
the ample amount of time to learn JavaFX. Thanks to YouTube I have found useful resources that have not only helped me learn JavaFX but also 
helped me understand and solve niche bugs as stated here.
</p>


<p>Contributors and their roles</p>
<ul>
  <li>Jay Chen - Developer</li>
  <li>Joshi Joseph - QA</li>
  <li>Brain LaBarbera - Manager/TechLead</li>
</ul>
<p>This project is for CSC330 - Object-Oriented Software Design as the final project of the class.</p>

<h1>Preview and Snapshots</h1>
<img src = "images/Screenshot 2024-02-22 235249.png" width = "500" >
<img src = "images/Screenshot 2024-02-22 235259.png" width = "500" height = "500">
<img src = "images/Screenshot 2024-02-22 235304.png" width = "500" height = "500">
<img src = "images/Screenshot 2024-02-22 235310.png" width = "500" height = "500">
<img src = "images/Screenshot 2024-02-22 235326.png" width = "500" height = "500">
<img src = "images/Screenshot 2024-02-22 235347.png" width = "500" height = "500">
<img src = "images/Screenshot 2024-02-22 235401.png" width = "500" height = "500">
<img src = "images/Screenshot 2024-02-22 235412.png" width = "500" height = "500">


