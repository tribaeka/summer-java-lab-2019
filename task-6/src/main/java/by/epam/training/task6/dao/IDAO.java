package by.epam.training.task6.dao;

import java.util.List;

public interface IDAO {
    <T> List<T> parseKey(String key, Class<T> type);
}
