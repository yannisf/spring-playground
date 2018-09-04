package fraglab.application.library;

import java.util.List;

public class ResponseError {

    private String info;
    private List<String> messages;

    public ResponseError() { }

    public ResponseError(String info, List<String> messages) {
        this.info = info;
        this.messages = messages;
    }

    public String getInfo() {
        return info;
    }

    public List<String> getMessages() {
        return messages;
    }

}
