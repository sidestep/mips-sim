package mips;

public class ALU {
	public short operation(short op, short funct, short rt, short rsimm) {
		if(op == 0) {
			switch(funct) {
			case Instruction.FUNCT_ADD:
				return (short) (rsimm + rt);
			case Instruction.FUNCT_SUB:
				return (short) (rsimm - rt);
			case Instruction.FUNCT_AND:
				return (short) (rsimm & rt);
			case Instruction.FUNCT_OR:
				return (short) (rsimm | rt);
			case Instruction.FUNCT_NOR:
				return (short) ~((byte)((byte)rsimm | (byte)rt));
			case Instruction.FUNCT_SLT:
				return (short) ((rsimm < rt) ? 1 : 0);
			}


		}

		return 0;
	}
}
