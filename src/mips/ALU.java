package mips;

/**
 * The mathematical/logical unit of the mips processor
 */
public class ALU {
	public static final short AND = 0;
	public static final short OR = 1;
	public static final short ADD = 2;
	public static final short SUBTRACT = 6;
	public static final short SLT = 7;
	public static final short NOR = 12;
	
	private int out;
	/**
	 * @return the out value
	 */
	public int getOut() {
		return out;
	}

	/**
	 * @return the zero line control
	 */
	public boolean isZero() {
		return zero;
	}

	private boolean zero;

	public void setOperation(short control, int srcv, int rsv) {
		zero = false;
		out = 0;
		
		switch(control) {
		case ADD:
			out =  rsv + srcv;
			break;
		case SUBTRACT:
			out = rsv - srcv;
			if(out == 0) {
				zero = true;
			}
			break;
		case AND:
			out = rsv & srcv;
			break;
		case OR:
			out = rsv | srcv;
			break;
		case NOR:
			out = ~(rsv | srcv);
			break;
		case SLT:
			out = rsv < srcv ? 1 : 0;
			break;
		}
	}
}
