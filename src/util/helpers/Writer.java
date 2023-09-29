package util.helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Writer {

    private final File FILE;

    public Writer(String location) {
        this.FILE = new File(location);
    }

    public void store(String line) {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true));
            writer.write(line);


        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    public void clear() {

        try {

            FileWriter fwOb = new FileWriter(FILE, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();

        } catch(IOException e) {

            throw new RuntimeException(e);

        }

    }

    public boolean doesEntryExist(String entry, String delimeter, int index) {

        List<String> entries = getEntries();

        if(delimeter == null) {
            index = 0;
            for(String line : entries) {
                if(line.equals(entry)) return true;
            }
        }

        for(String line : entries) {

            String[] parts = line.split(delimeter);

            if(parts.length > index) {

                if(parts[index].equals(entry)) return true;

            }

        }

        return false;

    }

    public List<String> getEntries() {
        List<String> entries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

}
