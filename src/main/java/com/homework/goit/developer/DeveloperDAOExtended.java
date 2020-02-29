package com.homework.goit.developer;

import java.util.List;
import java.util.Map;

public interface DeveloperDAOExtended<T> {
    List<T> getJavaDeveloper();
    Map<String, T> getMiddleDevelopers();
    Map<String, T> getDevelopersInProject(int id);
}
