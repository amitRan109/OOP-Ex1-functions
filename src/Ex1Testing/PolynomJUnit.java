package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
class PolynomJUnit {

	@Test
	void testF() {
		Polynom [] check= new Polynom [4];
		check[0]=new Polynom ("2");
		check[1]=new Polynom ("-x");
		check[2]=new Polynom ("-3.2x^2");
		check[3]=new Polynom ("0");
		
		double [] actual=new double [4];
		
		for (int i=0;i<4;i++) {
			actual[i]=check[i].f(3);
		}
		
		double [] expected= {2, -3 , -28.8 , 0}; 
		
		assertArrayEquals (expected, actual);
	}
	
	@Test
	void testAdd() {
		Polynom [] check= new Polynom [4];
		check[0]=new Polynom ("2+3x^2-9x^3");
		check[1]=new Polynom ("-x+4x^2");
		check[2]=new Polynom ("-3.2x^2+5.5x");
		check[3]=new Polynom ("0");
		
		//add monom
		Polynom [] expected= new Polynom [4];
		expected[0]=new Polynom ("2+5x^2-9x^3");
		expected[1]=new Polynom ("-x+6x^2");
		expected[2]=new Polynom ("-1.2x^2+5.5x");
		expected[3]=new Polynom ("2x^2");
		
		Monom add_m= new Monom ("2x^2");
		
		for (int i=0;i<4;i++) {
			check[i].add(add_m);
			if (!check[i].equals(expected[i]))
				fail ("ERR ");
		}
		
		//add polynom
		Polynom [] expected2= new Polynom [4];
		expected2[0]=new Polynom ("4+6x^2-18x^3");
		expected2[1]=new Polynom ("2-x+7x^2-9x^3");
		expected2[2]=new Polynom ("2+2.8x^2+5.5x-9x^3");
		expected2[3]=new Polynom ("2+5x^2-9x^3");
		
		Polynom add_p= new Polynom ("2+5x^2-9x^3");
		
		for (int i=0;i<4;i++) {
			check[i].add(add_p);
			if (!check[i].equals(expected2[i]))
				fail ("ERR ");
		}
		
	}
	
	@Test
	void testSub() {
		Polynom [] check= new Polynom [4];
		check[0]=new Polynom ("2+3x^2-9x^3");
		check[1]=new Polynom ("-x+4x^2");
		check[2]=new Polynom ("-3.2x^2+5.5x");
		check[3]=new Polynom ("0");
		
		Polynom [] expected= new Polynom [4];
		expected[0]=new Polynom ("2-x^2+x-9x^3");
		expected[1]=new Polynom ("0");
		expected[2]=new Polynom ("-2.8x^2+6.5x");
		expected[3]=new Polynom ("x-4x^2");
		
		for (int i=0;i<4;i++) {
			Polynom sub_p= new Polynom ("-x+4x^2");
			check[i].substract(sub_p);
			if (!check[i].equals(expected[i]))
				fail ("ERR ");
		}
		
	}
	
	@Test
	void testMul() {
		Polynom [] check= new Polynom [4];
		check[0]=new Polynom ("2+3x^2-9x^3");
		check[1]=new Polynom ("-x+4x^2");
		check[2]=new Polynom ("-3.2x^2+5.5x");
		check[3]=new Polynom ("0");
		
		//mul monom
		Polynom [] expected= new Polynom [4];
		expected[0]=new Polynom ("-2x^2-3x^4+9x^5");
		expected[1]=new Polynom ("x^3-4x^4");
		expected[2]=new Polynom ("3.2x^4-5.5x^3");
		expected[3]=new Polynom ("0");
		
		Monom mul_m= new Monom ("-x^2");
		
		for (int i=0;i<4;i++) {
			check[i].multiply(mul_m);
			//System.out.println(i+": "+check[i]);
			if (!check[i].equals(expected[i]))
				fail ("ERR ");
		}
		
		//mul polynom
		Polynom [] expected2= new Polynom [4];
		expected2[0]=new Polynom ("-4.0x^2-4.0x^4+18.0x^5+3.0x^6-9.0x^7");
		expected2[1]=new Polynom ("4x^6-x^5-8x^4+2x^3");
		expected2[2]=new Polynom ("-3.2x^6+5.5x^5+6.4x^4-11x^3");
		expected2[3]=new Polynom ("0");
		
		for (int i=0;i<4;i++) {
			//System.out.println("check "+i+": "+check[i]);
			Polynom mul_p= new Polynom ("2-x^2");
			check[i].multiply(mul_p);
			//System.out.println(i+": "+check[i]+" mul_p: "+mul_p);
			if (!check[i].equals(expected2[i]))
				fail ("ERR ");
		}
		
	}
	@Test
	void testEquals() {
		Polynom a=new Polynom ("0");
		Polynom b=new Polynom ("0x^5");
		boolean expected=true;
		boolean actual=a.equals(b);
		assertEquals(expected,actual);
		
		Polynom c=new Polynom ("-5+3x^4");
		Polynom d=new Polynom ("3x^4-5");
		boolean expected2=true;
		boolean actual2=c.equals(d);
		assertEquals(expected2,actual2);
		
		Polynom e=new Polynom ("5x^2");
		Polynom f=new Polynom ("5x^3");
		boolean expected3=false;
		boolean actual3=e.equals(f);
		assertEquals(expected3,actual3);
		
		Polynom g=new Polynom ("5x^0");
		Polynom h=new Polynom ("5");
		boolean expected4=true;
		boolean actual4=g.equals(h);
		assertEquals(expected4,actual4);
		
	}
	@Test
	void testIsZero() {
		Polynom a=new Polynom ("5x^0");
		boolean expected=false;
		boolean actual=a.isZero();
		assertEquals(expected,actual);
		
		Polynom b=new Polynom ("5x^2");
		boolean expected1=false;
		boolean actual1=b.isZero();
		assertEquals(expected1,actual1);
		
		Polynom c=new Polynom ("0x^0");
		boolean expected2=true;
		boolean actual2=c.isZero();
		assertEquals(expected2,actual2);
		
		Polynom d=new Polynom ("0x^60");
		boolean expected3=true;
		boolean actual3=d.isZero();
		assertEquals(expected3,actual3);
		
		Polynom e=new Polynom ("89");
		boolean expected4=false;
		boolean actual4=e.isZero();
		assertEquals(expected4,actual4);

	}
	@Test
	void testRoot() {
		Polynom [] check= new Polynom [4];
		check[0]=new Polynom ("2+3x^2-9x^3");
		check[1]=new Polynom ("-x+4x^2");
		check[2]=new Polynom ("-3.2x^2+5.5x+1");
		double eps=0.0001;
		
		double [] expected= {0.739593505859375,0.25,1.88458251953125};
		for (int i=0;i<3;i++) {
			//System.out.println(i+": "+check[i].root(0, 2, eps));
			assertEquals(expected[i],check[i].root(0, 2, eps));
		}
		
				

	}
	
	@Test
	void testCopy() {
		Polynom a= new Polynom ("2+3x^2-9x^3");
		Polynom b=(Polynom)a.copy();
		if (!a.equals(b)) fail ("ERR ");
		

	}
	
	@Test
	void testArea() {
		Polynom [] check= new Polynom [4];
		check[0]=new Polynom ("2+3x^2+9x^3");
		check[1]=new Polynom ("-x+4x^2");
		check[2]=new Polynom ("-3.2x^2+5.5x");
		check[3]=new Polynom ("0");
		
		double eps=0.0001;
		double [] expected= {48.004400099987755,8.688200009998072,2.949276203999954,0};
		for (int i=0;i<4;i++) {
			System.out.println(i+": "+check[i].area(0,2,eps));
			assertEquals(check[i].area(0,2,eps),expected[i]);
			System.out.println("amit");
		}
	}
	
	@Test
	void testDerivative() {
		Polynom [] check= new Polynom [4];
		check[0]=new Polynom ("2+3x^2-9x^3");
		check[1]=new Polynom ("-x+4x^2");
		check[2]=new Polynom ("-3.2x^2+5.5x");
		check[3]=new Polynom ("0");
		
		Polynom [] expected= new Polynom [4];
		expected[0]=new Polynom ("6x-27x^2");
		expected[1]=new Polynom ("-1+8x");
		expected[2]=new Polynom ("-6.4x+5.5");
		expected[3]=new Polynom ("0");
		
		for (int i=0;i<4;i++) {
			//System.out.println(i+": "+check[i].derivative());
			if (!check[i].derivative().equals(expected[i]))
				fail ("ERR ");
		}
	}
}
