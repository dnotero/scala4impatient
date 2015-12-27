package ar.com.scala4impatient

object Chapter3Exercises {

/*
1. Write a code snippet that sets a to an array of n random integers between 0
(inclusive) and n (exclusive).
*/

def rand(n: Int) = for(i <- 0 until n) yield util.Random.nextInt(n)
                                                  //> rand: (n: Int)scala.collection.immutable.IndexedSeq[Int]

val n1 = 10                                       //> n1  : Int = 10
rand(n1)                                          //> res0: scala.collection.immutable.IndexedSeq[Int] = Vector(7, 8, 1, 6, 6, 0, 
                                                  //| 4, 6, 3, 5)

/*
2. Write a loop that swaps adjacent elements of an array of integers. For example,
Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
*/
def swap2(src: Array[Int]) = {
	for(i <- 0 until (src.length - 1, 2)) {
		val aux = src(i)
		src(i) = src(i+1)
		src(i+1) = aux
	}
	
	src
}                                                 //> swap2: (src: Array[Int])Array[Int]

val src2 = Array(1, 2, 3, 4, 5)                   //> src2  : Array[Int] = Array(1, 2, 3, 4, 5)
swap2(src2)                                       //> res1: Array[Int] = Array(2, 1, 4, 3, 5)

/*
3. Repeat the preceding assignment, but produce a new array with the swapped
values. Use for/yield.
*/
def swap3(src: Array[Int]) = {
	for(i <- 0 until src.length) yield
		if(i == src.length - 1) src(i)
		else if(i % 2 == 0) src(i + 1)
		else src(i - 1)
}                                                 //> swap3: (src: Array[Int])scala.collection.immutable.IndexedSeq[Int]

val src3 = Array(1, 2, 3, 4, 5)                   //> src3  : Array[Int] = Array(1, 2, 3, 4, 5)
swap3(src3)                                       //> res2: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 1, 4, 3, 5)


/*
4. Given an array of integers, produce a new array that contains all positive values
of the original array, in their original order, followed by all values that are zero or
negative, in their original order
*/

val n4 = 10                                       //> n4  : Int = 10
val src4 = Array(3, -4, -3, 3, 0, 99, 0, -4)      //> src4  : Array[Int] = Array(3, -4, -3, 3, 0, 99, 0, -4)
val dst = src4.filter { _ > 0} ++ src4.filter { _ == 0} ++ src4.filter { _ < 0}
                                                  //> dst  : Array[Int] = Array(3, 3, 99, 0, 0, -4, -3, -4)
/*
5. How do you compute the average of an Array[Double]?
*/

val src5 = Array(1, 2, 3, 4, 5)                   //> src5  : Array[Int] = Array(1, 2, 3, 4, 5)
val avg = src5.sum / src5.length                  //> avg  : Int = 3

/*
6. How do you rearrange the elements of an Array[Int] so that they appear in
reverse sorted order? How do you do the same with an ArrayBuffer[Int]?
*/

val src6 = rand(10)                               //> src6  : scala.collection.immutable.IndexedSeq[Int] = Vector(9, 1, 4, 1, 9, 
                                                  //| 8, 2, 6, 9, 2)
val dst6 = src6.sorted.reverse                    //> dst6  : scala.collection.immutable.IndexedSeq[Int] = Vector(9, 9, 9, 8, 6, 
                                                  //| 4, 2, 2, 1, 1)


/*
7. Write a code snippet that produces all values from an array with duplicates
removed. (Hint: Look at ScalaDoc)
*/

val src7 = rand(10)                               //> src7  : scala.collection.immutable.IndexedSeq[Int] = Vector(3, 9, 6, 7, 8, 
                                                  //| 5, 9, 4, 1, 9)
val dst7 = src7.distinct                          //> dst7  : scala.collection.immutable.IndexedSeq[Int] = Vector(3, 9, 6, 7, 8, 
                                                  //| 5, 4, 1)
/*
8. Rewrite the example at the end of Section 4 using the drop method for dropping
the index of the first match. Look the method up in ScalaDoc.
*/
val src8 = Array(1, 4, 8, 3, -1, 2, 5, -7, 9, 0).toBuffer
                                                  //> src8  : scala.collection.mutable.Buffer[Int] = ArrayBuffer(1, 4, 8, 3, -1, 
                                                  //| 2, 5, -7, 9, 0)
(for(i <- 0 until src8.length if src8(i) < 0) yield i).drop(1).reverse.foreach(src8.remove(_))

src8                                              //> res3: scala.collection.mutable.Buffer[Int] = ArrayBuffer(1, 4, 8, 3, -1, 2,
                                                  //|  5, 9, 0)
/*
9. Make a collection of all time zones returned by
java.util.TimeZone.getAvailableIds that are in America. Strip off the
"America/" prefix and sort the result.
*/

val tz = (for( tz <- java.util.TimeZone.getAvailableIDs() if tz.startsWith("America/")) yield tz.stripPrefix("America/")).sorted
                                                  //> tz  : Array[String] = Array(Adak, Anchorage, Anguilla, Antigua, Araguaina, 
                                                  //| Argentina/Buenos_Aires, Argentina/Catamarca, Argentina/ComodRivadavia, Arge
                                                  //| ntina/Cordoba, Argentina/Jujuy, Argentina/La_Rioja, Argentina/Mendoza, Arge
                                                  //| ntina/Rio_Gallegos, Argentina/Salta, Argentina/San_Juan, Argentina/San_Luis
                                                  //| , Argentina/Tucuman, Argentina/Ushuaia, Aruba, Asuncion, Atikokan, Atka, Ba
                                                  //| hia, Bahia_Banderas, Barbados, Belem, Belize, Blanc-Sablon, Boa_Vista, Bogo
                                                  //| ta, Boise, Buenos_Aires, Cambridge_Bay, Campo_Grande, Cancun, Caracas, Cata
                                                  //| marca, Cayenne, Cayman, Chicago, Chihuahua, Coral_Harbour, Cordoba, Costa_R
                                                  //| ica, Creston, Cuiaba, Curacao, Danmarkshavn, Dawson, Dawson_Creek, Denver, 
                                                  //| Detroit, Dominica, Edmonton, Eirunepe, El_Salvador, Ensenada, Fort_Wayne, F
                                                  //| ortaleza, Glace_Bay, Godthab, Goose_Bay, Grand_Turk, Grenada, Guadeloupe, G
                                                  //| uatemala, Guayaquil, Guyana, Halifax, Havana, Hermosillo, Indiana/Indianapo
                                                  //| lis, Indiana/Knox, Indi
                                                  //| Output exceeds cutoff limit.

/*
10. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap
with the call
val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
Then call the getNativesForFlavor method with parameter
DataFlavor.imageFlavor, and get the return value as a Scala buffer. (Why this
obscure class? It's hard to find uses of java.util.List in the standard Java
library.)
*/

}