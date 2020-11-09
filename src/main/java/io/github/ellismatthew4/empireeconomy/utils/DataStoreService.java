package io.github.ellismatthew4.empireeconomy.utils;

import io.github.ellismatthew4.empireeconomy.data.Data;

public class DataStoreService {
    private static DataStoreService instance = null;
    private final LoggerService logger = LoggerService.getInstance();
    private final PluginIO pluginIO;
    private final String DATA_PATH = "ee_data.json";
    public Data data;

    private DataStoreService() {
        pluginIO = new PluginIO();
        data = pluginIO.readFile(DATA_PATH, Data.class);
        data = data == null ? new Data() : data;
        data.init();
    }

    public static DataStoreService getInstance() {
        if (instance == null) {
            synchronized (DataStoreService.class) {
                if (instance == null) {
                    instance = new DataStoreService();
                }
            }
        }
        return instance;
    }

    public void writeData() {
        logger.info("Writing data file to: " + DATA_PATH);
        pluginIO.writeFile(DATA_PATH, data);
    }

}
