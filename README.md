# mips-sim
A MIPS-simulator implementing a very small subset of the MIPS instruction set.

Comes with a swing GUI


## Supported instructions
* add
* sub
* and
* or
* nor
* slt
* lw
* sw
* beq
* nop
* exit

## Instruction breakdown
    add rd, rs, rt      0, 0x20
    sub rd, rs, rt      0, 0x22
    and rd, rs, rt      0, 0x24
    or  rd, rs, rt      0, 0x25
    nor rd, rs, rt      0, 0x27
    slt rd, rs, rt      0, 0x2a

    lw rt, offset(rs)   0x23
    sw rt, offset(rs)   0x2b    

    beq rs, rt, label   4
    nop                 0
    exit                0, 0xc with $v = 10 (???)