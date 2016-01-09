package ar.com.scala4impatient

object Chapter8Exercises {
	/*
	1. Extend the following BankAccount class to a CheckingAccount class that charges
	$1 for every deposit and withdrawal.
		class BankAccount(initialBalance: Double) {
			private var balance = initialBalance
			def deposit(amount: Double) = { balance += amount; balance }
			def withdraw(amount: Double) = { balance -= amount; balance }
		}
	*/
	class BankAccount(initialBalance: Double) {
		private var balance = initialBalance
		def deposit(amount: Double) = { balance += amount; balance }
		def withdraw(amount: Double) = { balance -= amount; balance }
	}
	
	class CheckingAccount(initialBalance: Double, val charges: Double) extends BankAccount(initialBalance) {
		override def deposit(amount: Double) = super.deposit(amount - charges)
		override def withdraw(amount: Double) = super.withdraw(amount + charges)
	}
	
	val ca = new CheckingAccount(100D, 1D)    //> ca  : ar.com.scala4impatient.Chapter8Exercises.CheckingAccount = ar.com.scal
                                                  //| a4impatient.Chapter8Exercises$CheckingAccount@4cdf35a9
	ca.withdraw(1)                            //> res0: Double = 98.0
	ca.deposit(10)                            //> res1: Double = 107.0
	
	/*
	2. Extend the BankAccount class of the preceding exercise to a class SavingsAccount
	that earns interest every month (when a method earnMonthlyInterest is called)
	and that has three free deposits or withdrawals every month. Reset the
	transaction count in the earnMonthlyInterest method.
	*/
	class SavingsAccount(initialBalance: Double, val charges: Double, val interest: Double) extends BankAccount(initialBalance) {
		private val FREE_TRANSACTIONS = 3
		private var freeTransactionsUsed = 0
		
		override def deposit(amount: Double) =
			try {
				if(isFreeTransaction) super.deposit(amount)
				else super.deposit(amount - charges)
			} finally {
				useTransaction
			}
			
		override def withdraw(amount: Double) =
			try {
				if(isFreeTransaction) super.withdraw(amount)
				else super.withdraw(amount + charges)
			} finally {
				useTransaction
			}
			
		def earnMonthlyInterest = {
			freeTransactionsUsed = 0
			super.deposit(interest)
		}
		
		private def isFreeTransaction = freeTransactionsUsed < FREE_TRANSACTIONS
		private def useTransaction { freeTransactionsUsed += 1 }
	}
	
	val sa = new SavingsAccount(100D, 1D, 0.5D)
                                                  //> sa  : ar.com.scala4impatient.Chapter8Exercises.SavingsAccount = ar.com.scal
                                                  //| a4impatient.Chapter8Exercises$$anonfun$main$1$SavingsAccount$1@edf4efb
	sa.withdraw(1)                            //> res2: Double = 99.0
	sa.withdraw(1)                            //> res3: Double = 98.0
	sa.withdraw(1)                            //> res4: Double = 97.0
	sa.withdraw(1)                            //> res5: Double = 95.0
	sa.earnMonthlyInterest                    //> res6: Double = 95.5
	
	/*
	3. Consult your favorite Java or C++ textbook that is sure to have an example of a
	toy inheritance hierarchy, perhaps involving employees, pets, graphical shapes,
	or the like. Implement the example in Scala.
	*/
	
	/*
	4. Define an abstract class Item with methods price and description. A SimpleItem
	is an item whose price and description are specified in the constructor. Take
	advantage of the fact that a val can override a def. A Bundle is an item that
	contains other items. Its price is the sum of the prices in the bundle. Also provide
	a mechanism for adding items to the bundle and a suitable description method.
	*/
	abstract class Item {
		def price: Double
		def description: String
	}
	
	class SimpleItem(val price: Double, val description: String) extends Item
	
	class Bundle(val description: String) extends Item {
		private var items = scala.collection.mutable.ArrayBuffer[Item]()
		
		def price: Double = items.map(_.price).sum
		def add(item: Item) { items += item }
	}
	
	val it1 = new SimpleItem(10D, "Item 1")   //> it1  : ar.com.scala4impatient.Chapter8Exercises.SimpleItem = ar.com.scala4i
                                                  //| mpatient.Chapter8Exercises$$anonfun$main$1$SimpleItem$1@2f7a2457
	val it2 = new SimpleItem(20D, "Item 2")   //> it2  : ar.com.scala4impatient.Chapter8Exercises.SimpleItem = ar.com.scala4i
                                                  //| mpatient.Chapter8Exercises$$anonfun$main$1$SimpleItem$1@566776ad
	val bundle = new Bundle("Bundle 1")       //> bundle  : ar.com.scala4impatient.Chapter8Exercises.Bundle = ar.com.scala4im
                                                  //| patient.Chapter8Exercises$$anonfun$main$1$Bundle$1@6b2fad11
           
	it1.price                                 //> res7: Double = 10.0
	it2.price                                 //> res8: Double = 20.0
  bundle.add(it1)
	bundle.price                              //> res9: Double = 10.0
	bundle.add(it2)
	bundle.price                              //> res10: Double = 30.0
	
	/*
	5. Design a class Point whose x- and y-coordinate values can be provided in a
	constructor. Provide a subclass LabeledPoint whose constructor takes a label
	value and x- and y-coordinates, such as
	new LabeledPoint("Black Thursday", 1929, 230.07)
	*/
	class Point(val x: Double, val y: Double)
	
	class LabeledPoint(val label: String, x: Double, y: Double) extends Point(x, y)
	
	/*
	6. Define an abstract class Shape with an abstract method centerPoint and
	subclasses Rectangle and Circle. Provide appropriate constructors for the
	subclasses and override the centerPoint method in each subclass.
	*/
	abstract class Shape {
		def centerPoint: Point
	}
	
	class Rectangle(base: Double, side: Double) extends Shape {
		def centerPoint = new Point(base / 2, side / 2)
	}
	
	class Cicle(radius: Double) extends Shape {
		def centerPoint = new Point(0, 0)
	}
	
	/*
	7. Provide a class Square that extends java.awt.Rectangle and has three
	constructors, one that constructs a square with a given corner point and width,
	one that constructs a square with corner (0, 0) and a given width, and one that
	constructs a square with corner (0, 0) and width 0.
	*/
	
	/*
	8. Compile the Person and SecretAgent classes in Section 6 and analyze the class
	files with javap. How many name fields are there? How many name getter
	methods are there? What do they get? (Hint: Use the -c and -private options.)
	*/
	
	/*
	9. In the Creature class of Section 10, replace val range with a def. What happens
	when you also use a def in the Ant subclass? What happens when you use a val
	in the subclass? Why?
	*/
	class Creature {
		def range: Int = 10
		val env: Array[Int] = new Array[Int](range)
	}
	
	class Ant extends Creature {
		override val range = 2
	}
	
	val ant = new Ant()                       //> ant  : ar.com.scala4impatient.Chapter8Exercises.Ant = ar.com.scala4impatien
                                                  //| t.Chapter8Exercises$$anonfun$main$1$Ant$1@3dd3bcd
	ant.range                                 //> res11: Int = 2
	
	/*
	10. The file scala/collection/immutable/Stack.scala contains the definition
		class Stack[A] protected (protected val elems: List[A])
	Explain the meanings of the protected keywords. (Hint: Look at the discussion
	of private constructors in the “Classes” chapter.)
	*/

}