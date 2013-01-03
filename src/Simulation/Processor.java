package Simulation;
import java.util.List;



public class Processor {

	private Instruction[] instructions = {};
	private int pc;

	private Control control;
	private RegisterFile reg;
	private DataFile data;

	public Processor() {
		control = new Control();
		reg = new RegisterFile();
		data = new DataFile();

	}

	public void setInstructionSet(List<Instruction> instructions) {
		this.instructions = (Instruction[]) instructions.toArray();
	}

	public void step() {
		if((pc/4) >= instructions.length) {
			return;
		}
		Instruction i = instructions[pc/4];
		control.setInstruction(i);


	}



}
