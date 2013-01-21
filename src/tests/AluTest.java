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
		alu.setOperation(ALU.ADD, 3, 2);
		assertEquals("3+2", 5, alu.getOut());

		alu.setOperation(ALU.ADD, -1, -128);
		assertEquals("-1 + -128", -129, alu.getOut());
	}

	@Test
	public void subtract() {
		alu.setOperation(ALU.SUBTRACT, 2, 3);
		assertEquals("3-2", 1, alu.getOut());

		alu.setOperation(ALU.SUBTRACT, 2, -3);
		assertEquals("-3 - 2", -5, alu.getOut());
	}

	@Test
	public void and() {
		alu.setOperation(ALU.AND, 0xff, 0xf0);
		assertEquals("0xff & 0xf0", 0xf0, alu.getOut());
	}

	@Test
	public void or() {
		alu.setOperation(ALU.OR, 0xff, 0xf0);
		assertEquals("0xff | 0xf0", 0xff, alu.getOut());
	}

	@Test
	public void nor() {
		alu.setOperation(ALU.NOR, 0xaa, 0xee);
		assertEquals("0xaa ~| 0xee", ~(0xaa | 0xee), alu.getOut());
	}

	@Test
	public void slt() {
		alu.setOperation(ALU.SLT, 0xf, 0xe);
		assertEquals("0xf < 0xe", 1, alu.getOut());

		alu.setOperation(ALU.SLT, 0xf, 0xfe);
		assertEquals("0xf < 0xfe", 0,  alu.getOut());
	}



}
