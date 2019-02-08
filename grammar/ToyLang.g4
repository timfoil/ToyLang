
grammar ToyLang;

module : functions? EOF;
//trait_decl : 'trait' ID trait_block;

functions : function+;

function : FUNC (type | VOID) ID params scope;

params : '(' ')'              //no params
       | '(' params_list ')'; //params

params_list : params_list ',' param
            | param
            ;

param : type ID;

scope : '{' statements '}' //statements
      | '{' '}' ;          //no statements

statements : statement+ ;

statement : assignment SEMI       //Variable Assignment
          | control_statement     //Control Statement
          | loop_statement        //LOOP BRUDDAH
          | return_statement SEMI
          | SEMI                  //Empty Statement
          | expression SEMI       //Expression
          ;

return_statement : RETURN expression ;

loop_statement : FOR assignment SEMI expression SEMI expression scope //classic for
               | WHILE expression scope                               //conditional loop
               | LOOP scope                                           //infinite LOOP
               ;

//currently only if, maybe add switches
control_statement : if_statement;

if_statement : IF (assignment | expression) scope else_clause
             | IF (assignment | expression) scope
             ;

else_clause: ELSEIF expression scope else_clause?
           | ELSE scope;

assignment : LET MUT? type? assignable ASSIGNOP expression //may need to put a : in between type and ID ?
           | assignable ASSIGNOP expression
           ;

assignable : ID assignable_next?;

assignable_next : '[' expression ']' assignable_next?
                | DOT_OP ID assignable_next?;


args : '(' arg_list ')'
     | '(' ')'; //emptyArgs

arg_list : arg_list ',' expression
         | expression
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

mult_div_exp : mult_div_exp ('*' | '/' | '%') pre_incr_decr
             | pre_incr_decr;

pre_incr_decr : (INCR | DECR) post_incr_decr
              | '!' post_incr_decr
              | post_incr_decr
              ;

post_incr_decr : parenthesized (INCR | DECR)
               | parenthesized
               ;

parenthesized : '(' expression ')'
              | NADA
              | BOOLEAN
              | INT
              | func_call
              | member_access
              | array_access
              | array_literal
              | ID (FORCE_UNWRAP | end_elvis)?
              ;

//Short and simple/meessy way to create an array, this will likely require more thought in the future?
array_literal : '[' arr_elements ']';

arr_elements : arr_elements ',' arr_element
             | arr_element
             ;

arr_element : expression;

array_access : ID '[' expression ']' chained_end?
             ;

func_call : primitive_types DOT_OP ID args chained_end? //built in function
          | ID args chained_end?                        //local args
          ;

member_access : ID DOT_OP ID chained_end?;

chained_end : end_func
            | end_array
            | end_member
            | FORCE_UNWRAP
            | end_elvis
            ;


end_func : DOT_OP ID args chained_end? ;

end_array : DOT_OP ID '[' expression ']' chained_end?;

end_member : DOT_OP ID chained_end?;

end_elvis : ELVIS expression;


type : array OPTIONAL_TYPE?
     | primitive_types OPTIONAL_TYPE?
     | ID OPTIONAL_TYPE?
     ;

//A question mark inside of the brackets indicates an optional array reference
array : LEFT_SQUARE MUT? type RIGHT_SQUARE;

primitive_types : 'bool' #bool
                | 'int' #int
                | 'string' #str
                ;
//Integers
INT : (MINUS)?[0-9]+;

//Keywords
WHILE : 'while';
FOR : 'for';
FUNC : 'fun';
RETURN : 'ret';
LOOP : 'loop';
IF : 'if';
ELSE: 'else';
ELSEIF : 'else if';
VOID : 'void';
NADA : 'nada';
LET : 'let'; //variable decl
MUT : 'mut'; //mutable

//primitive type names
BOOL_TYPE : 'bool';
INT_TYPE : 'int';
STRING_TYPE : 'string';

//Booleans
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
NOT_EQUAL : '!=';
LESS_THAN : '<';
GREATER_THAN : '>';
LESS_THAN_OR_EQ : '<=';
GREATER_THAN_OR_EQ : '>=';
INCR : '++';
DECR : '--';
DOT : '.';
DOT_SAFE : '?.';
DOT_UNSAFE : '!!.';
DOT_OP : (DOT | DOT_SAFE | DOT_UNSAFE);
ELVIS : '?|';
FORCE_UNWRAP: '!!';

//Math symbols
MULT : '*';
DIV : '/';
MINUS : '-';
PLUS : '+';

//Misc. brackets and puntuation
SEMI : ';';
COMMA_SEP : ',';
LEFT_PAREN : '(';
RIGHT_PAREN : ')';
LEFT_SQUARE : '[';
RIGHT_SQUARE : ']';
OPTIONAL_TYPE : '?';

//good ol' fashioned comments
LINE_COMMENT : '//' ~[\r\n]*? '\r'? '\n' -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;

//Identifiers
ID : [A-Za-z][A-Za-z0-9\-]* ; //match identifiers
WS : [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines