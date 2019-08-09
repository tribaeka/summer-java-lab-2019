package by.epam.training.task6.dao;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONDAO implements IDAO{

    private JSONObject data;

    public JSONDAO(JSONObject data) {
        this.data = data;
    }

    public  <T> List<T> parseKey(String key, Class<T> type) {
        List<T> answer = new ArrayList<>();
        for (Object item : data.getJSONArray(key)) {
            answer.add(JSON.parseObject(item.toString(), type));
        }
        return answer;
    }


}
