
grammar ToyLang;

//@header {
//package net.travitz.lang.toy;
//}

module : functions? EOF;
//trait_decl : 'trait' ID trait_block;

//TODO type declarations and interfaces/extensions
functions : function+;

function : FUNC (type | VOID) ID params scope;

params : LEFT_PAREN RIGHT_PAREN              //no params
       | LEFT_PAREN params_list RIGHT_PAREN; //params

params_list : params_list COMMA_SEP param
            | param
            ;

param : type ID;

scope : LEFT_CURLY statements RIGHT_CURLY //statements
      | LEFT_CURLY RIGHT_CURLY ;          //no statements

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

if_statement : IF (assignment | expression) scope else_clause
             | IF (assignment | expression) scope
             ;

else_clause: ELSEIF expression scope else_clause?
           | ELSE scope;

//may need to put a : in between type and ID ?
assignment : LET MUT? type? assignable ASSIGN_OP expression //Declaration
           | assignable ASSIGN_OP expression //regular assignment
           ;

//TODO double check this logic
assignable : ID assignable_next*;

//TODO also give this operator a question mark op for conditional assignment
assignable_next : (LEFT_SQUARE | LEFT_ARR_SAFE) expression RIGHT_SQUARE // do some research, make sure you want ?[ operator
                | DOT_OP ID ;


args : LEFT_PAREN arg_list RIGHT_PAREN
     | LEFT_PAREN RIGHT_PAREN; //emptyArgs

arg_list : arg_list COMMA_SEP expression
         | expression
         ;

//Expression stuff
expression : expression OR and_exp
       | and_exp
       ;

and_exp : and_exp AND comparison_exp
        | comparison_exp
        ;

comparison_exp : comparison_exp VALUE_COMPARISON add_sub_exp
               | add_sub_exp
               ;

add_sub_exp : add_sub_exp (PLUS | MINUS) mult_div_exp
            | mult_div_exp
            ;

mult_div_exp : mult_div_exp (MULT | DIV | MOD) pre_incr_decr
             | pre_incr_decr;

pre_incr_decr : (INCR | DECR) post_incr_decr
              | NOT_FORCE_UNWRAP post_incr_decr
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
array_literal : LEFT_SQUARE arr_elements RIGHT_SQUARE;

arr_elements : arr_elements COMMA_SEP arr_element
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
                | NOT_FORCE_UNWRAP
                | end_elvis
                ;

chained_end : end_func
            | end_array
            | end_member
            | NOT_FORCE_UNWRAP
            | end_elvis
            ;
literals : array_literal
           | STRING_LITERAL
           | BOOLEAN_LITERAL
           | INT_LITERAL
           | NADA;

end_func : DOT_OP ID args chained_end? ;

end_array : DOT_OP ID (LEFT_ARR_SAFE | LEFT_SQUARE) expression RIGHT_SQUARE chained_end?;

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
ASSIGN_OP : (EQ | PEQ | MEQ | TEQ | DIVEQ | MODEQ);

VALUE_COMPARISON: EQUALS
                | NOT_EQUAL
                | LESS_THAN
                | LESS_THAN_OR_EQ
                | GREATER_THAN
                | GREATER_THAN_OR_EQ;
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
DOT_UNSAFE : '!.';


ELVIS : '?|';

//TODO double exclamation marks look very awkward, hopefully find a way to solve following problem.
//TODO this needs looked at 'if (id! = 5)' vs 'if (id != 5)' ambiguous maybe?
//TODO Maybe use hidden channel and context sensitivity?

//TODO in scenario where unwrap is merely '!'
// foo != bar, good
// foo! = bar, bad

// foo! == bar, good
// foo != = bar, bad

NOT_FORCE_UNWRAP : '!';

//Math symbols
MULT : '*';
DIV : '/';
MINUS : '-';
PLUS : '+';
MOD : '%';

//Misc. brackets and puntuation
SEMI : ';';
STRING_DEL : '\'';
COMMA_SEP : ',';
LEFT_PAREN : '(';
RIGHT_PAREN : ')';
LEFT_SQUARE : '[';
LEFT_ARR_SAFE : '?[';
RIGHT_SQUARE : ']';
LEFT_CURLY : '{';
RIGHT_CURLY : '}';
OPTIONAL_IND : '?';

//good ol' fashioned comments
LINE_COMMENT : '//' ~[\r\n]*? '\r'? '\n' -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;


//Identifiers
ID : [A-Za-z_][A-Za-z0-9\-_]* ; //match identifiers
WS : [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines