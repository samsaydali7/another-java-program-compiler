grammar ShuffleResult;

file: header? line*;
header: headerline;

headerline: '['? entry (',' entry)* ']'? '\r'? '\n';

line: '<' keys ':' values '>' '\r'? '\n'?;
keys: '[' key (',' key)* ']';
values: '[' value (','' '? value)* ']';
key: TEXT;
value:
      arrayValue
     | TEXT;
arrayValue : '[' arrayEntry (',' arrayEntry)* ']';
arrayEntry : TEXT;
entry : TEXT;
TEXT: ~[,\n\r"[\]<>:]+ ;