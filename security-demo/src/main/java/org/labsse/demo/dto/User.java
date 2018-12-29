package org.labsse.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.labsse.demo.validator.MyConstraint;

//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author lijiechu
 * @create on 2018/12/21
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public interface UserSimpleView {}

    public interface UserDetailView extends UserSimpleView {}

    @MyConstraint(message = "这是一个测试")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String id;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getId() {
        return this.id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return this.username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return this.password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }
}
