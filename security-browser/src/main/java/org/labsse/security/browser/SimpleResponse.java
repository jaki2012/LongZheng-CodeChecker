package org.labsse.security.browser;

/**
 * @author lijiechu
 * @create on 2018/12/27
 * @description
 */
public class SimpleResponse {
    public String content;

    public SimpleResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
