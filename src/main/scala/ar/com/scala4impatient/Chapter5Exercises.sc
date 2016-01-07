package ar.com.scala4impatient

import scala.beans.BeanProperty

object Chapter5Exercises {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
	/*
	1. Improve the Counter class in Section 1 so that it doesn't turn negative at
	Int.MaxValue.
	*/
	class Counter {
		private var value: Int = 0
		def increment() { if(value < Int.MaxValue) value += 1 }
		def current() = value
	}
	
	/*
	2. Write a class BankAccount with methods deposit and withdraw, and a read-only
	property balance.
	*/
	class BankAccount {
		private var balance: Double = 0
		
		def deposit(amount: Double) = balance += amount
		def withdraw(amount: Double) = balance -= amount
		def current = balance
	}
	
	val account = new BankAccount             //> account  : ar.com.scala4impatient.Chapter5Exercises.BankAccount = ar.com.sca
                                                  //| la4impatient.Chapter5Exercises$$anonfun$main$1$BankAccount$1@7f690630
	account.current                           //> res0: Double = 0.0
	account.deposit(10)
	account.current                           //> res1: Double = 10.0
	account.withdraw(5)
	account.current                           //> res2: Double = 5.0
	
	/*
	3. Write a class Time with read-only properties hours and minutes and a method
	before(other: Time): Boolean that checks whether this time comes before the
	other. A Time object should be constructed as new Time(hrs, min), where hrs is
	in military time (between 0 and 23).
	*/
	class Time3(val hours: Int, val minutes: Int) {
		def before(other: Time3) =
			if(this.hours < other.hours) true
			else if(this.hours == other.hours && this.minutes < other.minutes) true
			else false
	}
	
	val time1_3 = new Time3(10, 20)           //> time1_3  : ar.com.scala4impatient.Chapter5Exercises.Time3 = ar.com.scala4im
                                                  //| patient.Chapter5Exercises$$anonfun$main$1$Time3$1@edf4efb
	val time2_3 = new Time3(10, 25)           //> time2_3  : ar.com.scala4impatient.Chapter5Exercises.Time3 = ar.com.scala4im
                                                  //| patient.Chapter5Exercises$$anonfun$main$1$Time3$1@2f7a2457
	val time3_3 = new Time3(11, 20)           //> time3_3  : ar.com.scala4impatient.Chapter5Exercises.Time3 = ar.com.scala4im
                                                  //| patient.Chapter5Exercises$$anonfun$main$1$Time3$1@566776ad
	
	time1_3.before(time2_3)                   //> res3: Boolean = true
	time1_3.before(time3_3)                   //> res4: Boolean = true
	time3_3.before(time2_3)                   //> res5: Boolean = false
	
	/*
	4. Reimplement the Time class from the preceding exercise so that the internal
	representation is the number of minutes since midnight (between 0 and 24×60-1).
	Do not change the public interface. That is, client code should be unaffected by
	your change.
	*/
	class Time4(val hours: Int, val minutes: Int) {
		private val time = hours * 60 + minutes
	
		def before(other: Time4) = this.time < other.time
	}
	
	val time1_4 = new Time4(10, 20)           //> time1_4  : ar.com.scala4impatient.Chapter5Exercises.Time4 = ar.com.scala4im
                                                  //| patient.Chapter5Exercises$$anonfun$main$1$Time4$1@6108b2d7
	val time2_4 = new Time4(10, 25)           //> time2_4  : ar.com.scala4impatient.Chapter5Exercises.Time4 = ar.com.scala4im
                                                  //| patient.Chapter5Exercises$$anonfun$main$1$Time4$1@1554909b
	val time3_4 = new Time4(11, 20)           //> time3_4  : ar.com.scala4impatient.Chapter5Exercises.Time4 = ar.com.scala4im
                                                  //| patient.Chapter5Exercises$$anonfun$main$1$Time4$1@6bf256fa
	
	time1_4.before(time2_4)                   //> res6: Boolean = true
	time1_4.before(time3_4)                   //> res7: Boolean = true
	time3_4.before(time2_4)                   //> res8: Boolean = false
	
	/*
	5. Make a class Student with read-write JavaBeans properties name (of type String)
	and id (of type Long). What methods are generated? (Use javap to check.) Can
	you call the JavaBeans getters and setters in Scala? Should you?
	*/
	class Student(@BeanProperty var name: String, @BeanProperty var id: Long) {
	}
	
	val student = new Student("Diego", 1)     //> student  : ar.com.scala4impatient.Chapter5Exercises.Student = ar.com.scala4
                                                  //| impatient.Chapter5Exercises$$anonfun$main$1$Student$1@6cd8737
	student.getName()                         //> res9: String = Diego
	student.name                              //> res10: String = Diego
	
	/*
	6. In the Person class of Section 1, provide a primary constructor that turns negative
	ages to 0.
	*/
	
	class Person6(_age: Int) {
		val age = if(_age > 0) _age else 0
	}
	
	new Person6(10).age                       //> res11: Int = 10
	new Person6(-10).age                      //> res12: Int = 0
	
	/*
	7. Write a class Person with a primary constructor that accepts a string containing a
	first name, a space, and a last name, such as new Person("Fred Smith"). Supply
	read-only properties firstName and lastName. Should the primary constructor
	parameter be a var, a val, or a plain parameter? Why?
	*/
	class Person7(fullName: String) {
		val firstName = fullName.split(" ").head
		val lastName = fullName.split(" ").last
		
	}
	
	new Person7("Diego Otero").firstName      //> res13: String = Diego
	
	/*
	8. Make a class Car with read-only properties for manufacturer, model name, and
	model year, and a read-write property for the license plate. Supply four
	constructors. All require the manufacturer and model name. Optionally, model
	year and license plate can also be specified in the constructor. If not, the model
	year is set to -1 and the license plate to the empty string. Which constructor are
	you choosing as the primary constructor? Why?
	*/
	class Car(val manufacturer: String, val modelName: String, val modelYear: Int) {
		
		var licensePlate: String = ""
		
		def this(manufacturer: String, modelName: String) = {
			this(manufacturer, modelName, -1)
		}
		
		def this(manufacturer: String, modelName: String, licensePlate: String) = {
			this(manufacturer, modelName)
			this.licensePlate = licensePlate
		}
		
		def this(manufacturer: String, modelName: String, modelYear: Int, licensePlate: String) = {
			this(manufacturer, modelName, modelYear)
			this.licensePlate = licensePlate
		}
		
	}
	
	val car1 = new Car("peugeot", "206")      //> car1  : ar.com.scala4impatient.Chapter5Exercises.Car = ar.com.scala4impatie
                                                  //| nt.Chapter5Exercises$$anonfun$main$1$Car$1@6b2fad11
	car1.manufacturer                         //> res14: String = peugeot
	car1.modelName                            //> res15: String = 206
	car1.modelYear                            //> res16: Int = -1
	car1.licensePlate                         //> res17: String = ""
	
	val car2 = new Car("peugeot", "206", 2005)//> car2  : ar.com.scala4impatient.Chapter5Exercises.Car = ar.com.scala4impatie
                                                  //| nt.Chapter5Exercises$$anonfun$main$1$Car$1@79698539
	car2.manufacturer                         //> res18: String = peugeot
	car2.modelName                            //> res19: String = 206
	car2.modelYear                            //> res20: Int = 2005
	car2.licensePlate                         //> res21: String = ""
	
	val car3 = new Car("peugeot", "206", "EVE270")
                                                  //> car3  : ar.com.scala4impatient.Chapter5Exercises.Car = ar.com.scala4impatie
                                                  //| nt.Chapter5Exercises$$anonfun$main$1$Car$1@73f792cf
	car3.manufacturer                         //> res22: String = peugeot
	car3.modelName                            //> res23: String = 206
	car3.modelYear                            //> res24: Int = -1
	car3.licensePlate                         //> res25: String = EVE270
	
	val car4 = new Car("peugeot", "206", 2005, "EVE270")
                                                  //> car4  : ar.com.scala4impatient.Chapter5Exercises.Car = ar.com.scala4impatie
                                                  //| nt.Chapter5Exercises$$anonfun$main$1$Car$1@2ed94a8b
	car4.manufacturer                         //> res26: String = peugeot
	car4.modelName                            //> res27: String = 206
	car4.modelYear                            //> res28: Int = 2005
	car4.licensePlate                         //> res29: String = EVE270
	
	val car5 = new Car("peugeot", "206", 2005, "EVE270")
                                                  //> car5  : ar.com.scala4impatient.Chapter5Exercises.Car = ar.com.scala4impatie
                                                  //| nt.Chapter5Exercises$$anonfun$main$1$Car$1@38082d64
	car5.licensePlate                         //> res30: String = EVE270
	car5.licensePlate = "ADC400"
	car5.licensePlate                         //> res31: String = ADC400
	
	/*
	9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your
	choice). How much shorter is the Scala class?
	*/
	
	/*
	10. Consider the class
		class Employee(val name: String, var salary: Double) {
			def this() { this("John Q. Public", 0.0) }
		}
	Rewrite it to use explicit fields and a default primary constructor. Which form do
	you prefer? Why?
	*/

}