package Ex1;

public class complexSave {

	public class ComplexFunction implements complex_function {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		ComplexFunction left;
		ComplexFunction right;
		Operation op;
		
		public ComplexFunction () { // defult constructor
			this.setLeft(null);
			this.setRight(null);
			this.setOp(Operation.Error);
		}
		 public ComplexFunction (function f1) {
			 this.setLeft((ComplexFunction)f1);
			 this.setRight(null);
			 this.setOp(Operation.Error); 
		 }
		
		@Override
		public double f(double x) {
			// TODO Auto-generated method stub
			return FRecurs (this, x);
		}
		
		private double FRecurs (ComplexFunction fu, double x) {
			try {
				if (fu.op== Operation.Error) return fu.f(x);
				else { 
					switch (fu.op) {
					case Plus:
						return FRecurs (fu.left,x)+FRecurs(fu.right,x);
					case Times:
						return FRecurs (fu.left,x)*FRecurs(fu.right,x);
					case Divid:
						return FRecurs (fu.left,x)/FRecurs(fu.right,x);
					case Max:
						return Math.max(FRecurs (fu.left,x), FRecurs(fu.right,x));
					case Min:
						return Math.min(FRecurs (fu.left,x), FRecurs(fu.right,x));
					case Comp:
						return fu.left.f(FRecurs(fu.right,x));
					case None:
						break;
					case Error:
						break;	
					}
				}
				return 0;
			}
			catch(Exception e) {
				throw new RuntimeException("this operation is'nt legal, please insert legal one.");		
			}
		}

		@Override
		public function initFromString(String s) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public function copy() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void plus(function f1) {
			// TODO Auto-generated method stub
			this.setLeft(this);
			this.setRight(f1);
			this.setOp(Operation.Plus);
		}

		@Override
		public void mul(function f1) {
			// TODO Auto-generated method stub
			this.setLeft(this);
			this.setRight(f1);
			this.setOp(Operation.Times);
		}

		@Override
		public void div(function f1) {
			// TODO Auto-generated method stub
			this.setLeft(this);
			this.setRight(f1);
			this.setOp(Operation.Divid);
		}

		@Override
		public void max(function f1) {
			// TODO Auto-generated method stub
			this.setLeft(this);
			this.setRight(f1);
			this.setOp(Operation.Max);
		}

		@Override
		public void min(function f1) {
			// TODO Auto-generated method stub
			this.setLeft(this);
			this.setRight(f1);
			this.setOp(Operation.Min);
		}

		@Override
		public void comp(function f1) {
			// TODO Auto-generated method stub
			this.setLeft(this);
			this.setRight(f1);
			this.setOp(Operation.Comp);
		}

		@Override
		public function left() {
			// TODO Auto-generated method stub
			return this.left();
		}

		@Override
		public function right() {
			// TODO Auto-generated method stub
			return this.right;
		}

		@Override
		public Operation getOp() {
			// TODO Auto-generated method stub
			return this.op;
		}
		private void setLeft (ComplexFunction l) {
			this.left= l;
		}
		private void setRight (function r) {
			ComplexFunction r1= (ComplexFunction) r;
			this.right= r1;
		}
		private void setOp (Operation o) {
			this.op= o;
		}
		
	}
}
