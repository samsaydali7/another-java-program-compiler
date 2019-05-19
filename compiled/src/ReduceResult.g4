grammar ReduceResult;

file: header? line+;
header: headerline;

headerline: '['? entry (','' '? entry)* ']'? '\r'? '\n';

line: '<' keys ':' value '>' '\r'? '\n'?;
keys: '[' key (','' '? key)* ']';
key: IDENT;
value: simple
     | object
     | array
     ;
simple: IDENT;
object: '{' objectEntry (','' '? objectEntry)* '}';
objectEntry : objectKey ':' objectValue;
objectKey : IDENT;
objectValue : IDENT;
array: '[' arrayElemnt (','' '? arrayElemnt)* ']';
arrayElemnt: '[' element (','' '? element)* ']';
element : IDENT;
entry : IDENT;


IDENT : ~[,\n\r"[\]<>:{}]+;
WS: [ \n\t\r]+ -> skip;

