grammar MapResult;

file: header? line*;
header: headerline;

headerline: '['? entry (',' entry)* ']'? '\r'? '\n'?;

line: '<' keys ':' value '>' '\r'? '\n'?;
keys: '[' key (',' key)* ']';
key: TEXT;
value: ' '? '['? TEXT (','' '? TEXT )* ' '? ']'? ' '?;


entry : TEXT;
TEXT: ~[,\n\r"[\]<>:]+ ;
WS: [ \n\t\r]+ -> skip;