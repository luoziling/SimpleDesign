package com.wzb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Satsuki
 * @time 2019/10/30 20:52
 * @description:
 *
 * 封装与redis交互的当前用户名和模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class UserAndModel {
    private String username;
    private String pronectName;
}
