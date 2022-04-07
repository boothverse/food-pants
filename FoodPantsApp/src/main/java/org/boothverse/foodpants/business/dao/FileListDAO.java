package org.boothverse.foodpants.business.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import org.boothverse.foodpants.persistence.IdObject;

import java.io.*;
import java.util.List;
import java.util.Map;

public class FileListDAO<T extends IdObject> extends FileDAO<T> {

    public FileListDAO(Class<T> t) {
        super(t);
    }

    public Map<String, T> load() throws IOException {
        try (Reader reader = new FileReader(filename)) {
            List<T> data = mapper.readValue(reader, new TypeReference<>(){});
            return Maps.uniqueIndex(data, T::getId);
        }
    }

    public void save(T data, Boolean append) throws IOException {
        try (Writer writer = new FileWriter(filename, append)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        }
    }

    public void save(List<T> data, Boolean append) throws IOException {
        try (Writer writer = new FileWriter(filename, append)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        }
    }

    public void delete(String id) throws IOException {
        List<T> data;
        try (Reader reader = new FileReader(filename)) {
            data = mapper.readValue(reader, new TypeReference<>(){});
        }
        data.removeIf(e -> e.getId().equals(id));
        save(data, false);
    }
}
