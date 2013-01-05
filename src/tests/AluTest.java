package tests;

import static org.junit.Assert.*;

import mips.ALU;
import mips.Instruction;

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
				alu.operation(
						(short)0, Instruction.FUNCT_ADD, (short)3, (short)2));
	}
	
	@Test
	public void sub() {
		assertEquals("3-2",
				1, 
				alu.operation(
						(short)0, Instruction.FUNCT_SUB, (short)2, (short)3));
	}
	
	@Test
	public void and() {
		assertEquals("0xff & 0xf0",
				0xf0, 
				alu.operation(
						(short)0, Instruction.FUNCT_AND, (short)0xff, (short)0xf0));
	}
	
	@Test
	public void or() {
		assertEquals("0xff | 0xf0",
				0xff, 
				alu.operation(
						(short)0, Instruction.FUNCT_OR, (short)0xff, (short)0xf0));
	}
	
	@Test
	public void nor() {
		assertEquals("0xaa ~| 0xee",
				0x11, 
				alu.operation(
						(short)0, Instruction.FUNCT_NOR, (short)0xaa, (short)0xee));
	}
	
	@Test
	public void slt() {
		assertEquals("0xf < 0xe",
				1, 
				alu.operation(
						(short)0, Instruction.FUNCT_SLT, (short)0xf, (short)0xe));
		assertEquals("0xf < 0xfe",
				0, 
				alu.operation(
						(short)0, Instruction.FUNCT_SLT, (short)0xf, (short)0xfe));
	}
	
	
	
}
