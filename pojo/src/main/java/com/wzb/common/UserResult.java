package com.wzb.common;

import com.wzb.pojo.EcUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Satsuki
 * @time 2020/4/8 18:36
 * @description: 整合用户登陆信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class UserResult {
    Boolean flag;
    EcUser user;
    String reviews;

    @Override
    public String toString() {
        return "UserResult{" +
                "flag=" + flag +
                ", user=" + user +
                ", reviews='" + reviews + '\'' +
                '}';
    }
}
