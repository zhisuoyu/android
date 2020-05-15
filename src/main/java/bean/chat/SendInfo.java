package bean.chat;

import com.google.gson.Gson;

public class SendInfo {


    public static void main(String[] args) {
        SendInfo sendInfo = new SendInfo();
        sendInfo.setTimestamp(System.currentTimeMillis() + "");
        sendInfo.setDescription("des");

        sendInfo.setFilter(Filter.newFilter(Filter.TYPE_AND, "tag", "tag2"));

        Custom custom = new Custom();
//        ChatMsg chatMsg = new ChatMsg("mzs",System.currentTimeMillis(),"hello");
//        custom.setDt(System.currentTimeMillis());
//        custom.setFrom("mzs");
//        custom.setMsg("hello");
        Body<Custom> customBody = new Body<>();
        customBody.setCustom(custom);
        PayLoad payLoad = new PayLoad();
        payLoad.setBody(customBody);
        sendInfo.setPayload(payLoad);

        System.out.println(new Gson().toJson(sendInfo));


    }


    private String appkey = "5e0ab9fb4ca35742b600066c";
    private String timestamp = System.currentTimeMillis() + "";
    private String type = "groupcast";
    private String description;

    private Filter filter;

    private PayLoad payload;

    private Policy policy;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public PayLoad getPayload() {
        return payload;
    }

    public void setPayload(PayLoad payload) {
        this.payload = payload;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
}
