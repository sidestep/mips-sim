package mips;

public class Control {
	/*
	 * Each control line, including
	 * RegDist,
	 * Branch,
	 * MemRead,
	 * MemtoReg,
	 * ALUOp,
	 * MemWrite,
	 * ALUSrc, and
	 * RegWrite
	 * must be implemented as a distinct Boolean variable.
	 */
	private boolean RegDist;
	private boolean Branch;
	private boolean MemRead;
	private boolean MemtoReg;
	private boolean ALUOp;
	private boolean MemWrite;
	private boolean ALUsrc;
	private boolean RegWrite;

	public void setInstruction(Instruction instruction) {
		short opcode = instruction.getOpcode();

		if(instruction.is_r_type()) {
			RegWrite = true;
			ALUOp = true;
		}

		if((opcode == Instruction.OPCODE_LW)
				|| (opcode == Instruction.OPCODE_SW)) {
			ALUsrc = true;
			ALUOp = true;
		}
	}

	public boolean isRegDist() {
		return RegDist;
	}

	public boolean isBranch() {
		return Branch;
	}

	public boolean isMemRead() {
		return MemRead;
	}

	public boolean isMemtoReg() {
		return MemtoReg;
	}

	public boolean isALUOp() {
		return ALUOp;
	}

	public boolean isMemWrite() {
		return MemWrite;
	}

	public boolean isALUsrc() {
		return ALUsrc;
	}

	public boolean isRegWrite() {
		return RegWrite;
	}


}
