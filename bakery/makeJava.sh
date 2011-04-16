#!/bin/sh

MANAGER=RelationManager
PARAMS="--stringparam targetPackage de.fu.scetris.data --stringparam managerName $MANAGER"

# xsltproc --stringparam relation '#manager' --stringparam targetPackage 'de.fu.scetris.data' --stringparam managerName 'RelationManager' autoTemplate.xsl Relations.full.xml

xsltproc --stringparam relation '#manager' $PARAMS autoTemplate.xsl Relations.full.xml > java/$MANAGER.java
for relation in `xsltproc --stringparam javaize yes lib/listRelations.xsl Relations.full.xml`
do
	xsltproc --stringparam relation $relation $PARAMS autoTemplate.xsl Relations.full.xml > java/$relation.java || exit	
done
