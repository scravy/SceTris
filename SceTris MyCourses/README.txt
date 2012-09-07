/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, Andre Zoufahl
 */

This is the "Final deliverable" from the team "scetris" who worked on the project "myCourses" in the context of the Students Contest on Software Engineering, 2011, provided by the ICSE.

To get an impression of the project, we recommend you reading the Summary Report, which is located in the directory `Summary Report`. The Source Code of the project is to be found in `Sources`. Information on how to start developing can be obtained form the `README.txt` located there.

The other parts of this archive contain the artifacts and other documentation created within our software engineering process. As our development process was losely based on the spiral model (also known as risk model). This means, that we worked in cycles (spirals), called "iterations". During those, we did a requirements elicitation and anticipated possible risks. We than changed the future plan according to what we thought was possible at the current state of understanding. Thus we developed many artifacts, which are grouped by iteration in the directories which are named after the following scheme: "Iteration <N> (<Topic of the Iteration>)".

The final artifacts, documenting the current state of our process, are to be found in "Final (after Iteration6)".

Our application is available as a ready-made executable, which can be installed using "scetris-install.jar". If you can not open it by simply double clicking it, you may want to start it like the following:
$ java -jar scetris-install.jar

Please note that you need to have JDK 6 installed in order to deploy the Tomcat Servlet Container, as well as a PostgreSQL-database. The database may be of version 8.3 or later (tested with 8.4 and the brand new 9.0). The Installer was tested on Microsoft Windows, Ubuntu Linux and Mac OS X.

An alternative way of running citrus is by using our sophisticated build-system, which offers you to deploy and run a standalone Tomcat Server as well as a Standalone scetris via the embedded Servlet-Container "Jetty". Have a look at the Sources-directory for further details.

There is also a live-demo, installed at http://score.imp.fu-berlin.de/ . You may login using the user-account `admin` with the password `score2011`. Other user-accounts may be created using the web-interface, some are also predefined. The passwords are `score2011` always.

Some user-manuals can be found in the directory containing the final artifacts ("Final ..." -> "Manual").

It is worth noting, that we did not update our Summary Report, since the enhancements suggested by our reviewers would not have fit into a 20 or 22-pages document. Also we did rather complex analysis (Benchmarks, Statistics about Lines of Code, etc.) of our project. These may be found in the according documents in the final artifacts directory. Most notable these documents are:
* Testing, Verification and Validation.pdf
* Lines of code per revision.pdf
and the Wiki excerpts (which explain a lot).


We would like to thank our professors Prof. Dipl.-Dr. Ing. Heinz Schweppe who attended our work and Prof. Dr. Lutz Prechelt who held our Software Engineering lectures and gave us some very useful advice for our own development process.


