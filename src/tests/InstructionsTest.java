package tests;

import static org.junit.Assert.*;

import mips.Instruction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InstructionsTest {

	@Test
	public void add() throws Exception {
		Instruction i = new Instruction("add $v0, $v1, $a0");
		assertEquals("funct", i.getFunct(), Instruction.FUNCT_ADD);
		assertEquals("rd", i.getRd(), 2);
		assertEquals("rs", i.getRs(), 3);
		assertEquals("rt", i.getRt(), 4);
	}
	
	@Test
	public void sub() throws Exception {
		Instruction i = new Instruction("sub $a1, $a2, $a3");
		assertEquals("funct", i.getFunct(), Instruction.FUNCT_SUB);
		assertEquals("rd", i.getRd(), 5);
		assertEquals("rs", i.getRs(), 6);
		assertEquals("rt", i.getRt(), 7);
	}
	
	@Test
	public void and() throws Exception {
		Instruction i = new Instruction("and $t0, $t1, $t2");
		assertEquals("funct", i.getFunct(), Instruction.FUNCT_AND);
		assertEquals("rd", i.getRd(), 8);
		assertEquals("rs", i.getRs(), 9);
		assertEquals("rt", i.getRt(), 10);
	}
	
	@Test
	public void or() throws Exception {
		Instruction i = new Instruction("or $t3, $t4, $t5");
		assertEquals("funct", i.getFunct(), Instruction.FUNCT_OR);
		assertEquals("rd", i.getRd(), 11);
		assertEquals("rs", i.getRs(), 12);
		assertEquals("rt", i.getRt(), 13);
	}
	
	@Test
	public void nor() throws Exception {
		Instruction i = new Instruction("nor $t6, $t7, $s0");
		assertEquals("funct", i.getFunct(), Instruction.FUNCT_NOR);
		assertEquals("rd", i.getRd(), 14);
		assertEquals("rs", i.getRs(), 15);
		assertEquals("rt", i.getRt(), 16);
	}
	
	@Test
	public void slt() throws Exception {
		Instruction i = new Instruction("slt $s1, $s2, $s3");
		assertEquals("funct", i.getFunct(), Instruction.FUNCT_SLT);
		assertEquals("rd", i.getRd(), 17);
		assertEquals("rs", i.getRs(), 18);
		assertEquals("rt", i.getRt(), 19);
	}
	
	
	@Test
	public void lw() throws Exception {
		Instruction i = new Instruction("lw $t8, 0x50");
		assertEquals("opcode", i.getOpcode(), Instruction.OPCODE_LW);
		assertEquals("rd", i.getRd(), 24);
		assertEquals("addr",  i.getAddr(), 0x50);
	}
	
	@Test
	public void sw() throws Exception {
		Instruction i = new Instruction("sw $gp, 893");
		assertEquals("opcode", i.getOpcode(), Instruction.OPCODE_SW);
		assertEquals("rd", i.getRd(), 28);
		assertEquals("addr",  i.getAddr(), 893);
	}
	
	@Test
	public void beq() throws Exception {
		Instruction i = new Instruction("beq $sp, $fp, 0x01");
		assertEquals("opcode", i.getOpcode(), Instruction.OPCODE_BEQ);
		assertEquals("rd", i.getRd(), 29);
		assertEquals("rt", i.getRt(), 30);
		assertEquals("addr",  i.getAddr(), 0x01);
	}
	
	

}
