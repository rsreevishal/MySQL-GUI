
/*  Grammer
    tablename =                         | To view all data in the table
    tablename = id                      | To view data with primary key(id) in the table
    tablename + ('val1','val2', ...)    | To add data in the table
    tablename ^ (id,'val1','val2', ...) | To update data with primary key(id) in the table
    tablename - id                      | To delete data with primary key(id) in the table
    tablename.colname =                 | To view all colname data in the table
    tablename.colname = 1               | To view colname data with primary key(id) in the table
    $var = (tablename.colname = 1)      | To store the value of returned data
    table += ('$var','val1'...)         | To add stored data
*/

grammar Crudql;

@header {
	package antlr;
}

// Start variable
prog: (expr)+ EOF ; 

add: ID '+' LIST ;

update: ID '^' ULIST ;

delete: ID '-' UID ;

view: ID '=' UID ;

viewAll: ID '=' ;

colView: ID '.' ID '=' UID ;

colViewAll: ID '.' ID '=' ;

storeColView: VAR '=' '(' colView ')';

createForm: 'CREATE FORM' ID '[' formInput+ ']';

createFormReport: 'CREATE VIEW' ID 'FOR' ID '[' condition+ ']';

formInput: ID inputType LIST;

condition: ID operator TEXT;

operator: '=' | '>' | '<' | 'HAS';

inputType: 'TEXT' | 'NUMBER' | 'TEXTAREA' | 'RADIO' | 'CHECKBOX' | 'PASSWORD' | 'EMAIL' | 'LINK';
       
expr: add | update | delete | view | viewAll | colView | colViewAll | storeColView | createForm | createFormReport;

/* Tokens */

LIST: '(' TEXT (',' TEXT)* ')' ;

ULIST: '(' UID (',' TEXT)* ')' ;
    
ID: [a-zA-Z][a-zA-Z0-9_]* ;

VAR: '$'[a-z][a-zA-Z0-9_]*;

TEXT: '\'' ~('\r' | '\n' | '\'' | '(' | ')')+ '\'';

UID: [0-9]+ ;

WS : [ \t\n]+ -> skip ;
