package tests;

import static org.junit.Assert.assertEquals;
import mips.ALU;

import org.junit.Before;
import org.junit.Test;

public class AluTest {
	private ALU alu;

	@Before
	public void setupAlu() {
		alu = new ALU();
	}

	@Test
	public void add() {
		assertEquals("3+2",
				5, 
				alu.operation(ALU.ADD, 3, 2));
	}

	@Test
	public void subtract() {
		assertEquals("3-2",
				1, 
				alu.operation(ALU.SUBTRACT, 2, 3));
	}

	@Test
	public void and() {
		assertEquals("0xff & 0xf0",
				0xf0, 
				alu.operation(ALU.AND, 0xff, 0xf0));
	}

	@Test
	public void or() {
		assertEquals("0xff | 0xf0",
				0xff, 
				alu.operation(ALU.OR, 0xff, 0xf0));
	}

	@Test
	public void nor() {
		assertEquals("0xaa ~| 0xee",
				~(0xaa | 0xee), 
				alu.operation(ALU.NOR, 0xaa, 0xee));
	}

	@Test
	public void slt() {
		assertEquals("0xf < 0xe",
				1, 
				alu.operation(ALU.SLT, 0xf, 0xe));
		assertEquals("0xf < 0xfe",
				0, 
				alu.operation(ALU.SLT, 0xf, 0xfe));
	}



}
