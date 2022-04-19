package org.boothverse.foodpants.business.dao.fileDAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Maps;
import org.boothverse.foodpants.persistence.IdObject;

import java.io.*;
import java.util.List;
import java.util.Map;

@Deprecated
public class FileListDAO<T extends IdObject> extends FileDAO<T> {

    protected final TypeFactory typeFactory;

    public FileListDAO(Class<T> t, String filename) {
        super(t, filename);
        typeFactory = mapper.getTypeFactory();
    }

    public Map<String, T> load() throws IOException {
        try (Reader reader = new FileReader(filename)) {
            List<T> data = mapper.readValue(reader, typeFactory.constructCollectionType(List.class, type));
            /**
            NutritionInstance[] raw = mapper.readValue(reader, NutritionInstance[].class);
            List<T> data = new ArrayList<>();
            for (NutritionInstance n : raw) {
                System.out.println(n);
            }
             */
            for (T d : data) {
                System.out.println(d);
            }
            return Maps.uniqueIndex(data, T::getId);
        }
    }

    public void save(List<T> data) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        }
    }

    public void delete(String id) throws IOException {
        List<T> data;
        try (Reader reader = new FileReader(filename)) {
            data = mapper.readValue(reader, new TypeReference<>(){});
        }
        data.removeIf(e -> e.getId().equals(id));
        save(data);
    }
}
