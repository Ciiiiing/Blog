package com.cing.blog.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static final List<String> findBetweenStr(String str, String leftstr, String rightstr) {
        List<String> list = new ArrayList<>();
        if(!str.isEmpty()){
            int leftIndex = str.indexOf(leftstr);//左文本起始位置
            int leftlength = leftstr.length();//左文本长度
            int rightIndex = 0;
            String temp = "";
            while (leftIndex != -1) {
                rightIndex = str.indexOf(rightstr, leftIndex + 1);
                if (rightIndex == -1) {
                    break;
                }
                temp = str.substring(leftIndex + leftlength, rightIndex);
                list.add(temp);
                leftIndex = str.indexOf(leftstr, rightIndex + 1);
            }
        }
        return list;
    }
}
