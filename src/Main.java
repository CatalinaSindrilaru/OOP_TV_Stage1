import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length >= 2) {
            ObjectMapper mapper = new ObjectMapper();

            Input input = mapper.readValue(new File(args[0]), Input.class);

            ArrayNode output = mapper.createArrayNode();

            // apelare functie cu input si output - clasa singleton
            StartProgram program = StartProgram.getInstance();
            program.run(input, output);

            ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
            objectWriter.writeValue(new File(args[1]), output);
        }
    }
}
