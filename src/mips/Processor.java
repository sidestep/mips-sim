package mips;
import java.util.List;



public class Processor {

	private Instruction[] instructions = {};
	private int pc;

	private Control control;
	private RegisterFile reg;
	private DataFile data;
	private ALU alu;

	public Processor() {
		control = new Control();
		reg = new RegisterFile();
		data = new DataFile();
		alu = new ALU();

	}

	public void setInstructionSet(List<Instruction> instructions) {
		this.instructions = (Instruction[]) instructions.toArray();
	}

	public void step() {
		Instruction i;
		short alu_out = 0;
		short data_out = 0;
		short rdv = 0;
		short rtv = 0;
		short rsv = 0;

		if((pc/4) >= instructions.length) {
			return;
		}
		i = instructions[pc/4];
		control.setInstruction(i);


		pc += 4;

		rtv = reg.get(i.getRt());
		rsv = reg.get(i.getRs());
		rdv = reg.get(i.getRd());

		if(control.isALUOp()) {
			alu_out = alu.operation(
					i.getOpcode(),
					i.getFunct(),
					i.getRt(),
					mux(rtv, i.getAddr(), control.isALUsrc()));
		}

		if(control.isMemRead()) {
			data_out = data.get(alu_out);
		}

		if(control.isRegWrite()) {
			reg.set(i.getRd(),
					(byte)mux(alu_out, data_out, control.isMemtoReg()));
		}

	}

	private short mux(short value1, short value2, boolean getSecond) {
		if(getSecond) {
			return value2;
		}
		return value1;
	}



}
