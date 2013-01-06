package mips;

public class ALU {
	public static final short AND = 0;
	public static final short OR = 1;
	public static final short ADD = 2;
	public static final short SUBTRACT = 6;
	public static final short SLT = 7;
	public static final short NOR = 12;

	public int operation(short control, int srcv, int rsv) {
		switch(control) {
		case ADD:
			return rsv + srcv;
		case SUBTRACT:
			return rsv - srcv;
		case AND:
			return rsv & srcv;
		case OR:
			return rsv | srcv;
		case NOR:
			return ~(rsv | srcv);
		case SLT:
			return rsv < srcv ? 1 : 0;
		}

		return 0;
	}
}
