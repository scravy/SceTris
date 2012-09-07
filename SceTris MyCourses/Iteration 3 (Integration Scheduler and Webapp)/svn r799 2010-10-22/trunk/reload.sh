#!/bin/sh
wget --quiet --user=admin --password=hateyou -O - http://localhost:8080/manager/html/reload?path=/scetris | grep OK

