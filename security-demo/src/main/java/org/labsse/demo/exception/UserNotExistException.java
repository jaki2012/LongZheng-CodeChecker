package org.labsse.demo.exception;

/**
 * @author lijiechu
 * @create on 2018/12/25
 * @description
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("用户不存在1");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
