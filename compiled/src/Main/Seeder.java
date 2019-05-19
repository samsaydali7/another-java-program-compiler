package Main;

import MapReduce.FileConfig;
import MapReduce.Map.Mapper;

import java.io.IOException;
import java.util.ArrayList;

public class Seeder {

    public void seed(Mapper mapper) throws IOException {

        ArrayList<String> row1 = new ArrayList<String>(){{
            add("1");add("Samer");add("21");
        }};
        ArrayList<String> row2 = new ArrayList<String>(){{
            add("2");add("Ibrahim");add("21");
        }};
        ArrayList<String> row3 = new ArrayList<String>(){{
            add("3");add("Mohammad");add("21");
        }};
        ArrayList<String> row4 = new ArrayList<String>(){{
            add("4");add("Samer");add("21");
        }};
        ArrayList<String> row5 = new ArrayList<String>(){{
            add("4");add("Sameer");add("23");
        }};

        mapper.addRow(row1);
        mapper.addRow(row2);
        mapper.addRow(row3);
        mapper.addRow(row4);
        mapper.addRow(row5);


    }
}
