package Simulation;

public class Control {
	private Instruction instruction;

	private boolean RegWrite;
	private boolean MemRead;
	private boolean MemWrite;
	private boolean Branch;
	private boolean AluOP;

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;


	}

	public boolean isRegWrite() {
		return RegWrite;
	}

	public boolean isMemRead() {
		return MemRead;
	}

	public boolean isMemWrite() {
		return MemWrite;
	}

	public boolean isBranch() {
		return Branch;
	}

	public boolean isAluOP() {
		return AluOP;
	}


}
