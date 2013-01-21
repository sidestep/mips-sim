package mips;

/**
 * The processor register file
 */
public class RegisterFile extends DataField {
	private int rr1;
	private int rr2;
	private int wr;

	public RegisterFile() {
		super(32);
	}

	public void setRegisters(int read1, int read2, int write) {
		rr1 = read1;
		rr2 = read2;
		wr = write;
	}

	public int readData1() {
		return get(rr1);
	}

	public int readData2() {
		return get(rr2);
	}

	public void write(boolean RegWrite, int data) {
		if(RegWrite) {
			set(wr, data);
		}
	}


	@Override
	protected int get(int index) {
		if(index == 0) {
			return 0; // $zero register
		}
		return super.get(index);
	}

	@Override
	protected void set(int index, int value) {
		if(index == 0) {
			return;
		}
		super.set(index, value);
	}

	public static String name(int index) {
		String[] names = {
				"$zero", 
				"$at",
				"$v0",
				"$v1",
				"$a0",
				"$a1",
				"$a2",
				"$a3",
				"$t0",
				"$t1",
				"$t2",
				"$t3",
				"$t4",
				"$t5",
				"$t6",
				"$t7",
				"$s0",
				"$s1",
				"$s2",
				"$s3",
				"$s4",
				"$s5",
				"$s6",
				"$s7",
				"$t8",
				"$t9",
				"$k0",
				"$k1",
				"$gp",
				"$sp",
				"$fp",
				"$ra"
		};
		return names[index];
	}
}
