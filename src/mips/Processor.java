package mips;
import java.util.ArrayList;
import java.util.List;

/**
 * This class weaves together all the modules of the mips processor
 * with muxes, provides a way to step through a set of instructions, 
 * and allows the state of the register and memory to be retrieved.
 */
public class Processor {

	private ProgramCounter pc;

	private RegisterFile register;
	private MemoryFile memory;
	private ALU alu;

	private InstructionMemoryFile instructions;

	/**
	 * Creates a new processor with a zeroes register and memory
	 */
	public Processor() {
		pc = new ProgramCounter();
		instructions = new InstructionMemoryFile();
		register = new RegisterFile();
		alu = new ALU();
		memory = new MemoryFile();
	}

	/**
	 * Feeds instructions into the processor
	 * Also resets the processor
	 * @param instructions
	 */
	public void setInstructionSet(List<Instruction> instructions) {
		this.instructions.load(new ArrayList<Instruction>(instructions));
		reset();
	}

	/**
	 * Resets all registers and memory locations to 0, and the pc to 0
	 */
	public void reset() {
		pc.reset();
		register.reset();
		memory.reset();
	}

	/**
	 * Steps through the iteration. Does nothing when the simulation has ended
	 */
	public void step() {
		Instruction i;
		int alu_out = 0;
		boolean alu_zero = false;
		int data_out = 0;
		int write_data;
		int regData1 = 0;
		int regData2 = 0;
		int new_pc = pc.get();
		int branch_pc;

		if(isDone()) {
			return;
		}

		//Fetch
		i = instructions.fetch(pc);
		Control control = new Control(i);

		//Reg
		int writeReg = mux(i.getRt(), i.getRd(), control.isRegDist());
		register.setRegisters(i.getRs(), i.getRt(), writeReg);
		regData1 = register.readData1();
		regData2 = register.readData2();


		//Alu
		alu.setOperation(
				ALUControl.getControl(control.isALUOp1(), control.isALUOp0(), i.getFunct()),
				mux(regData2, i.getImm(), control.isALUsrc()),
				regData1);
		alu_out = alu.getOut();
		alu_zero = alu.isZero();

		//Mem
		data_out = memory.cycle(alu_out, regData2, control.isMemRead(), control.isMemWrite());

		//Writeback
		write_data = mux(alu_out, data_out, control.isMemtoReg());
		register.write(control.isRegWrite(), write_data);


		new_pc += 4;
		branch_pc = new_pc + (i.getImm() << 2);
		new_pc = mux(new_pc, branch_pc, control.isBranch() && alu_zero);
		pc.set(new_pc);
	}

	private int mux(int value1, int value2, boolean getSecond) {
		if(getSecond) {
			return value2;
		}
		return value1;
	}

	/**
	 * Returns true if the simulation is done
	 * @return
	 */
	public boolean isDone() {
		return pc.get() >= instructions.length() || instructions.fetch(pc).isExit();
	}

	/**
	 * @return the pc value
	 */
	public int getPcValue() {
		return pc.get();
	}

	public int[] getRegisters() {
		return register.getRawData();
	}

	public int[] getMemory() {
		return memory.getRawData();
	}

	public List<Integer> getChangedRegisters() {
		return register.getChangedIndices();
	}

	public List<Integer> getChangedMemory() {
		return memory.getChangedIndices();
	}


}
