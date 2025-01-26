*-----------------------------------------------------------
* Title      : Codi Ensamblador 68K
* Written by : Marc Llobera Villalonga
* Description: Codi ensamblador generat per el compilador
*              de Marc Llobera per a la pràctica de
*              l'assignatura de Compiladors.
*-----------------------------------------------------------
    ORG    $1000
START:                  ; first instruction of program


    ; goto e2
    BRA       E2

    ; e1
E1:

    ; t4 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 16(A6)

    ; t5 = t4
    MOVE.L    16(A6), D0
    MOVE.L    D0, 20(A6)

    ; t6 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 24(A6)

    ; if t2 != t6 then goto e3
    MOVE.L    8(A6), D0
    MOVE.L    24(A6), D1
    CMP.L     D1, D0
    BNE       E3

    ; goto e4
    BRA       E4

    ; e3
E3:

    ; t7 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 28(A6)

    ; goto e5
    BRA       E5

    ; e4
E4:

    ; t7 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 28(A6)

    ; e5
E5:

    ; if t7 == 0 then goto e6
    MOVE.L    28(A6), D0
    MOVE.L    #0, D1
    CMP.L     D1, D0
    BEQ       E6

    ; t8 = t1 + t3
    MOVE.L    4(A6), D0
    MOVE.L    12(A6), D1
    ADD.L     D1, D0
    MOVE.L    D0, 32(A6)

    ; t9 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 36(A6)

    ; t10 = t8 - t9
    MOVE.L    32(A6), D0
    MOVE.L    36(A6), D1
    SUB.L     D1, D0
    MOVE.L    D0, 40(A6)

    ; t5 = t10
    MOVE.L    40(A6), D0
    MOVE.L    D0, 20(A6)

    ; goto e7
    BRA       E7

    ; e6
E6:

    ; t11 = t1 + t3
    MOVE.L    4(A6), D0
    MOVE.L    12(A6), D1
    ADD.L     D1, D0
    MOVE.L    D0, 44(A6)

    ; t12 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 48(A6)

    ; t13 = t11 + t12
    MOVE.L    44(A6), D0
    MOVE.L    48(A6), D1
    ADD.L     D1, D0
    MOVE.L    D0, 52(A6)

    ; t5 = t13
    MOVE.L    52(A6), D0
    MOVE.L    D0, 20(A6)

    ; e7
E7:

    ; rtrn t5
    MOVE.L    20(A6), D0
    RTS

    ; e2
E2:

    ; t14 = 100
    MOVE.L    #100, D0
    MOVE.L    D0, 56(A6)

    ; t15 = t14
    MOVE.L    56(A6), D0
    MOVE.L    D0, 60(A6)

    ; t16 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 64(A6)

    ; t17 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 68(A6)

    ; in(t18)
    MOVE.L    68(A6), D0
    JSR       VALIDATE_INPUT
    MOVE.L    D1, 72(A6)

    ; t19 = t16 - t18
    MOVE.L    64(A6), D0
    MOVE.L    72(A6), D1
    SUB.L     D1, D0
    MOVE.L    D0, 76(A6)

    ; t20 = t19
    MOVE.L    76(A6), D0
    MOVE.L    D0, 80(A6)

    ; t21 = 2
    MOVE.L    #2, D0
    MOVE.L    D0, 84(A6)

    ; in(t22)
    MOVE.L    84(A6), D0
    JSR       VALIDATE_INPUT
    MOVE.L    D1, 88(A6)

    ; t23 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 92(A6)

    ; t24 = t22
    MOVE.L    88(A6), D0
    MOVE.L    D0, 96(A6)

    ; if t24 == -1 then goto e8
    MOVE.L    96(A6), D0
    MOVE.L    #-1, D1
    CMP.L     D1, D0
    BEQ       E8

    ; t25 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 100(A6)

    ; goto e9
    BRA       E9

    ; e8
E8:

    ; t26 = t23
    MOVE.L    92(A6), D0
    MOVE.L    D0, 104(A6)

    ; if t26 == -1 then goto e10
    MOVE.L    104(A6), D0
    MOVE.L    #-1, D1
    CMP.L     D1, D0
    BEQ       E10

    ; t25 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 100(A6)

    ; goto e9
    BRA       E9

    ; e10
E10:

    ; t25 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 100(A6)

    ; e9
E9:

    ; t27 = t25
    MOVE.L    100(A6), D0
    MOVE.L    D0, 108(A6)

    ; t29 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 116(A6)

    ; t30 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 120(A6)

    ; t31 = t29
    MOVE.L    116(A6), D0
    MOVE.L    D0, 124(A6)

    ; goto e12
    BRA       E12

    ; e11
E11:

    ; t32 = t30
    MOVE.L    120(A6), D0
    MOVE.L    D0, 128(A6)

    ; t33 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 132(A6)

    ; t31 = t31 + t33
    MOVE.L    124(A6), D0
    MOVE.L    132(A6), D1
    ADD.L     D1, D0
    MOVE.L    D0, 124(A6)

    ; if t31 >= t32 then goto e13
    MOVE.L    124(A6), D0
    MOVE.L    128(A6), D1
    CMP.L     D1, D0
    BGE       E13

    ; e12
E12:

    ; t34 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 136(A6)

    ; t35 = t31
    MOVE.L    124(A6), D0
    MOVE.L    D0, 140(A6)

    ; out(t35 :: t34)
    MOVE.L    136(A6), D0
    MOVE.L    140(A6), D1
    CMP.L     #1, D0
    BNE       E16
    JSR       PRINT_INTEGER
    BRA       E14
E16:
    CMP.L     #2, D0
    BNE       E17
    JSR       PRINT_BOOLEAN
    BRA       E14
E17:
    LEA       ERROR_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
E14:

    ; t36 = t20
    MOVE.L    80(A6), D0
    MOVE.L    D0, 144(A6)

    ; t37 = t27
    MOVE.L    108(A6), D0
    MOVE.L    D0, 148(A6)

    ; t38 = t15
    MOVE.L    60(A6), D0
    MOVE.L    D0, 152(A6)

    ; t1 = t38
    MOVE.L    152(A6), D0
    MOVE.L    D0, 4(A6)

    ; t2 = t37
    MOVE.L    148(A6), D0
    MOVE.L    D0, 8(A6)

    ; t3 = t36
    MOVE.L    144(A6), D0
    MOVE.L    D0, 12(A6)

    ; t39 = e1()
    JSR       E1
    MOVE.L    D0, 156(A6)

    ; t40 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 160(A6)

    ; t41 = t39
    MOVE.L    156(A6), D0
    MOVE.L    D0, 164(A6)

    ; out(t41 :: t40)
    MOVE.L    160(A6), D0
    MOVE.L    164(A6), D1
    CMP.L     #1, D0
    BNE       E18
    JSR       PRINT_INTEGER
    BRA       E15
E18:
    CMP.L     #2, D0
    BNE       E19
    JSR       PRINT_BOOLEAN
    BRA       E15
E19:
    LEA       ERROR_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
E15:

    ; goto e11
    BRA       E11

    ; e13
E13:

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
