package Main;

import FileStream.DataSet.CSVFileReader;
import FileStream.DataSet.CSVWriter;
import FileStream.MapResult.MRLoader;
import FileStream.ReduceResult.RRLoader;
import FileStream.ResultWriter;
import FileStream.ShuffleResult.SRLoader;
import MapReduce.Assemple.Assembler;
import MapReduce.Reduce.Aggrigation.Complex.Summarize;
import MapReduce.Reduce.Aggrigation.DoNothing;
import MapReduce.Reduce.Analytical.DenseRank;
import MapReduce.Reduce.Analytical.Rank;
import MapReduce.Util.Compare.DistinctComparator;
import MapReduce.Util.Compare.SortComparator;
import MapReduce.FileConfig;
import MapReduce.Map.Filter;
import MapReduce.Map.Grouper;
import MapReduce.Map.Mapper;
import MapReduce.Reduce.Aggrigation.Sum;
import MapReduce.Reduce.Reducer;
import MapReduce.Reduce.Window;
import MapReduce.Shuffle.Shuffler;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Tester {
    public static void testCSVReader() throws IOException {
        CSVFileReader reader = new CSVFileReader("test\\set.csv", '|');
        reader.load();
        reader.finish();
    }
    public static void testCSVWriter() throws IOException {
        CSVWriter writer = new CSVWriter("temp\\pipline1\\temptable1.csv");
        ArrayList<String> headers = new ArrayList(){{add("id");add("name");add("age");}};
        writer.writeHeaders(headers);

        ArrayList<String> row1 = new ArrayList(){{add("1");add("Samer");add("22");}};
        writer.writeRow(row1);

        ArrayList<String> row2 = new ArrayList(){{add("2");add("Tabba");add("23");}};
        writer.writeRow(row2);

        writer.finish();
    }
    public static void testFileConfig(){
        // Test Pipeline
        System.out.println(FileConfig.getNextPipeline());
        for (File file : FileConfig.getPiplines()){
            System.out.println(file.getAbsolutePath());
        }
        String pipline1 = FileConfig.getPiplines()[0].getAbsolutePath();

        // Test Mapper Section
        System.out.println(FileConfig.getNextMapResult(pipline1));
        for (File file : FileConfig.listMapResultsFiles(pipline1)){
            System.out.println(file.getPath());
        }

        // Test Shuffle Section
        System.out.println(FileConfig.getNextShuffleResult(pipline1));
        for (File file : FileConfig.listShuffleResultsFiles(pipline1)){
            System.out.println(file.getPath());
        }

        // Test Reduce Section
        System.out.println(FileConfig.getNextReduceResult(pipline1));
        for (File file : FileConfig.listReduceResultsFiles(pipline1)){
            System.out.println(file.getPath());
        }
    }
    public static void testFileConfigJoin(){
        System.out.println(FileConfig.getNextJoinRelation());
        System.out.println(FileConfig.getNextJoinRelationFile("temp\\join\\joinRelation4"));
        System.out.println(FileConfig.lastJoinRelation());
        File[] files = FileConfig.listJoinRelationFiles(FileConfig.lastJoinRelation());
        for (File file:files) {
            System.out.println(file.getAbsolutePath());
        }
    }
    public static void testMapResultLoader() {
        MRLoader loader = new MRLoader("test\\MapResult.txt");
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testShuffleResultLoader(){
        SRLoader loader = new SRLoader("test\\ShuffleResult.txt");
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testReduceResultLoader(){
        RRLoader loader = new RRLoader("test\\ReduceResult.txt");
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testMapResultWriter() {
        ArrayList<String> headers = new ArrayList(){{ add("city");add("street"); }};

        ResultWriter writer = null;
        try {
            writer = new ResultWriter("test\\MapResult.txt",headers);
            LinkedHashSet keyset1 = new LinkedHashSet(){{ add("key1");add("key2"); }};
            String val1 = "val1";
            Pair<LinkedHashSet,Object> line1 = new Pair<>(keyset1,val1);
            writer.write(line1);

            LinkedHashSet keyset2 = new LinkedHashSet(){{ add("key1");add("key2"); }};
            String val2 = "val2";
            Pair<LinkedHashSet,Object> line2 = new Pair<>(keyset2,val2);
            writer.write(line2);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testShuffleResultWriter(){
        ArrayList<String> headers = new ArrayList(){{ add("city");add("street"); }};

        ResultWriter writer = null;
        try {
            writer = new ResultWriter("test\\ShuffleResult.txt",headers);
            LinkedHashSet keyset1 = new LinkedHashSet(){{ add("key1");add("key2"); }};
            ArrayList<String> val1 = new ArrayList(){{add("val1");add("val2");}};
            Pair<LinkedHashSet,Object> line1 = new Pair<>(keyset1,val1);
            writer.write(line1);

            LinkedHashSet keyset2 = new LinkedHashSet(){{ add("key3");add("key2"); }};
            ArrayList<String> val2 = new ArrayList(){{add("val3");}};
            Pair<LinkedHashSet,Object> line2 = new Pair<>(keyset2,val2);
            writer.write(line2);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testReduceResultWriter() {
        ArrayList<String> headers = new ArrayList(){{ add("city");add("street");add("sum"); }};

        ResultWriter writer = null;
        try {
            writer = new ResultWriter("test\\ReduceResult.txt",headers);
            LinkedHashSet keyset1 = new LinkedHashSet(){{ add("key1");add("key2"); }};
            String val1 = "res1";
            Pair<LinkedHashSet,Object> line1 = new Pair<>(keyset1,val1);
            writer.write(line1);

            LinkedHashSet keyset2 = new LinkedHashSet(){{ add("key1");add("key2"); }};
            String val2 = "res2";
            Pair<LinkedHashSet,Object> line2 = new Pair<>(keyset2,val2);
            writer.write(line2);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testMapper() throws IOException, ClassNotFoundException {
        Seeder seeder = new Seeder();

        ArrayList<String> headers = new ArrayList<String>(){{
            add("id");add("name");add("age");
        }};


        String pipeline = FileConfig.getNextPipeline();
        Mapper mapper = new Mapper(headers,pipeline);

        // Filter
        mapper.acceptFilter(new Filter() {
            @Override
            public boolean valid(Object object) {
                ArrayList<String> row = (ArrayList<String>) object;
                return true;
                //return mapper.getValue(row,"name").substring(0,3).equals("Sam");
            }
        });

        // Grouping
        mapper.acceptGrouper(new Grouper() {
            @Override
            public Object grouberKey(ArrayList<String> row) {
                Set<String> keysSet = new LinkedHashSet<>();
                keysSet.add(getValue(row,"name").substring(0,3));
                keysSet.add(getValue(row,"age"));
                return keysSet;
            }

            @Override
            public String grouberValue(ArrayList<String> row) {
                return getValue(row,"age");
            }
        });
        seeder.seed(mapper);
        // Close Data Stream
        mapper.finish();

    }
    public static void testShuffler() throws IOException, ClassNotFoundException {
        Shuffler shuffler = new Shuffler("test\\testPipeline");
        shuffler.shuffle();
        shuffler.finish();
    }
    public static void testReducer() throws IOException, ClassNotFoundException {
        Reducer reducer = new Reducer(FileConfig.getPiplines()[FileConfig.getPiplines().length - 1].getAbsolutePath(),new Sum());
        reducer.reduce();
        reducer.finish();
    }
    public static void testAssempler() throws IOException {
        Assembler assembler = new Assembler();
        assembler.assemble();
        assembler.print();

    }
    public static void testGroupByExtract() throws IOException {
        Assembler assembler = new Assembler();
        assembler.groupingFlag = true;
        assembler.assemble();
        assembler.print();

    }
    public static void testDistinct() throws IOException {
        // SELECT DISTINCT .....
        Assembler assembler = new Assembler();
        assembler.assemble();
        assembler.distinct();
        assembler.print();

    }
    public static void testHaving() throws IOException {
        Assembler assembler = new Assembler(new ArrayList(){{add("dep");add("name");
            add("sum1"); add("sum2"); add("avg");
        }});
        assembler.assemble();

        // Having Sum1 >= 200000;
        Assembler.Filter filter = assembler.new Filter() {
            @Override
            public boolean valid(ArrayList<Object> row) {
                return getDouble(row,"sum1") > 200000;
            }
        };
        assembler.filter(filter);
        assembler.print();

    }
    public static void testAssembleWrite() throws IOException {
        Assembler assembler = new Assembler(new ArrayList(){{add("dep");add("name");
            add("sum1"); add("sum2"); add("avg");
        }});
        assembler.assemble();

        assembler.write();

    }
    public static void testWindow(){
        ArrayList<Object> list = new ArrayList<>();
        list.add(200);
        list.add(200);
        list.add(300);
        list.add(200);
        list.add(500);
        list.add(400);

        // ROWS PRECEDING 2
        Window window = new Window(Window.Clause.ROWS,Window.Type.PRECEDING,false);
        window.setPrecedingFactor(2);

        //System.out.println(window.partition(list));

        // ROWS FOLLOWING 2
        window = new Window(Window.Clause.ROWS,Window.Type.FOLLOWING,false);
        window.setFollowingFactor(2);

        //System.out.println(window.partition(list));

        // ROWS BOTH 2 PRECEDING 2 FOLLOWING
        window = new Window(Window.Clause.ROWS,Window.Type.BOTH,false);
        window.setFollowingFactor(2);
        window.setPrecedingFactor(2);

        //System.out.println(window.partition(list));

        // ROWS PRECEDING UNBOUND
        window = new Window(Window.Clause.ROWS,Window.Type.PRECEDING,true);

        //System.out.println(window.partition(list));

        // ROWS FOLLOWING UNBOUND
        window = new Window(Window.Clause.ROWS,Window.Type.FOLLOWING,true);

        //System.out.println(window.partition(list));

        window = new Window(Window.Clause.ROWS,Window.Type.UNBOUND_PRECEDING_BOUND_FOLLOWING,true);
        window.setFollowingFactor(1);
        //System.out.println(window.partition(list));

        window = new Window(Window.Clause.ROWS,Window.Type.BOUND_PRECEDING_UNBOUND_FOLLOWING,true);
        window.setPrecedingFactor(1);
        System.out.println(window.partition(list));

    }
    public static void testRRLoader() throws IOException {
        Assembler assembler = new Assembler();
        RRLoader loader = new RRLoader("test\\ReduceResult.txt");
        loader.setAssembler(assembler);
        loader.load();
        assembler.print();
    }
    public static void testGetAllReduceResults(){
        String[] list = FileConfig.getAllReduceResults();
        for (String string : list){
            System.out.println(string);
        }
    }
    public static void testDistinctComparator(){
        ArrayList<ArrayList> lists = new ArrayList<>();

        ArrayList list1 = new ArrayList();
        list1.add("3");
        list1.add("Samer");

        ArrayList list2 = new ArrayList();
        list2.add("1");
        list2.add("Samer");

        ArrayList list3 = new ArrayList();
        list3.add("3");
        list3.add("Ahmad");

        ArrayList list4 = new ArrayList();
        list4.add("1");
        list4.add("Ahmad");

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);

        Collections.sort(lists,new DistinctComparator());

        System.out.println(lists);

    }
    public static void testSortComparator(){
        ArrayList<ArrayList> lists = new ArrayList<>();

        ArrayList list1 = new ArrayList();
        list1.add("1");
        list1.add("Samer");

        ArrayList list2 = new ArrayList();
        list2.add("7.1");
        list2.add("Samer");

        ArrayList list3 = new ArrayList();
        list3.add("111.0");
        list3.add("Ahmad");

        ArrayList list4 = new ArrayList();
        list4.add("1");
        list4.add("Ahmad");

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);

        SortComparator sortComparator = new SortComparator(SortComparator.SortType.ASC,0,true);
        sortComparator.thenUse(new SortComparator(SortComparator.SortType.DESC,1,false));

        Collections.sort(lists,sortComparator);

        System.out.println(lists);

    }
    public static void testDenseRank(){
        ArrayList list = new ArrayList();
        list.add("100");
        list.add("200");
        list.add("200");
        list.add("200");
        list.add("300");
        list.add("300");
        list.add("400");
        list.add("500");
        list.add("600");
        DenseRank DENSERANK = new DenseRank();
        for (Object o : list){
            System.out.println(o);
            DENSERANK.aggregate(o);
            System.out.println(DENSERANK.getResult());
        }
    }
    public static void testRank(){
        ArrayList list = new ArrayList();
        list.add("100");
        list.add("200");
        list.add("200");
        list.add("200");
        list.add("300");
        list.add("300");
        list.add("400");
        list.add("500");
        list.add("600");
        Rank RANK = new Rank();
        for (Object o : list){
            System.out.println(o);
            RANK.aggregate(o);
            System.out.println(RANK.getResult());
        }
    }
    public static void testReduceBreakList() throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        list.add("[Samer, 5]");
        list.add("[Obada, 7]");
        list.add("[Tabba, 4]");
        Reducer reducer = new Reducer("wer", new DoNothing());
        SortComparator sortComparator = new SortComparator(SortComparator.SortType.ASC,1,true);
        reducer.setSelectListComparator(sortComparator);
        ArrayList<Object> broken = reducer.breakList(list);

        System.out.println(broken);
    }

    public static void testSummerize(){
        Summarize summarize = new Summarize();
        summarize.aggregate(6);
        summarize.aggregate(7);
        summarize.aggregate(6);
        summarize.aggregate(5);
        System.out.println(summarize.getResult());
    }
}
