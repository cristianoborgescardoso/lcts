#!/bin/bash
#find files/* > fileList
find files/ -iname '*java' > fileList
java -jar LongestTokenSubsequences.jar fileList 

