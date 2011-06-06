for x in java sql css xml aj sh xsl
do
	echo -n "$x: "
	cat -s *.$x */*.$x */*/*.$x */*/*/*.$x */*/*/*/*.$x */*/*/*/*/*.$x */*/*/*/*/*/*.$x */*/*/*/*/*/*/*.$x 2>/dev/null | wc -l
done
echo -n "(data): "
cat -s src/de/fu/scetris/data/*.java 2>/dev/null | wc -l
