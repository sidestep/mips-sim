package mips;

public class ALU {
	public short operation(short op, short funct, short src, short rs) {
		if(op == 0) {
			switch(funct) {
			case Instruction.FUNCT_ADD:
				return (short) (rs + src);
			case Instruction.FUNCT_SUB:
				return (short) (rs - src);
			case Instruction.FUNCT_AND:
				return (short) (rs & src);
			case Instruction.FUNCT_OR:
				return (short) (rs | src);
			case Instruction.FUNCT_NOR:
				return (short) ~((byte)((byte)rs | (byte)src));
			case Instruction.FUNCT_SLT:
				return (short) ((rs < src) ? 1 : 0);
			}
		}
		
		switch(op) {
		case Instruction.OPCODE_LW:
		case Instruction.OPCODE_SW:
			return operation((short)0, Instruction.FUNCT_ADD, src, rs);
		case Instruction.OPCODE_BEQ:
			return operation((short)0, Instruction.FUNCT_SUB, src, rs);
		}

		return 0;
	}
}
