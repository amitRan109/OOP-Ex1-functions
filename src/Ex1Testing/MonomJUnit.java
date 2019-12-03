package Ex1Testing;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;

class MonomJUnit {
	Monom m;
	@Test
	void testF() 
	{
		Monom [] check= new Monom [4];
		check[0]=new Monom ("2");
		check[1]=new Monom ("-x");
		check[2]=new Monom ("-3.2x^2");
		check[3]=new Monom ("0");

		double [] actual=new double [4];

		for (int i=0;i<4;i++) {
			actual[i]=check[i].f(3);
		}

		double [] expected= {2, -3 , -28.8 , 0}; 

		assertArrayEquals (expected, actual);
	}

	@Test
	void testDerivate () 
	{
		Monom [] check= new Monom [4];
		check[0]=new Monom ("2");
		check[1]=new Monom ("-x");
		check[2]=new Monom ("-3.2x^2");
		check[3]=new Monom ("0");

		Monom [] expected= new Monom [4];
		expected[0]=new Monom ("0");
		expected[1]=new Monom ("-1");
		expected[2]=new Monom ("-6.4x^1");
		expected[3]=new Monom ("0");

		Monom [] actual= new Monom [4];

		for (int i=0;i<4;i++) {
			actual[i]=check[i].derivative();
			//System.out.println(actual[i]);
		}
		assertEquals(expected.length, actual.length);
		for(int i=0;i<actual.length;i++) {
			Monom e = expected[i];
			Monom a = actual[i];
			if(a.equals(e)==false) {
				fail("ERR: the monoms should have been equals: a="+a+"  e="+e);
			}
		}
	}

	@Test
	void testAdd() {

		Monom [] check= new Monom [4];
		check[0]=new Monom ("2x^2");
		check[1]=new Monom ("-x^2");
		check[2]=new Monom ("x^5");
		check[3]=new Monom ("0");

		Monom [] expected= new Monom [4];
		expected[0]=new Monom ("4x^2");
		expected[1]=new Monom ("x^2");
		expected[2]=new Monom ("x^5");
		expected[3]=new Monom ("2x^2");

		//Monom [] actual= new Monom [4];
		Monom MonomAdd=new Monom("2x^2");
		for (int i=0;i<4;i++) {
			check[i].add(MonomAdd);

			if (!check[i].equals(expected[i]))
				fail ("ERR ");}

	}
	@Test
	void testSub(){
		Monom [] check= new Monom [4];
		check[0]=new Monom ("2x^2");
		check[1]=new Monom ("-x^2");
		check[2]=new Monom ("3");
		check[3]=new Monom ("0");

		Monom [] expected= new Monom [4];
		expected[0]=new Monom ("0");
		expected[1]=new Monom ("-3x^2");
		expected[2]=new Monom ("3");
		expected[3]=new Monom ("-2x^2");

		//Monom [] actual= new Monom [4];
		Monom MonomSub=new Monom("2x^2");
		for (int i=0;i<4;i++) {
			check[i].sub(MonomSub);

			if (!check[i].equals(expected[i]))
				fail ("ERR ");}


	}
	@Test
	void testMultiply() {
		Monom [] check= new Monom [4];
		check[0]=new Monom ("3x^2");
		check[1]=new Monom ("0");
		check[2]=new Monom ("4");
		check[3]=new Monom ("x^3");

		Monom [] expected= new Monom [4];
		expected[0]=new Monom ("6x^4");
		expected[1]=new Monom ("0");
		expected[2]=new Monom ("8x^2");
		expected[3]=new Monom ("2x^5");

		Monom MonomMul=new Monom("2x^2");
		for (int i=0;i<4;i++) {
			check[i].multipy(MonomMul);
			if (!check[i].equals(expected[i]))
				fail ("ERR ");
			}

	}


	@Test
	void testString() {
		String [] check= new String [4];
		check[0]= ("3x^2");
		check[1]= ("0");
		check[2]= ("4");
		check[3]= ("-3x");

		Monom [] expected= new Monom [4];
		expected[0]=new Monom ("3x^2");
		expected[1]=new Monom ("0");
		expected[2]=new Monom ("4");
		expected[3]=new Monom ("-3x");
		
		for(int i=0; i<4;i++) {
			Monom actual=new Monom(check[i]);
			if(!actual.equals(expected[i])) {
				fail ("ERR ");
			}
		}
		}
		
		@Test
		 void testEqual1() {
		
			
			Monom [] check= new Monom [4];
			check[0]=new Monom ("3x^2");
			check[1]=new Monom ("0");
			check[2]=new Monom ("4");
			check[3]=new Monom ("x^3");

			Monom [] actual= new Monom [4];
			actual[0]=new Monom ("3x^2");
			actual[1]=new Monom ("0");
			actual[2]=new Monom ("4");
			actual[3]=new Monom ("x^3");
			for(int i=0;i<4;i++) {
				boolean expected1=true;
				boolean check1= check[i].equals(actual[i]);
				assertEquals(expected1,check1);
			}
		}
		@Test
		void testZero() {
			Monom check1=new Monom("0");
			Monom check2=new Monom("0x^2");
			boolean actual1=check1.isZero();
			boolean actual2=check2.isZero();
			assertTrue(actual1);
			assertTrue(actual2);

}
	@Test
	void testCopy() {
		Monom [] check= new Monom [4];
		check[0]=new Monom ("3x^2");
		check[1]=new Monom ("0");
		check[2]=new Monom ("4");
		check[3]=new Monom ("x^3");

		for(int i=0;i<4;i++) {
			Monom expected= (Monom)(check[i].copy());
			if(!check[i].equals(expected)) {
				fail("ERR");
			}
			
		}
		
	}
		
		
		
		
		
	}





