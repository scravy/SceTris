#!/bin/bash
function die {
	echo $1 1>&2
	exit 1
}
if [ ! -n "$PSQL" ] ; then
	PSQL=psql84
fi
which $PSQL > /dev/null || die "$PSQL not found"
echo "create..."
cat create.sql | $PSQL 3>&2 2>&1 1>/dev/null | grep -A2 ERROR 1>&2
echo "populate..."
cat populate.sql | $PSQL 3>&2 2>&1 1>/dev/null | grep -A2 ERROR 1>&2
