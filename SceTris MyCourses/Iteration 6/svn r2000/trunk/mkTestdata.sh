#!/bin/sh
#
# Part of SCORE myCourses
# 
# Team Scetris: David Bialik, Julian Fleischer,
# Hagen Mahnke, Konrad Reiche, André Zoufahl
#
java -cp bin:lib/servlet-api-2.5-20081211.jar:lib/postgresql-8.4-702.jdbc4.jar de.fu.scetris.TestDataGenerator $@
