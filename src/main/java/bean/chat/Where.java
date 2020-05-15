package bean.chat;

import java.util.List;
import java.util.Map;

public class Where {


    private List<Map<String, String>> and;
    private List<Map<String, String>> or;


    public List<Map<String, String>> getAnd() {
        return and;
    }

    public void setAnd(List<Map<String, String>> and) {
        this.and = and;
    }

    public List<Map<String, String>> getOr() {
        return or;
    }

    public void setOr(List<Map<String, String>> or) {
        this.or = or;
    }
}
