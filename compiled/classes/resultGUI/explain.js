explain_output = `
Loading relation : test/students
	Mapping C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/test/students/1.csv
	Mapping C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/test/students/2.csv
	Shuffling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline1/map/result1.txt
	Shuffling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline1/map/result2.txt
	Reducing C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline1/shuffle/result1.txt

Loading relation : test/students
	Mapping C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/test/students/1.csv
	Mapping C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/test/students/2.csv
	Shuffling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline2/map/result1.txt
	Shuffling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline2/map/result2.txt
	Reducing C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline2/shuffle/result1.txt

Assembler began
	Assembling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline1/reduce/result1.txt
	Assembling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline2/reduce/result1.txt

Loading relation : ./temp/assemble/assembleRelation1
	Mapping C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/assemble/assembleRelation1/result1.csv
	Shuffling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline1/map/result1.txt
	Reducing C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline1/shuffle/result1.txt

Assembler began
	Assembling C:/Users/Samer/Documents/GitHub/AnotherJavaProjectCompiler/compiled/classes/./temp/pipelines/pipeline1/reduce/result1.txt
	Assembler selecting distinct rows

5 mappers, 3 shufflers, 3 reducers, 2 assemblers
`;