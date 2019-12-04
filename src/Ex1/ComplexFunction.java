package Ex1;

//import java.util.Stack;

public class ComplexFunction implements complex_function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public class Node {


		private function f;
		private Node right; 
		private Node left;
		private Operation op;
		
		//***constructors***
		public Node (function _f) {
			this. f=_f;
			this.left=null;
			this.right=null; 
			this.op=null;
		}
		
		public Node (Operation _op,Node _left, Node _right) {
			this. f=null;
			this.left=_left;
			this.right=_right; 
			this.op=_op;
		}
		
		//***getters and setters***

		public function getF() {
			return f;
		}

		public void setF(function f) {
			this.f = f;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Operation getOp() {
			return op;
		}

		public void setOp(Operation op) {
			this.op = op;
		}
	}
	
	private Node head;
	
	//***constructors***
	public ComplexFunction (function f1) { // build constructor
		setHead(new Node (Operation.None,new Node (f1),null));
	}
	
	public ComplexFunction (String op, function f1, function f2) { // build constructor
		Operation _op= makeOpFromString(op);
		setHead(new Node (_op,new Node (f1),new Node (f1)));
	}
	
	private Operation makeOpFromString (String s) {
		s.toLowerCase();
		switch (s) {
		case "plus":

			return Operation.Plus;
		case "mul":
			return Operation.Times;
		case "div":
			return Operation.Divid;
		case "max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "comp":
			return Operation.Comp;
		default:
			throw new RuntimeException("error");
		}
	}
	
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return fRecurs(this.head,x);
	}
	
	private double fRecurs (Node n, double x) {
		if (n.f != null) return n.getF().f(x);
		else {
			switch (n.getOp()) {
			case Plus:
				return fRecurs(n.getLeft(),x)+fRecurs(n.getRight(),x);
			case Times:
				return fRecurs(n.getLeft(),x)*fRecurs(n.getRight(),x);
			case Divid:
				return fRecurs(n.getLeft(),x)/fRecurs(n.getRight(),x);
			case Max:
				return Math.max(fRecurs(n.getLeft(),x), fRecurs(n.getRight(),x));
			case Min:
				return Math.min(fRecurs(n.getLeft(),x), fRecurs(n.getRight(),x));
			case Comp:
				return fRecurs(n.getLeft(),fRecurs(n.getRight(),x));
			case None:
				return fRecurs(n.getLeft(),x);
			case Error:
				return 0;
			default:
				throw new RuntimeException("error");
			}
		}
	}
	
	public String toString () {
		return tsR(this.head);
	}
	
	private String tsR (Node n) {
		if (n==null) return "";
		if (n.getF()!= null) return n.getF().toString();
		else  if (n.getRight()==null ) return  makeFromOp(n.getOp())+"("+tsR(n.getLeft())+")";
		else return  makeFromOp(n.getOp())+"("+tsR(n.left)+","+tsR(n.getRight())+")";
	}
	
	private String makeFromOp (Operation op) {
		switch (op) {
		case Plus:
			return "plus";
		case Times:
			return "mul";
		case Divid:
			return "div";
		case Max:
			return "max";
		case Min:
			return "min";
		case Comp:
			return "comp";
		case None:
			return "";
		case Error:
			return "no";
		default:
			throw new RuntimeException("error");
		}
		
	}
	
	@Override
	public function initFromString(String s) {
		ComplexFunction ans=new ComplexFunction (new Polynom ("0"));
		
		/*Stack <String> st= new Stack <String> ();
		int i=0; int j=0;
		if (s.charAt(j))
		while (j<s.length()) {
			if ()
		}*/
		
		
		/*int i=0; int j=0; 
		if (s.charAt(j)=='-' || s.charAt(j)=='+') {
			j++;
		}
		while (j<s.length()) {
			if (s.charAt(j)=='+' || s.charAt(j)=='-' || s.charAt(j)=='*' || s.charAt(j)=='/') {
				Monom m=new Monom (s.substring(i, j));
				addToList (m);
				i=j;
			}
			j++; 
		}
		if (i!=j) {
			Monom m=new Monom (s.substring(i, j));
			addToList (m);
		}*/	



		return (function) ans;
	}

	@Override
	public function copy() {

		Node Nans= copyR(this.getHead());
		ComplexFunction ans = new ComplexFunction (new Monom (""));
		ans.setHead(Nans);
		return null;
	}
	
	private Node copyR(Node n) {
		if (n.getOp()!=null) return new Node (n.getOp(),copyR(n.getLeft()),copyR(n.getRight()));
		return new Node (n.getF());
	}
	
	@Override
	public void plus(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Plus, temp, cf1.getHead());
		
	}

	@Override
	public void mul(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Times, temp, cf1.getHead());
	}

	@Override
	public void div(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Divid, temp, cf1.getHead());
	}

	@Override
	public void max(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Max, temp, cf1.getHead());
	}

	@Override
	public void min(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Min, temp, cf1.getHead());	}

	@Override
	public void comp(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Comp, temp, cf1.getHead());
	}
	
	public boolean equals(Object obj) {
		return false;
	}
	
	//***getters**

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Node getHead () { return this.head; }
	
	//***SETTERS**
	public void setHead (Node _head) { this.head=_head; }

}
