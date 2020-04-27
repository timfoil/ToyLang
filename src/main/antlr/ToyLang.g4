
grammar ToyLang;

//@header {
//package net.travitz.lang.toy;
//}

module : functions? EOF;
//trait_decl : 'trait' ID trait_block;

//TODO type declarations and interfaces/extensions
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

//A ret may or may not have something following it
return_statement : RETURN expression? ;

loop_statement : FOR assignment SEMI expression SEMI expression scope //classic for
               | WHILE expression scope                               //conditional loop
               | LOOP scope                                           //infinite LOOP
               ;

//currently only if, maybe add switches
control_statement : if_statement;

if_statement : IF expression scope else_clause //(assignment | expression) scope else_clause
             | IF expression scope else_clause //(assignment | expression) scope
             ;

else_clause: ELSEIF expression scope else_clause?
           | ELSE scope;

assignment : LET MUT? type? assignable ASSIGNOP expression //may need to put a : in between type and ID ?
           | assignable ASSIGNOP expression
           ;
//TODO double check this logic
assignable : ID assignable_next?;

//TODO also give this operator a question mark op for conditional assignment
assignable_next : ('[' | '?[') expression ']' assignable_next?
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
              | literals
              | built_in_func_call
              | ID chained_end_new?
              ;

//Short and simple/messy way to create an array, this will likely require more thought in the future?
array_literal : '[' arr_elements ']';

arr_elements : arr_elements ',' arr_element
             | arr_element
             ;

arr_element : expression;

//TODO give this the option for this question mark op too? '?['
array_access : ('[' | '?[') expression ']' chained_end?;

built_in_func_call : primitive_types DOT_OP ID args chained_end?; //built in function

func_call : args chained_end?;  //local args

member_access : DOT_OP ID args chained_end? //member function
              | DOT_OP ID chained_end; //member value

chained_end_new : func_call
                | member_access
                | array_access
                | FORCE_UNWRAP
                | end_elvis
                ;

chained_end : end_func
            | end_array
            | end_member
            | FORCE_UNWRAP
            | end_elvis
            ;
literals : array_literal
           | STRING_LITERAL
           | BOOLEAN_LITERAL
           | INT_LITERAL
           | NADA;

end_func : DOT_OP ID args chained_end? ;

end_array : DOT_OP ID '?[' expression ']' chained_end?;

end_member : DOT_OP ID chained_end?;

end_elvis : ELVIS expression;


type : array OPTIONAL_IND?
     | primitive_types OPTIONAL_IND?
     | ID OPTIONAL_IND?
     ;

//A question mark inside of the brackets indicates an optional array reference
array : LEFT_SQUARE MUT? type RIGHT_SQUARE;

primitive_types : 'bool' #bool
                | 'int' #int
                | 'string' #str
                ;

STRING_LITERAL : STRING_DEL STRING_CONTENT;

//String Things
 fragment STRING_CONTENT :
            | STRING_CHARACTER STRING_CONTENT
            | ESCAPE_SEQUENCE STRING_CONTENT
            | STRING_DEL
            ;


 fragment STRING_CHARACTER : ~[\\\r\n'];
 fragment ESCAPE_SEQUENCE : '\\' ('n' | '\\');

//Integers
INT_LITERAL : (MINUS)?[0-9]+;

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
BOOLEAN_LITERAL : (YES | NO | ON | OFF | TRUE | FALSE);
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

DOT_OP : (DOT | DOT_SAFE | DOT_UNSAFE);
DOT : '.';
DOT_SAFE : '?.';
DOT_UNSAFE : '!!.';

ELVIS : '?|';
FORCE_UNWRAP: '!!';

//Math symbols
MULT : '*';
DIV : '/';
MINUS : '-';
PLUS : '+';

//Misc. brackets and puntuation
SEMI : ';';
STRING_DEL : '\'';
COMMA_SEP : ',';
LEFT_PAREN : '(';
RIGHT_PAREN : ')';
LEFT_SQUARE : '[';
LEFTW_ARR_SAFE : '?[';
RIGHT_SQUARE : ']';
OPTIONAL_IND : '?';

//good ol' fashioned comments
LINE_COMMENT : '//' ~[\r\n]*? '\r'? '\n' -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;


//Identifiers
ID : [A-Za-z_][A-Za-z0-9\-_]* ; //match identifiers
WS : [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines