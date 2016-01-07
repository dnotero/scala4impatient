package ar.com.scala4impatient

object Chapter2Exercises {

	/*
	1. The signum of a number is 1 if the number is positive,
	-1 if it is negative and 0 if it is zero.
	Write a function that computes this value.
	*/
	def signum(x: Int) =
  	if(x == 0) 0
  	else if(x > 0) 1
  	else -1                                   //> signum: (x: Int)Int
  	
  signum(0)                                       //> res0: Int = 0
  signum(-10)                                     //> res1: Int = -1
  signum(5)                                       //> res2: Int = 1
  

	/*
	2. What is the value of an empty block expression {}? What is its type?
	*/
	val ex2 = {}                              //> ex2  : Unit = ()
  
  
	/*
	3. Come up with one situation where the assignment x = y = 1 is valid in Scala.
	(Hint: Pick a suitable type for x.)
	*/
  

	/*
	4. Write a Scala equivalent for the Java loop
	for (int i = 10; i >= 0; i--) System.out.println(i);
	*/
	for(i <- 10 to 0 by -1) print(i)          //> 109876543210

	/*
	5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.
	*/
	def countdown(n: Int) {
  	for(i <- n to 0 by -1) print(i)
  }                                               //> countdown: (n: Int)Unit
  
  countdown(5)                                    //> 543210

	/*
	6. Write a for loop for computing the product of the Unicode codes of all letters in a string.
	For example, the product of the characters in "Hello" is 825152896.
	*/
	def product(s: String) = {
  	var res = 1
  	for(c <- s) res *= c.toInt
  	res
  }                                               //> product: (s: String)Int
  
  product("Hello")                                //> res3: Int = 825152896

	/*
	7. Solve the preceding exercise without writing a loop.
	(Hint: Look at the StringOps Scaladoc.)
	*/
	def product2(s: String) = s.map { _.toInt }.product
                                                  //> product2: (s: String)Int
	product2("Hello")                         //> res4: Int = 825152896
	
	/*
	8. Write a function product(s : String) that computes the product,
	as described in the preceding exercises.
	*/

	
	/*
	9. Make the function of the preceding exercise a recursive function.
	*/
	def product3(s: String) = {
		def product3Aux(s: String, n: Int): Int =
			if(n == s.length()) 1
			else s(n).toInt * product3Aux(s, n+1)
			
		product3Aux(s, 0)
	}                                         //> product3: (s: String)Int
	
	product3("Hello")                         //> res5: Int = 825152896

	/*
	10. Write a function that computes x^n, where n is an integer. Use the following recursive definition:
		x^n = y^2							if n is even and positive, where y = x^(n / 2)
		x^n = x*x^(n - 1) 		if n is odd and positive
		x^0 = 1
		x^n = 1 / x^(-n) 			if n is negative
		
		Don't use a return statement.
	*/
	def pow(x: Double, n: Int): Double = {
		if(n == 0) 1
		else if(n < 0) 1 / pow(x, -n)
		else if(n % 2 != 0) x * pow(x, n - 1)
		else { val y = pow(x, n / 2); y * y }
	}                                         //> pow: (x: Double, n: Int)Double
  
  pow(2, 3)                                       //> res6: Double = 8.0
  pow(10, 14)                                     //> res7: Double = 1.0E14
}