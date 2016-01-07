package ar.com.scala4impatient


object Chapter4Exercises {

	/*
	1. Set up a map of prices for a number of gizmos that you covet. Then produce a
	second map with the same keys and the prices at a 10 percent discount.
	*/
	val original = Map((1, 10), (2, 12), (1, 6), (1, 4), (1, 24))
                                                  //> original  : scala.collection.immutable.Map[Int,Int] = Map(1 -> 24, 2 -> 12)
	val discount = for((n, price) <- original) yield (n, price * 0.9)
                                                  //> discount  : scala.collection.immutable.Map[Int,Double] = Map(1 -> 21.6, 2 ->
                                                  //|  10.8)
	
	/*
	2. Write a program that reads words from a file. Use a mutable map to count how
	often each word appears. To read the words, simply use a java.util.Scanner:
		val in = new java.util.Scanner(java.io.File("myfile.txt"))
		while (in.hasNext()) process in.next()
	Or look at the “Files and Regular Expressions” chapter for a Scalaesque way.
	At the end, print out all words and their counts.
	*/
	
	val counters2 = scala.collection.mutable.Map[Char, Int]()
                                                  //> counters2  : scala.collection.mutable.Map[Char,Int] = Map()
	val fileName2 = "/home/diego/Development/code/scala4impatient/resources/chapter4.2.txt"
                                                  //> fileName2  : String = /home/diego/Development/code/scala4impatient/resources
                                                  //| /chapter4.2.txt
	val in2 = new java.util.Scanner(new java.io.File(fileName2))
                                                  //> in2  : java.util.Scanner = java.util.Scanner[delimiters=\p{javaWhitespace}+]
                                                  //| [position=0][match valid=false][need input=false][source closed=false][skipp
                                                  //| ed=false][group separator=\,][decimal separator=\.][positive prefix=][negati
                                                  //| ve prefix=\Q-\E][positive suffix=][negative suffix=][NaN string=\Q�\E][inf
                                                  //| inity string=\Q∞\E]
	
	while (in2.hasNext()) {
		for( c <- in2.next()) {
			counters2(c) = counters2.getOrElse(c, 0) + 1
		}
	}
	
	counters2                                 //> res0: scala.collection.mutable.Map[Char,Int] = Map(w -> 2, e -> 1, d -> 2, 
                                                  //| a -> 1, r -> 3, l -> 3, H -> 1, f -> 1, o -> 3)
	
	/*
	3. Repeat the preceding exercise with an immutable map.
	*/
	
	var counters3 = Map[Char, Int]()          //> counters3  : scala.collection.immutable.Map[Char,Int] = Map()
	val fileName3 = "/home/diego/Development/code/scala4impatient/resources/chapter4.2.txt"
                                                  //> fileName3  : String = /home/diego/Development/code/scala4impatient/resource
                                                  //| s/chapter4.2.txt
	val in3 = new java.util.Scanner(new java.io.File(fileName3))
                                                  //> in3  : java.util.Scanner = java.util.Scanner[delimiters=\p{javaWhitespace}+
                                                  //| ][position=0][match valid=false][need input=false][source closed=false][ski
                                                  //| pped=false][group separator=\,][decimal separator=\.][positive prefix=][neg
                                                  //| ative prefix=\Q-\E][positive suffix=][negative suffix=][NaN string=\Q�\E]
                                                  //| [infinity string=\Q∞\E]
	
	while (in3.hasNext()) {
		for( c <- in3.next()) {
			val count = counters3.getOrElse(c, 0) + 1
			counters3 = counters3 + (c -> count)
		}
	}
	
	counters3                                 //> res1: scala.collection.immutable.Map[Char,Int] = Map(e -> 1, f -> 1, a -> 1
                                                  //| , l -> 3, H -> 1, r -> 3, w -> 2, o -> 3, d -> 2)
	
	/*
	4. Repeat the preceding exercise with a sorted map, so that the words are printed in
	sorted order.
	*/
	var counters4 = scala.collection.immutable.SortedMap[Char, Int]()
                                                  //> counters4  : scala.collection.immutable.SortedMap[Char,Int] = Map()
	val fileName4 = "/home/diego/Development/code/scala4impatient/resources/chapter4.2.txt"
                                                  //> fileName4  : String = /home/diego/Development/code/scala4impatient/resource
                                                  //| s/chapter4.2.txt
	val in4 = new java.util.Scanner(new java.io.File(fileName3))
                                                  //> in4  : java.util.Scanner = java.util.Scanner[delimiters=\p{javaWhitespace}+
                                                  //| ][position=0][match valid=false][need input=false][source closed=false][ski
                                                  //| pped=false][group separator=\,][decimal separator=\.][positive prefix=][neg
                                                  //| ative prefix=\Q-\E][positive suffix=][negative suffix=][NaN string=\Q�\E]
                                                  //| [infinity string=\Q∞\E]
	
	while (in4.hasNext()) {
		for( c <- in4.next()) {
			val count = counters4.getOrElse(c, 0) + 1
			counters4 = counters4 + (c -> count)
		}
	}
	
	counters4                                 //> res2: scala.collection.immutable.SortedMap[Char,Int] = Map(H -> 1, a -> 1, 
                                                  //| d -> 2, e -> 1, f -> 1, l -> 3, o -> 3, r -> 3, w -> 2)
	
	/*
	5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the
	Scala API.
	*/
	
	import scala.collection.JavaConversions.mapAsScalaMap
	
	var counters5 = new java.util.TreeMap[Char, Int]()
                                                  //> counters5  : java.util.TreeMap[Char,Int] = {}
	val fileName5 = "/home/diego/Development/code/scala4impatient/resources/chapter4.2.txt"
                                                  //> fileName5  : String = /home/diego/Development/code/scala4impatient/resource
                                                  //| s/chapter4.2.txt
	val in5 = new java.util.Scanner(new java.io.File(fileName3))
                                                  //> in5  : java.util.Scanner = java.util.Scanner[delimiters=\p{javaWhitespace}+
                                                  //| ][position=0][match valid=false][need input=false][source closed=false][ski
                                                  //| pped=false][group separator=\,][decimal separator=\.][positive prefix=][neg
                                                  //| ative prefix=\Q-\E][positive suffix=][negative suffix=][NaN string=\Q�\E]
                                                  //| [infinity string=\Q∞\E]
	
	while (in5.hasNext()) {
		for( c <- in5.next()) {
			val count = counters5.getOrElse(c, 0) + 1
			counters5(c) = counters5.getOrElse(c, 0) + 1
		}
	}
	
	counters5                                 //> res3: java.util.TreeMap[Char,Int] = {H=1, a=1, d=2, e=1, f=1, l=3, o=3, r=3
                                                  //| , w=2}
	
	/*
	6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY,
	and similarly for the other weekdays. Demonstrate that the elements are visited
	in insertion order.
	*/
	import scala.collection.mutable.LinkedHashMap
	import java.util.Calendar._
	val days = LinkedHashMap[String, Int](
		"Monday" 			-> 	MONDAY,
		"Tuesday" 		->  TUESDAY,
		"Wednesday" 	-> 	WEDNESDAY,
		"Thursday" 		-> 	THURSDAY,
		"Friday" 			-> 	FRIDAY)
                                                  //> days  : scala.collection.mutable.LinkedHashMap[String,Int] = Map(Monday -> 
                                                  //| 2, Tuesday -> 3, Wednesday -> 4, Thursday -> 5, Friday -> 6)
	
	
	/*
	7. Print a table of all Java properties, like this:
	java.runtime.name | Java(TM) SE Runtime Environment
	sun.boot.library.path | /home/apps/jdk1.6.0_21/jre/lib/i386
	java.vm.version | 17.0-b16
	java.vm.vendor | Sun Microsystems Inc.
	java.vendor.url | http://java.sun.com/
	path.separator | :
	java.vm.name | Java HotSpot(TM) Server VM
	You need to find the length of the longest key before you can print the table.
	*/
	
	import scala.collection.JavaConversions.propertiesAsScalaMap
	val props: scala.collection.Map[String, String] = System.getProperties()
                                                  //> props  : scala.collection.Map[String,String] = Map(java.runtime.name -> Ope
                                                  //| nJDK Runtime Environment, sun.boot.library.path -> /usr/lib/jvm/java-8-open
                                                  //| jdk-amd64/jre/lib/amd64, java.vm.version -> 25.66-b17, java.vm.vendor -> Or
                                                  //| acle Corporation, java.vendor.url -> http://java.oracle.com/, path.separato
                                                  //| r -> :, java.vm.name -> OpenJDK 64-Bit Server VM, file.encoding.pkg -> sun.
                                                  //| io, user.country -> US, sun.java.launcher -> SUN_STANDARD, sun.os.patch.lev
                                                  //| el -> unknown, java.vm.specification.name -> Java Virtual Machine Specifica
                                                  //| tion, user.dir -> /home/diego/Development/tools/STS, java.runtime.version -
                                                  //| > 1.8.0_66-internal-b17, java.awt.graphicsenv -> sun.awt.X11GraphicsEnviron
                                                  //| ment, java.endorsed.dirs -> /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/endor
                                                  //| sed, os.arch -> amd64, java.io.tmpdir -> /tmp, line.separator -> "
                                                  //| ", java.vm.specification.vendor -> Oracle Corporation, os.name -> Linux, su
                                                  //| n.jnu.encoding -> UTF-8, java.li
                                                  //| Output exceeds cutoff limit.
	
	val maxLength = props.keySet.map(_.length).max
                                                  //> maxLength  : Int = 29
	
	for((k, v) <- props) {
		val space = (k.length() - maxLength).abs.intValue()
		println(k + (" " * space) + "|" + v)
	}                                         //> java.runtime.name            |OpenJDK Runtime Environment
                                                  //| sun.boot.library.path        |/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/amd
                                                  //| 64
                                                  //| java.vm.version              |25.66-b17
                                                  //| java.vm.vendor               |Oracle Corporation
                                                  //| java.vendor.url              |http://java.oracle.com/
                                                  //| path.separator               |:
                                                  //| java.vm.name                 |OpenJDK 64-Bit Server VM
                                                  //| file.encoding.pkg            |sun.io
                                                  //| user.country                 |US
                                                  //| sun.java.launcher            |SUN_STANDARD
                                                  //| sun.os.patch.level           |unknown
                                                  //| java.vm.specification.name   |Java Virtual Machine Specification
                                                  //| user.dir                     |/home/diego/Development/tools/STS
                                                  //| java.runtime.version         |1.8.0_66-internal-b17
                                                  //| java.awt.graphicsenv         |sun.awt.X11GraphicsEnvironment
                                                  //| java.endorsed.dirs           |/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/end
                                                  //| orsed
                                                  //| os.arch                      |amd64
                                                  //| java.io.tmpdir        
                                                  //| Output exceeds cutoff limit.
	
	
	
	/*
	8. Write a function minmax(values: Array[Int]) that returns a pair containing the
	smallest and largest value in the array.
	*/
	
	def minmax(values: Array[Int]) : (Int, Int) = (values.min, values.max)
                                                  //> minmax: (values: Array[Int])(Int, Int)
	
	minmax(Array(1, 2, 3, 4, 5))              //> res4: (Int, Int) = (1,5)
	
	/*
	9. Write a function lteqgt(values: Array[Int], v: int) that returns a triple
	containing count of values less than v, equal to v, and greater than v.
	*/
	
	def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = (values.filter(_ < v).length, values.filter(_ == v).length, values.filter(_ > v).length)
                                                  //> lteqgt: (values: Array[Int], v: Int)(Int, Int, Int)
	lteqgt(Array(1, 2, 3, 4, 5), 3)           //> res5: (Int, Int, Int) = (2,1,2)
	/*
	10. What happens when you zip together two strings, such as "Hello".zip("World")?
	Come up with a plausible use case.
	*/
	
	"Hello".zip("World")                      //> res6: scala.collection.immutable.IndexedSeq[(Char, Char)] = Vector((H,W), (
                                                  //| e,o), (l,r), (l,l), (o,d))


}