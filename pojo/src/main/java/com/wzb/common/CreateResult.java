package com.wzb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Satsuki
 * @time 2020/4/10 16:11
 * @description:
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class CreateResult {
    boolean flag;
    Integer projectID;
    String reviews;

    @Override
    public String toString() {
        return "CreateResult{" +
                "flag=" + flag +
                ", projectID=" + projectID +
                ", reviews='" + reviews + '\'' +
                '}';
    }
}
