package mips;

public class Testruns {

	public static void main(String[] args) {
		try {
			new Instruction("add $v0, $v1, $a0");
			System.out.println("0x20, 2, 3, 4\n");

			new Instruction("sub $a1, $a2, $a3");
			System.out.println("0x22, 5, 6, 7\n");

			new Instruction("and $t0, $t1, $t2");
			System.out.println("0x24, 8, 9, 10\n");

			new Instruction("or $t3, $t4, $t5");
			System.out.println("0x25, 11, 12, 13\n");

			new Instruction("nor $t6, $t7, $s0");
			System.out.println("0x27, 14, 15, 16\n");

			new Instruction("slt $s1, $s2, $s3");
			System.out.println("0x2a, 17, 18, 19\n");


			new Instruction("lw $t8, 0x50");
			System.out.println("0x23, 24, 0x50\n");

			new Instruction("sw $gp, -60");
			System.out.println("0x2b, 28, 0xffc4\n");


			new Instruction("beq $sp, $fp, 0x01");
			System.out.println("0x2b, 29, 30, 0x01\n");

		} catch(Exception e) {
			e.printStackTrace();
		}


	}

}
