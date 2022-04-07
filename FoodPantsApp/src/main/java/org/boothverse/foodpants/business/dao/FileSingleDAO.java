package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import org.boothverse.foodpants.persistence.User;

import java.io.*;

public class FileSingleDAO<T> extends FileDAO {

    public FileSingleDAO(String filename) {
        super(filename);
    }

    public T load(Class<T> t) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            return mapper.readValue(reader, t);
        }
    }

    public void save(T data) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        }
    }
}
