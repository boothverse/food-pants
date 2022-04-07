package org.boothverse.foodpants.business.dao;

import java.io.*;

public class FileSingleDAO<T> extends FileDAO<T> {

    public FileSingleDAO(Class<T> t) {
        super(t);
    }

    public T load() throws IOException {
        try (Reader reader = new FileReader(filename)) {
            return mapper.readValue(reader, type);
        }
    }

    public void save(T data) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        }
    }
}
