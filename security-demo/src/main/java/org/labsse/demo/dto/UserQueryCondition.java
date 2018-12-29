package org.labsse.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijiechu
 * @create on 2018/12/21
 * @description 用户查询条件DTO类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryCondition {

    private String username;
    /**
     * 年龄上界条件
     */
    @ApiModelProperty("用户终止年龄")
    private int ageTo;
    /**
     * 年龄下界条件
     */
    @ApiModelProperty("用户起始年龄")
    private int ageFrom;
}
