package mips;

public class ALU {
	public int operation(short op, short funct, int srcv, int rsv) {
		if(op == 0) {
			switch(funct) {
			case Instruction.FUNCT_ADD:
				return rsv + srcv;
			case Instruction.FUNCT_SUB:
				return rsv - srcv;
			case Instruction.FUNCT_AND:
				return rsv & srcv;
			case Instruction.FUNCT_OR:
				return rsv | srcv;
			case Instruction.FUNCT_NOR:
				return ~((byte)((byte)rsv | (byte)srcv));
			case Instruction.FUNCT_SLT:
				return rsv < srcv ? 1 : 0;
			}
		}

		switch(op) {
		case Instruction.OPCODE_LW:
		case Instruction.OPCODE_SW:
			return operation((short)0, Instruction.FUNCT_ADD, srcv, rsv);
		case Instruction.OPCODE_BEQ:
			return operation((short)0, Instruction.FUNCT_SUB, srcv, rsv);
		}

		return 0;
	}
}
