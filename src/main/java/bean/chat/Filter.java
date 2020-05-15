package bean.chat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Filter {


    public static final int TYPE_AND = 1;
    public static final int TYPE_OR = 2;
    private Where where;

    public Filter(Where where) {
        this.where = where;
    }

    public Where getWhere() {
        return where;
    }


    public void setWhere(Where where) {
        this.where = where;
    }


    public static Filter newFilter(int type, String key, String value) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(key, value);
        return newFilter(type, hashMap);
    }

    public static Filter newFilter(int type, Map<String, String>... maps) {
        if (maps == null || maps.length == 0) {
            return null;
        }
        Where where = new Where();
        if (type == TYPE_AND) {
            where.setAnd(Arrays.asList(maps));
        } else {
            where.setAnd(Arrays.asList(maps));
        }
        return new Filter(where);

    }

}
