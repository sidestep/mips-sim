package Simulation;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Instruction {
	public static final int FUNCT_ADD = 0x20;
	public static final int FUNCT_SUB = 0x22;
	public static final int FUNCT_AND = 0x24;
	public static final int FUNCT_OR = 0x25;
	public static final int FUNCT_NOR = 0x27;
	public static final int FUNCT_SLT = 0x2a;

	public static final int OPCODE_LW = 0x23;
	public static final int OPCODE_SW = 0x2b;

	public static final int OPCODE_BEQ = 4;

	private String repr;
	private short opcode = 0;
	private short funct = 0;
	private short rd = 0;
	private short rs = 0;
	private short rt = 0;
	private short addr = 0;
	private boolean r_type = false;


	public Instruction(String line) throws Exception {
		repr = line;

		line = line.replaceAll(",", "");
		StringTokenizer tokens = new StringTokenizer(line, " ");
		String op = "", t1 = "", t2 = "", t3 = "";

		op = tokens.nextToken();
		try {
			t1 = tokens.nextToken();
			t2 = tokens.nextToken();
			t3 = tokens.nextToken();
		} catch(NoSuchElementException e) {}

		if(op.equalsIgnoreCase("add")) {
			funct = FUNCT_ADD;
			r_type = true;
		} else if(op.equalsIgnoreCase("sub")) {
			funct = FUNCT_SUB;
			r_type = true;
		} else if(op.equalsIgnoreCase("and")) {
			funct = FUNCT_AND;
			r_type = true;
		} else if(op.equalsIgnoreCase("or")) {
			funct = FUNCT_OR;
			r_type = true;
		} else if(op.equalsIgnoreCase("nor")) {
			funct = FUNCT_NOR;
			r_type = true;
		} else if(op.equalsIgnoreCase("slt")) {
			funct = FUNCT_SLT;
			r_type = true;
		}

		else if(op.equalsIgnoreCase("lw")) {
			opcode = OPCODE_LW;
		} else if(op.equalsIgnoreCase("sw")) {
			opcode = OPCODE_SW;
		}

		else if(op.equalsIgnoreCase("beq")) {
			opcode = OPCODE_BEQ;
		}


		// Parse additional parameters
		if((opcode == OPCODE_LW) || (opcode == OPCODE_SW)) {
			rt = parseReg(t1);
			addr = parseAddr(t2);

			System.out.printf("0x%x, %d, 0x%x\n", opcode, rt, addr);

		} else if(r_type) {
			rd = parseReg(t1);
			rs = parseReg(t2);
			rt = parseReg(t3);

			System.out.printf("0x%x, %d, %d, %d\n", funct, rd, rs, rt);

		} else if(opcode == OPCODE_BEQ) {
			rs = parseReg(t1);
			rt = parseReg(t2);
			addr = parseAddr(t3);

			System.out.printf("0x%x, %d, %d, 0x%x\n", opcode, rs, rt, addr);
		}






	}


	private short parseAddr(String address) {
		if(address.contains("x")) {
			return Short.parseShort(
					address.substring(address.indexOf('x')+1), 16);
		}
		return Short.parseShort(address);
	}


	private short parseReg(String register) throws Exception {
		if(register.charAt(0) == '$') {
			if(register.equalsIgnoreCase("$zero")) {
				return 0;
			} else if(register.equalsIgnoreCase("$gp")) {
				return 28;
			} else if(register.equalsIgnoreCase("$sp")) {
				return 29;
			} else if(register.equalsIgnoreCase("$fp")) {
				return 30;
			} else if(register.equalsIgnoreCase("$ra")) {
				return 31;
			}

			char prefix = register.charAt(1);
			short number = Short.parseShort(register.substring(2));
			switch(prefix) {
			case 'v':
				number += 2;
				break;
			case 'a':
				number += 4;
				break;
			case 't':
				number += 8;
				if(number >= 8) {
					number += 8;
				}
				break;
			case 's':
				number += 16;
				break;

			default:
				throw new Exception("Invalid register " + register);
			}
			return number;
		}

		return parseAddr(register);
	}
}
