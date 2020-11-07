package io.github.ellismatthew4.empireeconomy.utils;

import com.google.gson.Gson;

import java.io.*;

public class PluginIO {
    private final LoggerService logger = LoggerService.getInstance();
    private final Gson gson = new Gson();

    public <T> void writeFile(String path, T object) {
        try {
            PrintWriter out = new PrintWriter(path);
            out.println(gson.toJson(object));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.warning("Could not save file: " + path);
        }
    }

    public <T> T readFile(String path, Class<T> cl) {
        logger.info("Reading file: " + path);
        StringBuilder in = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                in.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("Could not read file: " + path);
        }
        return gson.fromJson(in.toString(), cl);
    }
}
