package mips;

/**
 * The mips control module, exhibits or inhibits processor modules 
 * depending on the instruction.
 */
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
	private boolean ALUOp1;
	private boolean ALUOp0;
	private boolean MemWrite;
	private boolean ALUsrc;
	private boolean RegWrite;

	public Control(Instruction instruction) {
		short opcode = instruction.getOpcode();

		if(instruction.is_r_type()) {
			RegDist = true;
			RegWrite = true;
			ALUOp1 = true;
		}

		else if(opcode == Instruction.OPCODE_LW) {
			MemRead = true;
			MemtoReg = true;
			RegWrite = true;
			ALUsrc = true;
		}

		else if(opcode == Instruction.OPCODE_SW) {
			MemWrite = true;
			ALUsrc = true;
		}

		else if(opcode == Instruction.OPCODE_BEQ) {
			Branch = true;
			ALUOp0 = true;
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

	public boolean isALUOp1() {
		return ALUOp1;
	}

	public boolean isALUOp0() {
		return ALUOp0;
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
