package Main;
import FileStream.DataSet.*;
import FileStream.FileHelper;
import MapReduce.Assemple.Assembler;
import MapReduce.FileConfig;
import MapReduce.Map.*;
import MapReduce.Reduce.Aggrigation.*;
import MapReduce.Reduce.Aggrigation.Complex.*;
import MapReduce.Reduce.Analytical.*;
import MapReduce.Reduce.Reducer;
import MapReduce.Reduce.Window;
import MapReduce.Shuffle.Shuffler;
import MapReduce.Util.Compare.NumericComparator;
import MapReduce.Util.Compare.SortComparator;
import MapReduce.Util.DateParse.DateParser;
import MapReduce.Util.DateParse.OutFormat;
import MapReduce.Util.Explain;
import javafx.util.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileHelper.deleteTemp();
        try {
            //testSimpleDataSetLoader();
            //Tester.testGroupByExtract();
            //testNewJoin();
            //testSelfJoin();
            //Tester.testAssempler();
            //testDistinctInAggregation();
            //testSelectList_GroupByNothing();
            //testSummerize();
            //testOrderBy();
            //Tester.testHaving();
            //Tester.testDistinct();
            testAssignQueryResultToVar_andExplain();

            // Row Functions ////
            //testDateParser();

            ///// Analytical Functions //////
            //testAnalytical();
            //testAnalyticalWithOrderBy();
            //testRankFunction();
            //testAnalyticalWithOrderByAndWindow();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void testSimpleDataSetLoader() throws Exception {
        /*
            SELECT people.department AS dep,AVG(salary) as avg
             FROM employees AS people
             Group By dep;
         */
        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                // Where dep = 1;
                //return this.getValue((ArrayList<String>) object,"dep").equals("1");
                return true;
            }
        };
        HashMap relationName = new HashMap(){{put("employees",new ArrayDeque(){{offer("people");}}); }};
        HashMap attrNames = new HashMap(){{put("people_department","dep");}};

        // Pipeline 1
        Grouper grouper1 = Grouper.make(new String[]{"dep"},new String[]{"dep"});
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,relationName,attrNames,new Pair<>("test\\employees","|"));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        // Pipeline 2
        Grouper grouper2 = Grouper.make(new String[]{"dep"},"people_salary");
        String pipeline2 = FileConfig.getNextPipeline();
        loader = new SimpleDataSetLoader(filter,grouper2,pipeline2,relationName,attrNames,new Pair<>("test\\employees","|"));
        loader.load();

        shuffler = new Shuffler(pipeline2);
        shuffler.shuffle();
        shuffler.finish();
        reducer = new Reducer(pipeline2,new Average());
        reducer.reduce();
        reducer.finish();

    }
    public static void testSummerize() throws Exception {
        /*
            SELECT people.department AS dep,SUMMERIZE(salary)
             FROM employees AS people
             Group By dep;
         */
        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };
        HashMap relationName = new HashMap(){{put("employees",new ArrayDeque(){{offer("people");}}); }};
        HashMap attrNames = new HashMap(){{put("people_department","dep");}};

        // Pipeline 1
        Grouper grouper1 = Grouper.make(new String[]{"dep"},new String[]{"dep"});
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,relationName,attrNames,new Pair<>("test\\employees","|"));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        // Pipeline 2
        Grouper grouper2 = Grouper.make(new String[]{"dep"},"people_salary");
        String pipeline2 = FileConfig.getNextPipeline();
        loader = new SimpleDataSetLoader(filter,grouper2,pipeline2,relationName,attrNames,new Pair<>("test\\employees","|"));
        loader.load();

        shuffler = new Shuffler(pipeline2);
        shuffler.shuffle();
        shuffler.finish();
        reducer = new Reducer(pipeline2,new Summarize());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(new ArrayList(){{add("dep");add("count");add("mean");
            add("std"); add("min"); add("q1"); add("median"); add("q2"); add("max"); }});
        assembler.groupingFlag = true;
        assembler.assemble();
        assembler.write();

        // These Line Should Be Added to each Function Tail
        Explain.getStatistics(); // Show Explain Statistics // Important to save result to GUI
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(FileConfig.getResultGUIIndexURI());
    }
    public static void testDistinctInAggregation() throws Exception {
        /*
            SELECT employees.department ,SUM(DISTINCT salary)
             FROM employees
             Group By employees.department;
         */
        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1
        Grouper grouper1 = Grouper.make(new String[]{"employees_department"},new String[]{"employees_department"});
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>("test\\employees","|"));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        // Pipeline 2
        Grouper grouper2 = Grouper.make(new String[]{"employees_department"},"employees_salary");
        String pipeline2 = FileConfig.getNextPipeline();
        loader = new SimpleDataSetLoader(filter,grouper2,pipeline2,new Pair<>("test\\employees","|"));
        loader.load();

        shuffler = new Shuffler(pipeline2);
        shuffler.shuffle();
        shuffler.finish();
        reducer = new Reducer(pipeline2,new Sum());
        reducer.distict = true; // <- Important
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(new ArrayList(){{add("department");add("Sum");}});
        assembler.groupingFlag = true;
        assembler.assemble();
        assembler.write();
    }

    public static void testAnalytical() throws Exception {
        /*

        SELECT id AS employee_id,
               department,
               salary,
               AVG(salary) OVER (PARTITION BY department) AS avg
        FROM   employees

         */

        /**
         * SAME AS
         *         SELECT id AS employee_id,
                     department,
                     salary,
                     AVG() AS avg
                     Group BY department
                     FROM   employees
         But groubingFlag = false in assembler;

         *
         */

    }
    public static void testAnalyticalWithOrderBy() throws Exception {
        /*

        SELECT department,name,salary
               AVG(salary) OVER (PARTITION BY department ORDER BY name [ASC]) AS runningAVG
        FROM   employees

         */

        /**
         *
         * This kind of query (That has order by in over) goes in tow phases
         * First Select department,salary,name then order by name the assemble and save relation
         *
         * then reload last assemble result and continue the work of partitioning and and calculate DENSE_RANK
         *
         * Very similar to sub query or assign query to variable
         */
        ArrayList assemblerHeaders = new ArrayList(){{add("department");
            add("name");add("salary");
        }};

        /**
         *  IF QUERY WAS
         *          SELECT department
                    AVG(salary) OVER (PARTITION BY department ORDER BY name [ASC]) AS runningAVG
                     FROM   employees

         * THEN header must be
         *         ArrayList assemblerHeaders = new ArrayList(){{add("department");
                     add("salary");add("name");
                   }};
         * Because we need those for outer query
         */

        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1
        //The Select List
        Grouper grouper1 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("Nothing");
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"employees_department"));
                list.add(this.getValue(row,"employees_name"));
                list.add(this.getValue(row,"employees_salary"));
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();

        // there is no AS names so called another constructor
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>("test\\employees","|"));
        // or it can be
        //SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1, (HashMap<String, String>) null,(HashMap<String, String>) null,new Pair<>("test\\employees","|"));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();

        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(assemblerHeaders);
        assembler.assemble();
        // SORT by Name
        assembler.orderBy("name", SortComparator.SortType.ASC,false); // <- Important
        /*
          If it was ORDER BY 2
          assembler.orderBy(new SortComparator(SortComparator.SortType.ASC,1,false)); 2-1 = 1;
         */
        assembler.sort(); // <- Very Important
        assembler.write();

        /**
         *
         *  SORTING OF RELATION ENDED HERE
         *
         */

        FileConfig.clearSubQuery();
        String lastRelation = FileConfig.lastAssembelRelation();

        ArrayList assemblerHeaders2 = new ArrayList(){{add("department");add("name");add("salary");add("runningAVG");

        }};

        Filter filter2 = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 2
        //The Select List
        Grouper grouper2 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"department"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"department"));
                list.add(this.getValue(row,"name"));
                list.add(this.getValue(row,"salary"));
                return list;
            }
        };
        String pipeline2 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader2 = new SimpleDataSetLoader(filter2,grouper2,pipeline2,new Pair<>(lastRelation,","));
        loader2.concatRelationName = false; // <- important if processing sub query or previews assemble result or join result
        loader2.load();
        Shuffler shuffler2 = new Shuffler(pipeline2);
        shuffler2.shuffle();
        shuffler2.finish();
        Reducer reducer2 = new Reducer(pipeline2,new DoNothing());
        // All SELECT LIST IN ANALYTICAL FUNCTIONS WITH ORDER BY HAS SELF WINDOW
        Window window1 = new Window(Window.Clause.ROWS, Window.Type.SELF,false);
        reducer2.setWindow(window1);
        reducer2.reduce();
        reducer2.finish();

        // Pipeline 3
        Grouper grouper3 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"department"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                return this.getValue(row,"salary");
            }
        };
        String pipeline3 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader3 = new SimpleDataSetLoader(filter2,grouper3,pipeline3,new Pair<>(lastRelation,","));
        loader3.concatRelationName = false;
        loader3.load();
        Shuffler shuffler3 = new Shuffler(pipeline3);
        shuffler3.shuffle();
        shuffler3.finish();
        Reducer reducer3 = new Reducer(pipeline3,new Average());
        // DEFAULT WINDOW WITH ORDER BY IS UNBOUND PRECEDING
        Window window2 = new Window(Window.Clause.ROWS, Window.Type.PRECEDING,true);
        reducer3.setWindow(window2);
        reducer3.reduce();
        reducer3.finish();

        Assembler assembler2 = new Assembler(assemblerHeaders2);
        assembler2.assemble();
        assembler2.write();


        /*

        1- LAST_VALUE,FIRST_VALUE
        SELECT id AS employee_id,
               department,
               salary,
               LAST_VALUE(salary) OVER (PARTITION BY department ORDER BY name) AS last_val
        FROM   employees

        => reducer = new Reducer(pipeline2,new LastValue());

        1- AVG,Sum.... and all aggregation function works
        SELECT id AS employee_id,
               department,
               salary,
               SUM(salary) OVER (PARTITION BY department ORDER BY name) AS running_Sum
        FROM   employees

        => reducer = new Reducer(pipeline2,new Sum());



         */
    }
    public static void testAnalyticalWithOrderByAndWindow() throws Exception {

                /*
            NOTES:

            Window Class takes 3 Parameters
            Clause (Always ROWS) , Type and boolean UNBOUND

            SELECT LIST WINDOW IS ALWAYS TYPE SELF:
                Window window = new Window(Window.Clause.ROWS, Window.Type.SELF, false);

            FOR DIFFERENT OPTIONS:

            1- PRECEDING
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS [BETWEEN] 2 PRECEDING [AND] [CURRENT ROW] ) as SSum,
            FROM employees

            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS 2 PRECEDING ) as SSum,
            FROM employees

            Window window = new Window(Window.Clause.ROWS, Window.Type.PRECEDING, false);
            window.setPrecedingFactor(1);

            2- FOLLOWING
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS 2 FOLLOWING ) as SSum,
            FROM employees

            Window window = new Window(Window.Clause.ROWS, Window.Type.FOLLOWING, false);
            window.setFollowingFactor(1);

            3- BOTH
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS [BETWEEN] 1 PRECEDING AND 1 FOLLOWING ) as SSum,
            FROM employees

            Window window = new Window(Window.Clause.ROWS, Window.Type.BOTH, false);
            window.setPrecedingFactor(1);
            window.setFollowingFactor(1);

            4- BOTH UNBOUND
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS [BETWEEN] UNBOUND PRECEDING AND UNBOUND FOLLOWING ) as SSum,
            FROM employees

            Window window = new Window(Window.Clause.ROWS, Window.Type.BOTH, true);

            5- UNBOUNDED FOLLOWING
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS [BETWEEN] [CURRENT ROW] [AND] UNBOUNDED FOLLOWING ) as SSum,
            FROM employees

            Window window = new Window(Window.Clause.ROWS, Window.Type.FOLLOWING, UNBOUNDED:true);

            6- UNBOUNDED PRECEDING
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS UNBOUNDED PRECEDING ) as SSum,
            FROM employees

            Window window = new Window(Window.Clause.ROWS, Window.Type.PRECEDING, UNBOUNDED:true);

            7- UNBOUNDED PRECEDING BOUND PRECEDING
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS [BETWEEN] UNBOUNDED PRECEDING [AND] 1 FOLLOWING ) as SSum,
            FROM employees

            window = new Window(Window.Clause.ROWS,Window.Type.UNBOUND_PRECEDING_BOUND_FOLLOWING,true);
            window.setFollowingFactor(1);

            8- BOUNDED PRECEDING UNBOUND PRECEDING
            SELECT department,name
            SUM(salary) OVER (PARTITION BY department ROWS [BETWEEN] 1 PRECEDING [AND] UNBOUNDED FOLLOWING ) as SSum,
            FROM employees

            window = new Window(Window.Clause.ROWS,Window.Type.BOUND_PRECEDING_UNBOUND_FOLLOWING,true);
            window.setPrecedingFactor(1);


         */

        /*

        SELECT department,name,salary
               lAST_VALUE(salary) OVER (PARTITION BY department ORDER BY name
               ROWS [BETWEEN] UNBOUND PRECEDING [AND] UNBOUND FOLLOWING
               ) AS last_val
        FROM   employees

         */

        /**
         * IMPORTANT FOR SYNTAX CHECK : Window frame with ROWS must have an ORDER BY clause
         */

        ArrayList assemblerHeaders = new ArrayList(){{add("department");
            add("name");add("salary");
        }};

        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1
        //The Select List
        Grouper grouper1 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("Nothing");
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"employees_department"));
                list.add(this.getValue(row,"employees_name"));
                list.add(this.getValue(row,"employees_salary"));
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();

        // there is no AS names so called another constructor
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>("test\\employees","|"));
        // or it can be
        //SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1, (HashMap<String, String>) null,(HashMap<String, String>) null,new Pair<>("test\\employees","|"));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();

        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(assemblerHeaders);
        assembler.assemble();
        // SORT by Name
        assembler.orderBy("name", SortComparator.SortType.ASC,false); // <- Important
        /*
          If it was ORDER BY 2
          assembler.orderBy(new SortComparator(SortComparator.SortType.ASC,1,false)); 2-1 = 1;
         */
        assembler.sort(); // <- Very Important
        assembler.write();

        /**
         *
         *  SORTING OF RELATION ENDED HERE
         *
         */

        FileConfig.clearSubQuery();
        String lastRelation = FileConfig.lastAssembelRelation();

        ArrayList assemblerHeaders2 = new ArrayList(){{
            add("department");add("name");add("salary");add("last_value");
        }};

        Filter filter2 = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 2
        //The Select List
        Grouper grouper2 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"department"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"department"));
                list.add(this.getValue(row,"name"));
                list.add(this.getValue(row,"salary"));
                return list;
            }
        };
        String pipeline2 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader2 = new SimpleDataSetLoader(filter2,grouper2,pipeline2,new Pair<>(lastRelation,","));
        loader2.concatRelationName = false; // <- important if processing sub query or previews assemble result or join result
        loader2.load();
        Shuffler shuffler2 = new Shuffler(pipeline2);
        shuffler2.shuffle();
        shuffler2.finish();
        Reducer reducer2 = new Reducer(pipeline2,new DoNothing());
        // All SELECT LIST IN ANALYTICAL FUNCTIONS WITH ORDER BY HAS SELF WINDOW
        Window window1 = new Window(Window.Clause.ROWS, Window.Type.SELF,false);
        reducer2.setWindow(window1);
        reducer2.reduce();
        reducer2.finish();

        // Pipeline 3
        Grouper grouper3 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"department"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                return this.getValue(row,"salary");
            }
        };
        String pipeline3 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader3 = new SimpleDataSetLoader(filter2,grouper3,pipeline3,new Pair<>(lastRelation,","));
        loader3.concatRelationName = false;
        loader3.load();
        Shuffler shuffler3 = new Shuffler(pipeline3);
        shuffler3.shuffle();
        shuffler3.finish();
        Reducer reducer3 = new Reducer(pipeline3,new LastValue());
        // ROWS [BETWEEN] UNBOUND PRECEDING [AND] UNBOUND FOLLOWING
        Window window2 = new Window(Window.Clause.ROWS, Window.Type.BOTH,true);
        reducer3.setWindow(window2);
        reducer3.reduce();
        reducer3.finish();

        Assembler assembler2 = new Assembler(assemblerHeaders2);
        assembler2.assemble();
        assembler2.write();

    }

    public static void testNewJoin() throws IOException, ClassNotFoundException {
        /*
         * SELECT COUNT(s.id) as myCount FROM students [AS] s
         *  FULL OUTER JOIN classes [AS] c
         *      ON s.class = c.id
         *  RIGHT [OUTER] JOIN  sections [AS] sc
         *      ON c.section = sc.id
         *  GROUP BY sc.id
         *  WHERE s.id IS NOT NULL
 *            AND sc.id IS NOT NULL
         */

        HashMap relationNames = new HashMap(){{
            put("students",new ArrayDeque(){{ offer("s"); }});
            put("classes",new ArrayDeque(){{ offer("c"); }});
            put("sections",new ArrayDeque(){{ offer("sc"); }});
        }};

        HashMap attrNames = new HashMap(){{

        }};

        JoiningDataSetLoader loader = new JoiningDataSetLoader(relationNames,attrNames,new Pair<>("test\\students",","),new Pair<>("test\\classes","|"),new Pair<>("test\\sections",","));

        // ON students.class = classes.id
        JoiningDataSetLoader.JoinFilter filter1 = loader.new JoinFilter() {
            @Override
            public boolean valid(ArrayList<String> row1, ArrayList<String> row2) {
                return equals(row1,"s_class",row2,"c_id");
            }
        };

        // ON classes.section = sections.id
        JoiningDataSetLoader.JoinFilter filter2 = loader.new JoinFilter() {
            @Override
            public boolean valid(ArrayList<String> row1, ArrayList<String> row2) {
                return equals(row1,"c_section",row2,"sc_id");
            }
        };
        loader.addFilters(filter1,filter2);
        loader.addTypes(JoiningDataSetLoader.JoinType.FULL_OUTER,JoiningDataSetLoader.JoinType.RIGHT);
        loader.load();

        /********************************** JOIN ENDED HERE ************************************/

        // SELECT LIST NAMES
        ArrayList<String> headers = new ArrayList<>();
        headers.add("myCount");

        String relation = FileConfig.lastJoinRelation();

        // WHERE
        Filter filter = new Filter() {
            public boolean valid(Object object) {
            return
                    !getValue((ArrayList<String>) object,"s_id").equals("NULL")
                    &&
                    !getValue((ArrayList<String>) object,"sc_id").equals("NULL");
            }
        };


        // Pipeline 1 :
        String pipeline1 = FileConfig.getNextPipeline();
        String[] grouperKeys1 = {"sc_id"};
        String grouperValues1 = "s_id";
        Grouper grouper1 = Grouper.make(grouperKeys1,grouperValues1);
        SimpleDataSetLoader loader1 = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>(relation,","));
        loader1.concatRelationName = false; // <- Important in JOIN
        loader1.load();
        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new Count());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(headers);
        assembler.assemble();

        assembler.write();

        // These Line Should Be Added to each Function Tail
        Explain.getStatistics(); // Show Explain Statistics // Important to save result to GUI
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(FileConfig.getResultGUIIndexURI());
    }
    public static void testSelfJoin() throws IOException, ClassNotFoundException {
        /*
         * SELECT A.name,B.name FROM students A,students B
         *  WHERE A.class <> B.class
         */

        HashMap relationNames = new HashMap(){{
            put("students",new ArrayDeque(){{ offer("A");offer("B"); }});
        }};

        HashMap attrNames = new HashMap(){{

        }};

        JoiningDataSetLoader loader = new JoiningDataSetLoader(relationNames,attrNames,new Pair<>("test\\students",","),new Pair<>("test\\students",","));

        // ON students.class = classes.id
        JoiningDataSetLoader.JoinFilter filter1 = loader.new JoinFilter() {
            @Override
            public boolean valid(ArrayList<String> row1, ArrayList<String> row2) {
                return !equals(row1,"A_class",row2,"B_class");
                // It's Not JOIN Condition but can be in this case
                // BUT it must be in WHERE CONDITION AFTER THIS JOIN
            }
        };

        loader.addFilters(filter1);
        loader.addTypes(JoiningDataSetLoader.JoinType.SELF); // <- It's SELF JOIN
        loader.load();

        /********************************** JOIN ENDED HERE ************************************/
    }
    public static void testSelectList_GroupByNothing() throws Exception {
        /*
         *  SELECT DISTINCT class FROM students;
         *
         */

        // SELECT LIST NAMES
        ArrayList<String> headers = new ArrayList<>();
        headers.add("class");

        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1 GROUP BY NOTHING
        Grouper grouper1 = new Grouper() {
            @Override // GROUP BY "nothing"
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("nothing");
                return keysSet;
            }

            @Override // SELECT LIST
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"students_class"));
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>("test\\students",","));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(headers);
        assembler.assemble();
        assembler.distinct(); // <- Came From DISTINCT

        assembler.write();
    }
    public static void testOrderBy() throws IOException, ClassNotFoundException {
        /*
         * SELECT classes.id c_id ,classes.name
         *  FROM classes
         * ORDER BY c_id, 2 DESC;
         */
        ArrayList<String> headers = new ArrayList<>();
        headers.add("c_id");
        headers.add("classes_name");

        HashMap relationNames = new HashMap(){{

        }};

        HashMap attrNames = new HashMap(){{
            put("classes_id","c_id");
        }};

        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1 GROUP BY NOTHING
        Grouper grouper1 = new Grouper() {
            @Override // GROUP BY "nothing"
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("nothing");
                return keysSet;
            }

            @Override // SELECT LIST
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"c_id"));
                list.add(this.getValue(row,"classes_name"));
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,relationNames,attrNames,new Pair<>("test\\classes","|"));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(headers);
        assembler.assemble();
        assembler.orderBy("c_id", SortComparator.SortType.DESC,false);  // <- Important;
        assembler.orderBy(new SortComparator(SortComparator.SortType.ASC,1,true));  // <- Important; 2 - 1 = 1 So sort index is 1
        assembler.sort(); // <- Important;

        assembler.write();

    }

    public static void testAssignQueryResultToVar_andExplain() throws Exception{
        /*
            VAR myRelation = (
                        SELECT s.class as myClass, COUNT(s.id) as myCount
                        FROM students [AS] s
                        Group By myClass
                    )
             SELECT DISTINCT myCount FROM myRelation

             // Or it's a Sub Select
             SELECT DISTINCT myCount FROM (
                        SELECT s.class as myClass, COUNT(s.id) as myCount
                        FROM students [AS] s
                        Group By myClass
             )
         */
        Explain.use(true); // use explain
        /**
         *  First Process myRelation
         */
        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };
        ArrayList<String> assemblerHeaders = new ArrayList(){{add("myClass");add("myCount");}};
        HashMap relationName = new HashMap(){{put("students",new ArrayDeque(){{offer("s");}}); }};
        HashMap attrNames = new HashMap(){{
            put("s_class","myClass");
        }};

        // Pipeline 1 // SELECT LIST
        Grouper grouper1 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"myClass"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"myClass"));
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,relationName,attrNames,new Pair<>("test\\students",","));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        // Pipeline 2
        Grouper grouper2 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"myClass"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                return this.getValue(row,"s_id");
            }
        };
        String pipeline2 = FileConfig.getNextPipeline();
        loader = new SimpleDataSetLoader(filter,grouper2,pipeline2,relationName,attrNames,new Pair<>("test\\students",","));
        loader.load();

        shuffler = new Shuffler(pipeline2);
        shuffler.shuffle();
        shuffler.finish();
        reducer = new Reducer(pipeline2,new Count());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(assemblerHeaders);
        assembler.groupingFlag = true;
        assembler.assemble();
        assembler.write();
        String myRelation = FileConfig.lastAssembelRelation(); // Subquery Result

        /***********************     Sub Query Ended HERE         ******************************/

        // When starting new query in the same run we should clear subquery pipelines and join temp
        // Not the assemble result
        FileConfig.clearSubQuery(); // Clears Temp

        filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };
        assemblerHeaders = new ArrayList(){{add("myCount");}};
        relationName = new HashMap(){{  }};
        attrNames = new HashMap(){{  }};

        // Pipeline 1 // SELECT LIST
        grouper1 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("NoThing");
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"myCount"));
                return list;
            }
        };
        pipeline1 = FileConfig.getNextPipeline();
        loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,relationName,attrNames,new Pair<>(myRelation,","));
        loader.concatRelationName = false;
        loader.load();

        shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        assembler = new Assembler(assemblerHeaders);
        assembler.assemble();
        assembler.distinct();
        assembler.write();

        // These Line Should Be Added to each Function Tail
        Explain.getStatistics(); // Show Explain Statistics // Important to save result to GUI
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(FileConfig.getResultGUIIndexURI());

    }

    public static void testDateParser() throws Exception{
        /*
             SELECT  Parse(students.register_date AS date) AS parsed_date FROM students
         */

        // SELECT LIST NAMES
        ArrayList<String> headers = new ArrayList<>();
        headers.add("parsed_date");

        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1 GROUP BY NOTHING
        Grouper grouper1 = new Grouper() {
            @Override // GROUP BY "nothing"
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("nothing");
                return keysSet;
            }

            @Override // SELECT LIST
            public Object grouberValue(ArrayList<String> row){
                ArrayList list = new ArrayList();
                list.add(
                        DateParser.parse(this.getValue(row,"students_register_date"),OutFormat.date) // <- Important

                );
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>("test\\students",","));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(headers);
        assembler.assemble();

        assembler.write();

        /** OTHER CASES */
        /*
        // SELECT PARSE('Friday, 20 July 2018' AS date)
        System.out.println(DateParser.parse("Friday, 20 July 2018", OutFormat.date));

        // SELECT PARSE('2:35:50pm, Friday, 20 July 2018' AS smalldatetime)
        System.out.println(DateParser.parse("2:35:50pm, Friday, 20 July 2018", OutFormat.smalldatetime));

        // SELECT PARSE('2:35:50.5234pm, Friday, 20 July 2018' AS datetime)
        System.out.println(DateParser.parse("2:35:50pm, Friday, 20 July 2018", OutFormat.datetime));

        /*
        SELECT
            PARSE('Friday, 20 July 2018' AS date) AS 'Result 1',
            PARSE('Fri, 20 July 2018' AS date) AS 'Result 2',
            PARSE('Friday, July 20 2018' AS date) AS 'Result 3',
            PARSE('Fri 20 Jul 18' AS date) AS 'Result 4';
         */
        /*
        System.out.println(DateParser.parse("Friday, 20 July 2018", OutFormat.date));
        System.out.println(DateParser.parse("Fri, 20 July 2018", OutFormat.date));
        System.out.println(DateParser.parse("Friday, July 20 2018", OutFormat.date));
        System.out.println(DateParser.parse("Fri 20 Jul 18", OutFormat.date));

        // SELECT PARSE('2:35:50pm, Friday, 20 July 2018' AS time)
        System.out.println(DateParser.parse("2:35:50pm, Friday, 20 July 2018", OutFormat.time));

        /*
              All this inputs works
              "31/12/1998";
              "31-Dec-1998";
              "12 31, 1998";
              "Thu, Dec 31 1998";
              "Thu, Dec 31 1998 23:37:50";
              "31-Dec-1998 23:37:50";
         */
        /**
         * Supported Formats
         */

        /*
            "dd/MM/yyyy",
            "dd-MMM-yyyy",
            "MM dd, yyyy",
            "E, dd MMMM yyyy",
            "E, dd MM yyyy",
            "E, dd MMM yyyy",
            "E, dd MMMM yy",
            "E, dd MM yy",
            "E, dd MMM yy",
            "E, dd MMMM yy",
            "E dd MMMM yyyy",
            "E dd MM yyyy",
            "E dd MMM yyyy",
            "E dd MMMM yy",
            "E dd MM yy",
            "E dd MMM yy",
            "E dd MMMM yy",
            "E, MMM dd yyyy",
            "E, MMMM dd yyyy",
            "E, MMM dd yyyy HH:mm:ss",
            "E, MMM dd yyyy HH:mm:ssa",
            "E, MMMM dd yyyy HH:mm:ss",
            "E, MMMM dd yyyy HH:mm:ssa",
            "dd-MMM-yyyy HH:mm:ss",
            "MM/dd/yyyy",
            "dd-M-yyyy hh:mm:ss",
            "dd MMMM yyyy",
            "dd MMMM yyyy zzzz",
            "E, dd MMM yyyy HH:mm:ss z",
            "E, dd MM yyyy HH:mm:ss z",
            "HH:mm:ssa, E, MMMM YYYY",
            "HH:mm:ssa, E, dd MMMM YYYY",
            "HH:mm:ssa, E, MMM YYYY",
            "HH:mm:ssa, E, dd MMM YYYY",
            "HH:mm:ss.Sa, E, MMMM YYYY",
            "HH:mm:ss.Sa, E, dd MMMM YYYY",
            "HH:mm:ss.Sa, E, MMM YYYY",
            "HH:mm:ss.Sa, E, dd MMM YYYY",
         */

    }
    public static void testRankFunction() throws Exception{
        /*

        SELECT department,name,salary
               RANK() OVER (PARTITION BY department ORDER BY salary) AS rank
        FROM   employees

         */

        /**
         * Rank Functions on salary (That came after order by)
         * So it has no paremeter
         */
        ArrayList assemblerHeaders = new ArrayList(){{add("department");
            add("name");add("salary");
        }};

        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1
        //The Select List
        Grouper grouper1 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("Nothing");
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"employees_department"));
                list.add(this.getValue(row,"employees_name"));
                list.add(this.getValue(row,"employees_salary"));
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();

        // there is no AS names so called another constructor
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>("test\\employees","|"));
        // or it can be
        //SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1, (HashMap<String, String>) null,(HashMap<String, String>) null,new Pair<>("test\\employees","|"));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();

        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(assemblerHeaders);
        assembler.assemble();
        // SORT by Salary
        assembler.orderBy("salary", SortComparator.SortType.ASC,true); // <- Important , Numeric = true
        assembler.sort(); // <- Very Important
        assembler.write();

        /**
         *
         *  SORTING OF RELATION ENDED HERE
         *
         */

        FileConfig.clearSubQuery();
        String lastRelation = FileConfig.lastAssembelRelation();

        ArrayList assemblerHeaders2 = new ArrayList(){{add("department");add("name");add("salary");add("rank");

        }};

        Filter filter2 = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 2
        //The Select List
        Grouper grouper2 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"department"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                ArrayList list = new ArrayList();
                list.add(this.getValue(row,"department"));
                list.add(this.getValue(row,"name"));
                list.add(this.getValue(row,"salary"));
                return list;
            }
        };
        String pipeline2 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader2 = new SimpleDataSetLoader(filter2,grouper2,pipeline2,new Pair<>(lastRelation,","));
        loader2.concatRelationName = false; // <- important if processing sub query or previews assemble result or join result
        loader2.load();
        Shuffler shuffler2 = new Shuffler(pipeline2);
        shuffler2.shuffle();
        shuffler2.finish();
        Reducer reducer2 = new Reducer(pipeline2,new DoNothing());
        // All SELECT LIST IN ANALYTICAL FUNCTIONS WITH ORDER BY HAS SELF WINDOW
        Window window1 = new Window(Window.Clause.ROWS, Window.Type.SELF,false);
        reducer2.setWindow(window1);
        reducer2.reduce();
        reducer2.finish();

        // Pipeline 3
        Grouper grouper3 = new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(this.getValue(row,"department"));
                return keysSet;
            }

            @Override
            public Object grouberValue(ArrayList<String> row) {
                return this.getValue(row,"salary");
            }
        };
        String pipeline3 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader3 = new SimpleDataSetLoader(filter2,grouper3,pipeline3,new Pair<>(lastRelation,","));
        loader3.concatRelationName = false;
        loader3.load();
        Shuffler shuffler3 = new Shuffler(pipeline3);
        shuffler3.shuffle();
        shuffler3.finish();
        Reducer reducer3 = new Reducer(pipeline3,new Rank());
        // DEFAULT WINDOW WITH ORDER BY IS UNBOUND PRECEDING
        Window window2 = new Window(Window.Clause.ROWS, Window.Type.PRECEDING,true);
        reducer3.setWindow(window2);
        reducer3.reduce();
        reducer3.finish();

        Assembler assembler2 = new Assembler(assemblerHeaders2);
        assembler2.assemble();
        assembler2.write();

                /*

        SELECT department,name,salary
               DENSE_RANK() OVER (PARTITION BY department ORDER BY salary) AS rank
        FROM   employees

        Reducer reducer3 = new Reducer(pipeline3,new DenseRank());

         */
    }

    /*Mohammad Alkhiyami added this function*/
    public static void TestCastType() throws Exception{
        /*
             SELECT  Cast(students.register_time AS TIME) AS Casted_type FROM students
         */

        // SELECT LIST NAMES
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Casted_type");

        Filter filter = new Filter() {
            @Override
            public boolean valid(Object object) {
                return true;
            }
        };

        // Pipeline 1 GROUP BY NOTHING
        Grouper grouper1 = new Grouper() {
            @Override // GROUP BY "nothing"
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add("nothing");
                return keysSet;
            }

            @Override // SELECT LIST
            public Object grouberValue(ArrayList<String> row){
                ArrayList list = new ArrayList();
                /*list.add(
                        TypeCaster.caste(this.getValue(row,"students_register_time"),CastTo.time) // <- Important

                );*/
                return list;
            }
        };
        String pipeline1 = FileConfig.getNextPipeline();
        SimpleDataSetLoader loader = new SimpleDataSetLoader(filter,grouper1,pipeline1,new Pair<>("test\\students",","));
        loader.load();

        Shuffler shuffler = new Shuffler(pipeline1);
        shuffler.shuffle();
        shuffler.finish();
        Reducer reducer = new Reducer(pipeline1,new DoNothing());
        reducer.reduce();
        reducer.finish();

        Assembler assembler = new Assembler(headers);
        assembler.assemble();

        assembler.write();
    }


}
