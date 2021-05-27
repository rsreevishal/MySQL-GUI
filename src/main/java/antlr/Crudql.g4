
/*  Grammer
    tablename =                         | To view all data in the table
    tablename = id                      | To view data with primary key(id) in the table
    tablename + ('val1','val2', ...)    | To add data in the table
    tablename ^ (id,'val1','val2', ...) | To update data with primary key(id) in the table
    tablename - id                      | To delete data with primary key(id) in the table
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
       
expr: add | update | delete | view | viewAll | colView | colViewAll | storeColView;

/* Tokens */

LIST: '(' TEXT (',' TEXT)* ')' ;

ULIST: '(' UID (',' TEXT)* ')' ;
    
ID: [a-z][a-zA-Z0-9_]* ;

VAR: '$'[a-z][a-zA-Z0-9_]*;

TEXT: '\'' ~('\r' | '\n')+ '\'';

UID: [0-9]+ ;

WS : [ \t\n]+ -> skip ;
