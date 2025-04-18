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

    ; t5 = t3 + t4
    MOVE.L    12(A6), D0 
    MOVE.L    16(A6), D1 
    ADD.L     D1, D0 
    MOVE.L    D0, 20(A6) 

    ; rtrn t5 
    MOVE.L    20(A6), D0
    RTS 

    ; e2
E2: 

    ; goto e4
    BRA       E4 

    ; e3
E3: 

    ; t6 = 1
    MOVE.L    #1, D0 
    MOVE.L    D0, 24(A6) 

    ; t7 = 2
    MOVE.L    #2, D0 
    MOVE.L    D0, 28(A6) 

    ; t8 = t7
    MOVE.L    28(A6), D0 
    MOVE.L    D0, 32(A6) 

    ; t9 = t6
    MOVE.L    24(A6), D0 
    MOVE.L    D0, 36(A6) 

    ; t3 = t9
    MOVE.L    36(A6), D0 
    MOVE.L    D0, 12(A6) 

    ; t4 = t8
    MOVE.L    32(A6), D0 
    MOVE.L    D0, 16(A6) 

    ; t10 = e1() 
    JSR       E1 
    MOVE.L    D0, 40(A6) 

    ; t11 = 1
    MOVE.L    #1, D0 
    MOVE.L    D0, 44(A6) 

    ; t12 = t10
    MOVE.L    40(A6), D0 
    MOVE.L    D0, 48(A6) 

    ; out(t12 :: t11) 
    MOVE.L    44(A6), D0 
    MOVE.L    48(A6), D1 
    CMP.L     #1, D0 
    BNE       E6   
    JSR       PRINT_INTEGER   
    BRA       E5   
E6:   
    CMP.L     #2, D0 
    BNE       E7   
    JSR       PRINT_BOOLEAN   
    BRA       E5   
E7:   
    LEA       ERROR_MSG, A1 
    MOVE.W  #14, D0 
    TRAP    #15 
E5: 

    ; t13 = -1
    MOVE.L    #-1, D0 
    MOVE.L    D0, 52(A6) 

    ; rtrn t13 
    MOVE.L    52(A6), D0
    RTS 

    ; e4
E4: 

    ; t14 = e3() 
    JSR       E3 
    MOVE.L    D0, 56(A6) 

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
