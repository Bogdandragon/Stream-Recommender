import java.io.*;
import java.util.AbstractMap;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ProiectPOO {

    private static ProiectPOO uniqueInst;
    // All variables of type Stream, Streamer and User are stored here
    private AbstractMap<Integer, InputBase> inputCollection = new TreeMap<>();
    private ProiectPOO() {}

    public static ProiectPOO Instance() {
        if(uniqueInst == null) {
            uniqueInst = new ProiectPOO();
        } else {
            uniqueInst.inputCollection = new TreeMap<>();
        }

        return uniqueInst;
    }

    public static void load(String type, ProiectPOO instance, InputFactory factory, String path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        String line;
        while((line = br.readLine()) != null) {
            String[] values = line.split(",\"");
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].replace("\"", "");
            }
            factory.input(type, values, instance.inputCollection);
        }
        br.close();
    }

    public static String JSONOutput(Object obj) {
        try {
            ObjectWriter ow = new ObjectMapper().configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true).writer();
            return ow.writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        // Instantiate unique instance
        ProiectPOO instance = Instance();

        // The factory that is used to process input
        InputFactory factory = new InputFactory();

        try{
            // Load the streamers into memory
            load("streamer", instance, factory, "src/main/resources/" + args[0]);

            // Load the users into memory
            load("user", instance, factory, "src/main/resources/" + args[2]);

            // Load the streams into memory
            load("stream", instance, factory, "src/main/resources/" + args[1]);

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + args[3]));
            String line;
            while((line = br.readLine()) != null) {
                String values[] = line.split(" ");
                factory.command(values, instance.inputCollection);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
