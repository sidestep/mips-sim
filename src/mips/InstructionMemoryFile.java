package mips;

import java.util.List;
import java.util.ListIterator;

public class InstructionMemoryFile {
	private Instruction[] instructions = {};

	public void load(List<Instruction> instructions) {
		this.instructions = new Instruction[instructions.size()];
		ListIterator<Instruction> iterator = instructions.listIterator();
		for(int i = 0; i < instructions.size(); i++) {
			this.instructions[i] = iterator.next();
		}
	}

	public Instruction fetch(ProgramCounter pc) {
		return instructions[pc.get()/4];
	}

	public int length() {
		return instructions.length * 4;
	}
}
