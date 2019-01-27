
grammar ToyLang;

module : functions? EOF;

functions : function+;

function : FUNC type ID params scope;

params : '(' ')' //no params
       | '(' params_list ')'; //params

params_list : params_list ',' param
            | param
            ;

param : type ID;

scope : '{' statements '}' //statements
      | '{' '}' ; //no statements

statements : statement+ ;

statement : assignment SEMI     //Variable Assignment
          | control_statement   //Control Statement
          | loop_statement      //LOOP BRUDDAH
          | SEMI                //Empty Statement
          | expression SEMI     //Expression
          ;

loop_statement : WHILE expression scope //conditional loop
               | LOOP scope  //infinite LOOP
               ;

//currently only if, maybe add switches
control_statement : if_statement;

if_statement : IF expression scope else_clause
             | IF expression scope;

else_clause: ELSEIF expression scope else_clause
           | ELSE scope;

assignment : LET ID ASSIGNOP expression
           | ID ASSIGNOP expression;

func_call : ID args;

args : '(' arg_list ')'
     | '(' ')'; //emptyArgs

arg_list : arg_list ',' ID
         | ID
         ;

//Expression stuff
expression : expression OR and_exp
       | and_exp
       ;

and_exp : and_exp AND comparison_exp
        | comparison_exp
        ;

comparison_exp : comparison_exp EQUALS add_sub_exp
               | comparison_exp GREATER_THAN_OR_EQ add_sub_exp
               | comparison_exp LESS_THAN_OR_EQ add_sub_exp
               | comparison_exp LESS_THAN add_sub_exp
               | comparison_exp GREATER_THAN add_sub_exp
               | add_sub_exp
               ;

add_sub_exp : add_sub_exp '+' mult_div_exp
            | add_sub_exp '-' mult_div_exp
            | mult_div_exp
            ;

mult_div_exp : mult_div_exp ('*' | '/') pre_incr_decr
             | pre_incr_decr;

pre_incr_decr : ('++' | '--') post_incr_decr
              | post_incr_decr
              ;

post_incr_decr : parenthesized ('++' | '--')
               | parenthesized
               ;

parenthesized : '(' expression ')'
              | BOOLEAN
              | INT
              | func_call
              | member_access
              | variable;

member_access : ID '.' ID;

variable : ID ;

type : primitiveTypes
     | ID
     ;


primitiveTypes : 'bool' #bool
               | 'int' #int
               | 'string' #str
               ;
// Integers
INT : (MINUS)?[0-9]+;

// Keywords
WHILE : 'while';
FUNC : 'fun';
RETURN : 'ret';
LOOP : 'loop';
IF : 'if';
ELSE: 'else';
ELSEIF : 'else if';

LET : 'let';

//primitive type names
BOOL_TYPE : 'bool';
INT_TYPE : 'int';
STRING_TYPE : 'string';

// Booleans
BOOLEAN : (YES | NO | ON | OFF | TRUE | FALSE);
YES : 'yes';
NO : 'no';
ON : 'on';
OFF : 'off';
TRUE : 'true';
FALSE : 'false';

//Assignment stuff
ASSIGNOP : (EQ | PEQ | MEQ | TEQ | DIVEQ | MODEQ);
EQ : '=' ;
PEQ : '+=';
TEQ : '*=';
MEQ : '-=';
DIVEQ : '/=';
MODEQ : '%=';


//Common operators
OR : '||';
AND : '&&';
EQUALS : '==';
LESS_THAN : '<';
GREATER_THAN : '>';
LESS_THAN_OR_EQ : '<=';
GREATER_THAN_OR_EQ : '>=';

// Math symbols
MULT : '*';
DIV : '/';
MINUS : '-';
PLUS : '+';

// Misc. brackets and puntuation
SEMI : ';';

// good ol' fashioned comments
LINE_COMMENT : '//' ~[\r\n]*? '\r'? '\n' -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;

// Identifiers
ID : [A-Za-z][A-Za-z0-9\-]* ; //match identifiers
WS : [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines