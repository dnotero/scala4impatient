package ar.com.scala4impatient

object Chapter6Exercises {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  /*
	  1. Write an object Conversions with methods inchesToCentimeter, gallonsToLiter,
	and milesToKilometer.
  */
	object Conversions {
		def inchesToCentimeter(inches: Double) = inches * 2.54d
		def gallonsToLiter(gallons: Double) = gallons * 3.78541d
		def milesToKilometer(miles: Double) = miles * 1.60934d
	}
	
	Conversions.inchesToCentimeter(100)       //> res0: Double = 254.0
	Conversions.gallonsToLiter(100)           //> res1: Double = 378.541
	Conversions.milesToKilometer(100)         //> res2: Double = 160.934

  /*
	2. The preceding problem wasn't very object-oriented. Provide a general superclass
	UnitConversion and define objects InchesToCentimeter, GallonsToLiter, and
	MilesToKilometer that extend it.
  */
  abstract class UnitConversion {
  	def transform(unit: Double): Double = unit * multiplier
  	def multiplier: Double
  }
  
	object InchesToCentimeter extends UnitConversion {
		override def multiplier: Double = 2.54d
	}
	
	object GallonsToLiter extends UnitConversion {
		override def multiplier: Double = 3.78541d
	}
	
	object MilesToKilometer extends UnitConversion {
		override def multiplier: Double = 1.60934d
	}
	
	InchesToCentimeter.transform(100)         //> res3: Double = 254.0
	GallonsToLiter.transform(100)             //> res4: Double = 378.541
	MilesToKilometer.transform(100)           //> res5: Double = 160.934
	
  /*
	3. Define an Origin object that extends java.awt.Point. Why is this not actually a
	good idea? (Have a close look at the methods of the Point class.)
  */
  object Origin extends java.awt.Point {}

  /*
	4. Define a Point class with a companion object so that you can construct Point
	instances as Point(3, 4), without using new.
  */
  class Point(val x: Int, val y: Int) {}

	object Point {
		def apply(x: Int, y: Int) = new Point(x,y)
	}

	val p: Point = Point(1,2)                 //> p  : ar.com.scala4impatient.Chapter6Exercises.Point = ar.com.scala4impatien
                                                  //| t.Chapter6Exercises$$anonfun$main$1$Point$2@2f7a2457
	p.x                                       //> res6: Int = 1
	p.y                                       //> res7: Int = 2

  /*
	5. Write a Scala application, using the App trait, that prints the command-line
	arguments in reverse order, separated by spaces. For example, scala Reverse
	Hello World should print World Hello.
  */
	object Reverse extends App {
		print(args.reverse)
	}
	
  /*
	6. Write an enumeration describing the four playing card suits so that the toString
	method returns ♣, ♦, ♥, ♠.
  */
  object Suit extends Enumeration {
  	type Suit = Value
  	
  	val Heart = Value("♥")
  	val Spade = Value("♠")
  	val Diamod = Value("♦")
  	val Clubs = Value("♣")
  }
  
  Suit.Clubs                                      //> res8: ar.com.scala4impatient.Chapter6Exercises.Suit.Value = ♣

  /*
	7. Implement a function that checks whether a card suit value from the preceding
	exercise is red.
  */
  import Suit._
  
  def isRed(suit: Suit) = suit == Heart || suit == Diamod
                                                  //> isRed: (suit: ar.com.scala4impatient.Chapter6Exercises.Suit.Suit)Boolean
	isRed(Heart)                              //> res9: Boolean = true
	isRed(Spade)                              //> res10: Boolean = false
	
  /*
	8. Write an enumeration describing the eight corners of the RGB color cube. As
	IDs, use the color values (for example, 0xff0000 for Red).
  */
  object RGB extends Enumeration {
  	type RGB = Value
  
  	val Black = Value(0x000000)
  	val White = Value(0xffffff)
  	val Red = Value(0xff0000)
  	val Green = Value(0x00ff00)
  	val Blue = Value(0x0000ff)
  	val Yellow = Value(0xffff00)
  	val Cian = Value(0x00ffff)
  	val Magenta = Value(0xff00ff)
  }
  
  RGB.Blue.id                                     //> res11: Int = 255
  
}