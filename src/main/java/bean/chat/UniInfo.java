package bean.chat;

public class UniInfo extends SendInfo {
    private String device_tokens;

    public UniInfo() {
        setType("unicast");
    }

    public String getDevice_tokens() {
        return device_tokens;
    }

    public void setDevice_tokens(String device_tokens) {
        this.device_tokens = device_tokens;
    }
}
