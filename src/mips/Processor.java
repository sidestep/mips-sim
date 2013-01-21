package mips;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
		int rtv = 0;
		int rsv = 0;
		int new_pc = pc.get();
		int branch_pc;

		if(isDone()) {
			return;
		}
		i = instructions.fetch(pc);
		Control control = new Control(i);

		rtv = register.get(i.getRt());
		rsv = register.get(i.getRs());
		
		alu.setOperation(
				ALUControl.getControl(control.isALUOp1(), control.isALUOp0(), i.getFunct()),
				mux(rtv, i.getAddr(), control.isALUsrc()),
				rsv);
		
		alu_out = alu.getOut();
		alu_zero = alu.isZero();


		if(control.isMemRead()) {
			data_out = memory.get(alu_out);
		}

		if(control.isMemWrite()) {
			memory.set(alu_out, rtv);
		}

		if(control.isRegWrite()) {
			register.set(
					mux(i.getRt(), i.getRd(), control.isRegDist()),
					(byte)mux(alu_out, data_out, control.isMemtoReg()));
		}

		new_pc += 4;
		branch_pc = new_pc + (i.getAddr() << 2);
		
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
