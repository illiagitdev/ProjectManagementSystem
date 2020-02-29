package com.homework.goit.project;

import java.sql.Date;
import java.util.Map;

public interface ProjectDAOExtended<T> {
    Map<String, Integer> getSalaryByProject(int id);

    Map<Date, Map<String, Integer>> getProjects();
}
