package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTes {
	ComplexFunction cf1;
	ComplexFunction cf2;
	ComplexFunction cf3;
	ComplexFunction cf4;
	@BeforeEach
	void ComplexFunction() {
		String s1 = "8x^2-5x^3";
		String s2 = "3+4x^2-4x^5";
		String[] s3 = {"4x+3","x^2-8x^1", "-9x+3x^2"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		cf1 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf1.plus(new Polynom(s3[i]));
		}
		cf2 = new ComplexFunction("plus", p1,p2); 
		cf3 = new ComplexFunction("mul", new Polynom("-x"),cf1);
		cf4 = new ComplexFunction(Operation.Comp, new Polynom("x+x^2"),cf2);	
		}
	@Test
	void ComplexFunctionCheck() {
		String s1="plus(plus(+3.0x^0+4.0x^1,-8.0x^1+1.0x^2),-9.0x^1+3.0x^2)";
		String s2="plus(+8.0x^2-5.0x^3,+3.0x^0+4.0x^2-4.0x^5)";
		String s3="mul(-1.0x^1,plus(plus(+3.0x^0+4.0x^1,-8.0x^1+1.0x^2),-9.0x^1+3.0x^2))";
		String s4="comp(+1.0x^1+1.0x^2,plus(+8.0x^2-5.0x^3,+3.0x^0+4.0x^2-4.0x^5))";
		
		if (!s1.equalsIgnoreCase(cf1.toString())) fail ("Err: constructor");
		if (!s2.equalsIgnoreCase(cf2.toString())) fail ("Err: constructor");
		if (!s3.equalsIgnoreCase(cf3.toString())) fail ("Err: constructor");
		if (!s4.equalsIgnoreCase(cf4.toString())) fail ("Err: constructor");
	}
	
	@Test
	void f() {
		double actual1=cf1.f(-1);
		double actual2=cf2.f(0);
		double actual3=cf3.f(2);
		double actual4=cf4.f(1);

		double expected1=20;
		double expected2=3;
		double expected3=14;
		double expected4=42;
		
		assertEquals(actual1,expected1,"check f1");
		assertEquals(actual2,expected2,"check f2");
		assertEquals(actual3,expected3,"check f3");
		assertEquals(actual4,expected4,"check f4");
	}
	
	@Test
	void tostringAndInitFromString() {
		ComplexFunction cf5=new ComplexFunction(Operation.Divid,cf4,cf3);
		String expected="div(comp(+1.0x^1+1.0x^2,plus(+8.0x^2-5.0x^3,+3.0x^0+4.0x^2-4.0x^5)),mul(-1.0x^1,plus(plus(+3.0x^0+4.0x^1,-8.0x^1+1.0x^2),-9.0x^1+3.0x^2)))"; 
		String actual=cf5.toString();
		assertEquals(actual,expected,"check toString");
		function cf6=cf5.initFromString(cf5.toString());
		if (!cf5.equals(cf6)) fail("fail init");

	}
	
	@Test
	void copy() {
		function cf5=cf4.copy();
		if (!cf5.equals(cf4)) fail("fail init");
	}
	
	@Test
	void equals() {
		if (cf3.equals(cf4)) fail("fail equals");
		ComplexFunction cf5=new ComplexFunction(Operation.Comp, new Polynom("x+x^2"),cf2);
		if (!cf5.equals(cf4)) fail("fail equals");		
	}
	
	@Test
	void getters() {
		function cf5=cf4.left();
		function cf6=cf3.right();
		System.out.println(cf4.left());
		System.out.println(cf3.right());
		function expected5=new Polynom("+1.0x^1+1.0x^2");
		function expected6=cf5.initFromString("plus(plus(+3.0x^0+4.0x^1,-8.0x^1+1.0x^2),-9.0x^1+3.0x^2)");
		System.out.println(expected6);
		System.out.println(expected5);
		if (!cf5.equals(expected5)) fail("fail equals");
		if (!cf6.equals(expected6)) fail("fail equals");
		
	}
	

}