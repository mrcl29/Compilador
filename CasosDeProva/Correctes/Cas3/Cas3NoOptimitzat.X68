*-----------------------------------------------------------
* Title      : Codi Ensamblador 68K
* Written by : Marc Llobera Villalonga
* Description: Codi ensamblador generat per el compilador
*              de Marc Llobera per a la pràctica de
*              l'assignatura de Compiladors.
*-----------------------------------------------------------
    ORG    $1000
START:                  ; first instruction of program


    ; t3 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 12(A6)

    ; t4 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 16(A6)

    ; t5 = t3 * t4
    MOVE.L    12(A6), D0
    MOVE.L    16(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 20(A6)

    ; t6 = 10000
    MOVE.L    #10000, D0
    MOVE.L    D0, 24(A6)

    ; t1[t5] = t6
    MOVE.L    20(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    24(A6), D1
    MOVE.L    D1, (A0)

    ; t7 = 2
    MOVE.L    #2, D0
    MOVE.L    D0, 28(A6)

    ; in(t8)
    MOVE.L    28(A6), D0
    JSR       VALIDATE_INPUT
    MOVE.L    D1, 32(A6)

    ; t9 = t8
    MOVE.L    32(A6), D0
    MOVE.L    D0, 36(A6)

    ; t11 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 44(A6)

    ; t12 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 48(A6)

    ; t13 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 52(A6)

    ; t14 = t12 * t13
    MOVE.L    48(A6), D0
    MOVE.L    52(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 56(A6)

    ; t15 = t1[t14]
    MOVE.L    56(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    (A0), D1
    MOVE.L    D1, 60(A6)

    ; t16 = t11
    MOVE.L    44(A6), D0
    MOVE.L    D0, 64(A6)

    ; goto e2
    BRA       E2

    ; e1
E1:

    ; t17 = t15
    MOVE.L    60(A6), D0
    MOVE.L    D0, 68(A6)

    ; t18 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 72(A6)

    ; t16 = t16 + t18
    MOVE.L    64(A6), D0
    MOVE.L    72(A6), D1
    ADD.L     D1, D0
    MOVE.L    D0, 64(A6)

    ; if t16 >= t17 then goto e3
    MOVE.L    64(A6), D0
    MOVE.L    68(A6), D1
    CMP.L     D1, D0
    BGE       E3

    ; e2
E2:

    ; t19 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 76(A6)

    ; t20 = t16
    MOVE.L    64(A6), D0
    MOVE.L    D0, 80(A6)

    ; out(t20 :: t19)
    MOVE.L    76(A6), D0
    MOVE.L    80(A6), D1
    CMP.L     #1, D0
    BNE       E26
    JSR       PRINT_INTEGER
    BRA       E4
E26:
    CMP.L     #2, D0
    BNE       E27
    JSR       PRINT_BOOLEAN
    BRA       E4
E27:
    LEA       ERROR_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
E4:

    ; t21 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 84(A6)

    ; if t9 == t21 then goto e5
    MOVE.L    36(A6), D0
    MOVE.L    84(A6), D1
    CMP.L     D1, D0
    BEQ       E5

    ; goto e6
    BRA       E6

    ; e5
E5:

    ; t22 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 88(A6)

    ; goto e7
    BRA       E7

    ; e6
E6:

    ; t22 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 88(A6)

    ; e7
E7:

    ; t23 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 92(A6)

    ; t24 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 96(A6)

    ; t25 = t23 * t24
    MOVE.L    92(A6), D0
    MOVE.L    96(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 100(A6)

    ; t26 = t1[t25]
    MOVE.L    100(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    (A0), D1
    MOVE.L    D1, 104(A6)

    ; t27 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 108(A6)

    ; t28 = t26 - t27
    MOVE.L    104(A6), D0
    MOVE.L    108(A6), D1
    SUB.L     D1, D0
    MOVE.L    D0, 112(A6)

    ; if t16 == t28 then goto e8
    MOVE.L    64(A6), D0
    MOVE.L    112(A6), D1
    CMP.L     D1, D0
    BEQ       E8

    ; goto e9
    BRA       E9

    ; e8
E8:

    ; t29 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 116(A6)

    ; goto e10
    BRA       E10

    ; e9
E9:

    ; t29 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 116(A6)

    ; e10
E10:

    ; t30 = t22
    MOVE.L    88(A6), D0
    MOVE.L    D0, 120(A6)

    ; if t30 == -1 then goto e11
    MOVE.L    120(A6), D0
    MOVE.L    #-1, D1
    CMP.L     D1, D0
    BEQ       E11

    ; t31 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 124(A6)

    ; goto e12
    BRA       E12

    ; e11
E11:

    ; t32 = t29
    MOVE.L    116(A6), D0
    MOVE.L    D0, 128(A6)

    ; if t32 == -1 then goto e13
    MOVE.L    128(A6), D0
    MOVE.L    #-1, D1
    CMP.L     D1, D0
    BEQ       E13

    ; t31 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 124(A6)

    ; goto e12
    BRA       E12

    ; e13
E13:

    ; t31 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 124(A6)

    ; e12
E12:

    ; if t31 == 0 then goto e14
    MOVE.L    124(A6), D0
    MOVE.L    #0, D1
    CMP.L     D1, D0
    BEQ       E14

    ; t33 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 132(A6)

    ; t34 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 136(A6)

    ; t35 = t33 * t34
    MOVE.L    132(A6), D0
    MOVE.L    136(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 140(A6)

    ; t1[t35] = t9
    MOVE.L    140(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    36(A6), D1
    MOVE.L    D1, (A0)

    ; t36 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 144(A6)

    ; t37 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 148(A6)

    ; t38 = t36 * t37
    MOVE.L    144(A6), D0
    MOVE.L    148(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 152(A6)

    ; t39 = t1[t38]
    MOVE.L    152(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    (A0), D1
    MOVE.L    D1, 156(A6)

    ; t40 = 2
    MOVE.L    #2, D0
    MOVE.L    D0, 160(A6)

    ; t41 = t39
    MOVE.L    156(A6), D0
    MOVE.L    D0, 164(A6)

    ; out(t41 :: t40)
    MOVE.L    160(A6), D0
    MOVE.L    164(A6), D1
    CMP.L     #1, D0
    BNE       E28
    JSR       PRINT_INTEGER
    BRA       E15
E28:
    CMP.L     #2, D0
    BNE       E29
    JSR       PRINT_BOOLEAN
    BRA       E15
E29:
    LEA       ERROR_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
E15:

    ; e14
E14:

    ; goto e1
    BRA       E1

    ; e3
E3:

    ; t42 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 168(A6)

    ; t43 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 172(A6)

    ; t44 = t42 * t43
    MOVE.L    168(A6), D0
    MOVE.L    172(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 176(A6)

    ; t45 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 180(A6)

    ; t1[t44] = t45
    MOVE.L    176(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    180(A6), D1
    MOVE.L    D1, (A0)

    ; t46 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 184(A6)

    ; t47 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 188(A6)

    ; t48 = t46 * t47
    MOVE.L    184(A6), D0
    MOVE.L    188(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 192(A6)

    ; t49 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 196(A6)

    ; t50 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 200(A6)

    ; t51 = t49 * t50
    MOVE.L    196(A6), D0
    MOVE.L    200(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 204(A6)

    ; t52 = t1[t51]
    MOVE.L    204(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    (A0), D1
    MOVE.L    D1, 208(A6)

    ; t53 = t52
    MOVE.L    208(A6), D0
    MOVE.L    D0, 212(A6)

    ; if t53 == 0 then goto e16
    MOVE.L    212(A6), D0
    MOVE.L    #0, D1
    CMP.L     D1, D0
    BEQ       E16

    ; t54 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 216(A6)

    ; goto e17
    BRA       E17

    ; e16
E16:

    ; t55 = t9
    MOVE.L    36(A6), D0
    MOVE.L    D0, 220(A6)

    ; if t55 == 0 then goto e18
    MOVE.L    220(A6), D0
    MOVE.L    #0, D1
    CMP.L     D1, D0
    BEQ       E18

    ; t54 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 216(A6)

    ; goto e17
    BRA       E17

    ; e18
E18:

    ; t54 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 216(A6)

    ; e17
E17:

    ; t56 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 224(A6)

    ; t57 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 228(A6)

    ; t58 = t56 * t57
    MOVE.L    224(A6), D0
    MOVE.L    228(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 232(A6)

    ; t59 = t1[t58]
    MOVE.L    232(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    (A0), D1
    MOVE.L    D1, 236(A6)

    ; if t16 == t59 then goto e19
    MOVE.L    64(A6), D0
    MOVE.L    236(A6), D1
    CMP.L     D1, D0
    BEQ       E19

    ; goto e20
    BRA       E20

    ; e19
E19:

    ; t60 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 240(A6)

    ; goto e21
    BRA       E21

    ; e20
E20:

    ; t60 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 240(A6)

    ; e21
E21:

    ; t61 = t54
    MOVE.L    216(A6), D0
    MOVE.L    D0, 244(A6)

    ; if t61 == -1 then goto e22
    MOVE.L    244(A6), D0
    MOVE.L    #-1, D1
    CMP.L     D1, D0
    BEQ       E22

    ; t62 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 248(A6)

    ; goto e23
    BRA       E23

    ; e22
E22:

    ; t63 = t60
    MOVE.L    240(A6), D0
    MOVE.L    D0, 252(A6)

    ; if t63 == -1 then goto e24
    MOVE.L    252(A6), D0
    MOVE.L    #-1, D1
    CMP.L     D1, D0
    BEQ       E24

    ; t62 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 248(A6)

    ; goto e23
    BRA       E23

    ; e24
E24:

    ; t62 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 248(A6)

    ; e23
E23:

    ; t1[t48] = t62
    MOVE.L    192(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    248(A6), D1
    MOVE.L    D1, (A0)

    ; t64 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 256(A6)

    ; t65 = 4
    MOVE.L    #4, D0
    MOVE.L    D0, 260(A6)

    ; t66 = t64 * t65
    MOVE.L    256(A6), D0
    MOVE.L    260(A6), D1
    MULS      D1, D0
    MOVE.L    D0, 264(A6)

    ; t67 = t1[t66]
    MOVE.L    264(A6), D0
    LEA       4(A6), A0
    ADD.L     D0, A0
    MOVE.L    (A0), D1
    MOVE.L    D1, 268(A6)

    ; t68 = 2
    MOVE.L    #2, D0
    MOVE.L    D0, 272(A6)

    ; t69 = t67
    MOVE.L    268(A6), D0
    MOVE.L    D0, 276(A6)

    ; out(t69 :: t68)
    MOVE.L    272(A6), D0
    MOVE.L    276(A6), D1
    CMP.L     #1, D0
    BNE       E30
    JSR       PRINT_INTEGER
    BRA       E25
E30:
    CMP.L     #2, D0
    BNE       E31
    JSR       PRINT_BOOLEAN
    BRA       E25
E31:
    LEA       ERROR_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
E25:

; FINAL

END:
    SIMHALT
PRINT_INTEGER:
    MOVE.L  D1, D2
    LEA     BUFFER, A0
    JSR     INT_TO_STR
    BRA     PRINT_STRING
PRINT_BOOLEAN:
    CMP.L   #-1, D1
    BEQ     PRINT_TRUE
    CMP.L   #0, D1
    BEQ     PRINT_FALSE
    LEA     ERROR_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
    RTS
PRINT_TRUE:
    LEA     TRUE_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
    RTS
PRINT_FALSE:
    LEA     FALSE_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
    RTS
PRINT_STRING:
    MOVE.L  A0, A1
    MOVE.W  #14, D0
    TRAP    #15
    LEA     NEWLINE, A1
    MOVE.W  #14, D0
    TRAP    #15
    RTS
INT_TO_STR:
    LEA     BUFFER+10, A1
    CLR.B   (A1)
    MOVE.L  D2, D3
    TST.L   D3
    BPL     POSITIVE
    NEG.L   D3
    MOVE.B  #'-', D4
POSITIVE:
    MOVE.B  #0, D5
CONVERT_LOOP:
    DIVU    #10, D3
    SWAP    D3
    ADD.B   #'0', D3
    MOVE.B  D3, -(A1)
    CLR.W   D3
    SWAP    D3
    ADDQ.B  #1, D5
    TST.L   D3
    BNE     CONVERT_LOOP
    TST.B   D4
    BEQ     NO_SIGN
    MOVE.B  D4, -(A1)
NO_SIGN:
    MOVE.L  A1, A0
    RTS
VALIDATE_INPUT:
    CMP.B   #1, D0
    BEQ     INTEGER_INPUT
    CMP.B   #2, D0
    BEQ     BOOLEAN_INPUT
    RTS
INTEGER_INPUT:
    LEA     PROMPT_INT, A0
    JSR     PRINT_STRING
    JSR     READ_STRING
    JSR     CONVERT_TO_INT
    TST.B   D1
    BEQ     VALID_INT
    LEA     ERROR_MSG, A0
    JSR     PRINT_STRING
    MOVE.L  #0, D1
    RTS
VALID_INT:
    MOVE.L  D2, D1
    RTS
BOOLEAN_INPUT:
    LEA     PROMPT_BOOL, A0
    JSR     PRINT_STRING
    JSR     READ_STRING
    JSR     CONVERT_TO_BOOL
    TST.B   D1
    BEQ     VALID_BOOL
    LEA     ERROR_MSG, A0
    JSR     PRINT_STRING
    MOVE.L  #0, D1
    RTS
VALID_BOOL:
    MOVE.L  D2, D1
    RTS
READ_STRING:
    LEA     INPUT_BUFFER, A1
READ_LOOP:
    MOVE.W  #5, D0
    TRAP    #15
    CMP.B   #$0D, D1
    BEQ     READ_DONE
    MOVE.B  D1, (A1)+
    BRA     READ_LOOP
READ_DONE:
    CLR.B   (A1)
    RTS
CONVERT_TO_INT:
    CLR.L   D2
    CLR.B   D3
    LEA     INPUT_BUFFER, A1
    MOVE.B  (A1), D1
    CMP.B   #'-', D1
    BNE     CONVERT_INT_LOOP
    MOVE.B  #1, D3
    ADDQ.L  #1, A1
CONVERT_INT_LOOP:
    MOVE.B  (A1)+, D1
    BEQ     CONVERT_INT_DONE
    CMP.B   #'0', D1
    BLT     INVALID_INPUT
    CMP.B   #'9', D1
    BGT     INVALID_INPUT
    SUB.B   #'0', D1
    MULU.W  #10, D2
    ADD.L   D1, D2
    BRA     CONVERT_INT_LOOP
CONVERT_INT_DONE:
    TST.B   D3
    BEQ     POSITIVE_NUMBER
    NEG.L   D2
POSITIVE_NUMBER:
    CLR.B   D1
    RTS
INVALID_INPUT:
    MOVE.B  #1, D1
    RTS
CONVERT_TO_BOOL:
    LEA     INPUT_BUFFER, A1
    MOVE.B  (A1)+, D1
    CMP.B   #'T', D1
    BEQ     CHECK_TRUE
    CMP.B   #'F', D1
    BEQ     CHECK_FALSE
    BRA     INVALID_INPUT
CHECK_TRUE:
    MOVE.B  (A1)+, D1
    CMP.B   #'R', D1
    BNE     INVALID_INPUT
    MOVE.B  (A1)+, D1
    CMP.B   #'U', D1
    BNE     INVALID_INPUT
    MOVE.B  (A1)+, D1
    CMP.B   #'E', D1
    BNE     INVALID_INPUT
    MOVE.L  #-1, D2
    CLR.B   D1
    RTS
CHECK_FALSE:
    MOVE.B  (A1)+, D1
    CMP.B   #'A', D1
    BNE     INVALID_INPUT
    MOVE.B  (A1)+, D1
    CMP.B   #'L', D1
    BNE     INVALID_INPUT
    MOVE.B  (A1)+, D1
    CMP.B   #'S', D1
    BNE     INVALID_INPUT
    MOVE.B  (A1)+, D1
    CMP.B   #'E', D1
    BNE     INVALID_INPUT
    CLR.L   D2
    CLR.B   D1
    RTS
CR           EQU     $0D
LF           EQU     $0A
TRUE_MSG     DC.B    'TRUE',CR,LF, 0
FALSE_MSG    DC.B    'FALSE',CR,LF, 0
ERROR_MSG    DC.B    'ERROR',CR,LF, 0
NEWLINE      DC.B    ' ',CR,LF, 0
BUFFER       DS.B    20
PROMPT_INT   DC.B    'Enter an integer: ', 0
PROMPT_BOOL  DC.B    'Enter a boolean (TRUE/FALSE): ', 0
INPUT_BUFFER DS.B    100
    END    START
