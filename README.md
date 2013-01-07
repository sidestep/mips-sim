# mips-sim
mips-sim is a single-cycle MIPS processor simulator written in Java, 
implementing a very small subset of the MIPS32 instruction set. 
Included is a GUI that lets the user load a text file with assembly code, 
that can then be run or stepped through, executing each instruction.


## Supported instructions
* add Rd, Rs, Rt : Rd = Rs + Rt
* sub Rd, Rs, Rt : Rd = Rs - Rt
* and Rd, Rs, Rt : Rd = Rs & Rt
* or Rd, Rs, Rt : Rd = Rs | Rt
* nor Rd, Rs, Rt : Rd = ~(Rs | Rt)
* slt Rd, Rs, Rt : Rd = (Rs < Rt)? 1: 0
* lw Rt, Off16(Rs) : Rt = Mem32(Rs + Off16)
* sw Rt, Off16(Rs) : Mem32(Rs + Off16) = Rt
* beq Rs, Rt, Off18 : If Rs = Rt, PC += Off18
* nop
* exit
