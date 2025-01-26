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

    ; t1 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 4(A7)

    ; t2 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 8(A7)

    ; t3 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 12(A7)

    ; if -3 == t3 then goto e3
    MOVE.L    #-3, D0
    MOVE.L    12(A7), D1
    CMP.L     D1, D0
    BEQ       E3

    ; goto e4
    BRA       E4

    ; e3
E3:

    ; t4 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 16(A7)

    ; goto e5
    BRA       E5

    ; e4
E4:

    ; t4 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 16(A7)

    ; e5
E5:

    ; if t4 == 0 then goto e6
    MOVE.L    16(A7), D0
    MOVE.L    #0, D1
    CMP.L     D1, D0
    BEQ       E6

    ; t5 = -6
    MOVE.L    #-6, D0
    MOVE.L    D0, 20(A7)

    ; t6 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 24(A7)

    ; t7 = -6 - t6
    MOVE.L    #-6, D0
    MOVE.L    24(A7), D1
    SUB.L     D1, D0
    MOVE.L    D0, 28(A7)

    ; t2 = t7
    MOVE.L    28(A7), D0
    MOVE.L    D0, 8(A7)

    ; goto e7
    BRA       E7

    ; e6
E6:

    ; t8 = -6
    MOVE.L    #-6, D0
    MOVE.L    D0, 32(A7)

    ; t9 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 36(A7)

    ; t10 = -6 + t9
    MOVE.L    #-6, D0
    MOVE.L    36(A7), D1
    ADD.L     D1, D0
    MOVE.L    D0, 40(A7)

    ; t2 = t10
    MOVE.L    40(A7), D0
    MOVE.L    D0, 8(A7)

    ; e7
E7:

    ; rtrn t2
    MOVE.L    8(A7), D0
    RTS

    ; e2
E2:

    ; t11 = 100
    MOVE.L    #100, D0
    MOVE.L    D0, 44(A7)

    ; t12 = 100
    MOVE.L    #100, D0
    MOVE.L    D0, 48(A7)

    ; t13 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 52(A7)

    ; in(t14)
    MOVE.L    #1, D0
    JSR       VALIDATE_INPUT
    MOVE.L    D1, 56(A7)

    ; t15 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 60(A7)

    ; t16 = t14 + t15
    MOVE.L    56(A7), D0
    MOVE.L    60(A7), D1
    ADD.L     D1, D0
    MOVE.L    D0, 64(A7)

    ; t17 = t16
    MOVE.L    64(A7), D0
    MOVE.L    D0, 68(A7)

    ; t18 = 2
    MOVE.L    #2, D0
    MOVE.L    D0, 72(A7)

    ; in(t19)
    MOVE.L    #2, D0
    JSR       VALIDATE_INPUT
    MOVE.L    D1, 76(A7)

    ; t20 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 80(A7)

    ; t21 = t19
    MOVE.L    76(A7), D0
    MOVE.L    D0, 84(A7)

    ; if t19 == -1 then goto e8
    MOVE.L    76(A7), D0
    MOVE.L    #-1, D1
    CMP.L     D1, D0
    BEQ       E8

    ; t22 = 0
    MOVE.L    #0, D0
    MOVE.L    D0, 88(A7)

    ; goto e9
    BRA       E9

    ; e8
E8:

    ; t23 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 92(A7)

    ; goto e10
    BRA       E10

    ; goto e9
    BRA       E9

    ; e10
E10:

    ; t22 = -1
    MOVE.L    #-1, D0
    MOVE.L    D0, 88(A7)

    ; e9
E9:

    ; t24 = t22
    MOVE.L    88(A7), D0
    MOVE.L    D0, 96(A7)

    ; t25 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 100(A7)

    ; t26 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 104(A7)

    ; t27 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 108(A7)

    ; goto e12
    BRA       E12

    ; e11
E11:

    ; t28 = 10
    MOVE.L    #10, D0
    MOVE.L    D0, 112(A7)

    ; t29 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 116(A7)

    ; t27 = t27 + t29
    MOVE.L    108(A7), D0
    MOVE.L    116(A7), D1
    ADD.L     D1, D0
    MOVE.L    D0, 108(A7)

    ; if t27 >= t28 then goto e13
    MOVE.L    108(A7), D0
    MOVE.L    112(A7), D1
    CMP.L     D1, D0
    BGE       E13

    ; e12
E12:

    ; t30 = t17 - t27
    MOVE.L    68(A7), D0
    MOVE.L    108(A7), D1
    SUB.L     D1, D0
    MOVE.L    D0, 120(A7)

    ; t17 = t30
    MOVE.L    120(A7), D0
    MOVE.L    D0, 68(A7)

    ; t31 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 124(A7)

    ; t32 = t27
    MOVE.L    108(A7), D0
    MOVE.L    D0, 128(A7)

    ; out(t32 :: 1)
    MOVE.L    #1, D0
    MOVE.L    128(A7), D1
    CMP.L     #1, D0
    BNE       E20
    JSR       PRINT_INTEGER
    BRA       E14
E20:
    CMP.L     #2, D0
    BNE       E21
    JSR       PRINT_BOOLEAN
    BRA       E14
E21:
    LEA       ERROR_MSG, A1
    MOVE.W  #14, D0
    TRAP    #15
E14:

    ; t33 = t17
    MOVE.L    68(A7), D0
    MOVE.L    D0, 132(A7)

    ; t34 = t22
    MOVE.L    88(A7), D0
    MOVE.L    D0, 136(A7)

    ; t35 = 100
    MOVE.L    #100, D0
    MOVE.L    D0, 140(A7)

    ; t36 = e1()
    JSR       E1
    MOVE.L    D0, 144(A7)

    ; t37 = 1
    MOVE.L    #1, D0
    MOVE.L    D0, 148(A7)

    ; t38 = t36
    MOVE.L    144(A7), D0
    MOVE.L    D0, 152(A7)

    ; out(t38 :: 1)
    MOVE.L    #1, D0
    MOVE.L    152(A7), D1
    CMP.L     #1, D0
    BNE       E22
    JSR       PRINT_INTEGER
    BRA       E15
E22:
    CMP.L     #2, D0
    BNE       E23
    JSR       PRINT_BOOLEAN
    BRA       E15
E23:
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
