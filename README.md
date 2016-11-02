﻿# PROPJavaTokenizer

This is the A/B EBNF form:

block = '{' , stmts , '}' ; </br>
stmts = [ assign , stmts ] ; </br>
assign = id , '=' , expr , ';' ; </br>
expr = term , [ ( '+' | '-' ) , expr ] ; </br>
term = factor , [ ( '*' | '/' ) , term ] ; </br>
factor = int | id | '(' , expr , ')' ; </br>
                     
And this is the C/D/E EBNF form:
                     
assign = id , '=' , expr , ';' ; </br>
expr = term , [ ( '+' | '-' ) , expr ] ; </br>
term = factor , [ ( '*' | '/') , term] ; </br>
factor = int | '(' , expr , ')' ; </br>
