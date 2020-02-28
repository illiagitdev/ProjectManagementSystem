package com.homework.goit.common;

import java.util.List;

public abstract class DataAccessObject<T extends Entity> {
        public abstract void create(T t);
        public abstract void update(T t);
        public abstract T getById(int id);
        public abstract List<T> getAll();
        public abstract void delete(int id);
}
