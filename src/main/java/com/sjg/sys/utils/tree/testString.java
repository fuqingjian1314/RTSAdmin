package com.sjg.sys.utils.tree;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by fuqingjian on 2016/12/4.
 */
public class testString {
    public static void main(String[] args) {
        String str="{\"q-1-1\":\"\",\"q-1-2\":\"\",\"q-1-3\":\"\",\"q-1-4\":\"\",\"q-1-5\":\"\",\"q-1-6\":\"\",\"q-1-7\":\"\",\"q-1-8\":\"\",\"q-1-9\":\"\",\"q-1-10\":\"\",\"q-1-11\":\"\",\"q-1-12\":\"\",\"q-1-13\":\"\",\"q-1-14\":\"\",\"q-2-1\":\"\",\"q-2-2\":\"\",\"q-2-3\":\"\",\"q-2-4\":\"\",\"q-2-5\":\"\",\"q-2-6\":\"\",\"q-2-7\":\"\",\"q-2-8\":\"\",\"q-2-9\":\"\",\"q-2-10\":\"\",\"q-2-11\":\"\",\"q-2-12\":\"\",\"q-2-13\":\"\",\"q-2-14\":\"\",\"q-3-1\":\"\",\"q-3-2\":\"\",\"q-3-3\":\"\",\"q-3-4\":\"\",\"q-3-5\":\"\",\"q-3-6\":\"\",\"q-3-7\":\"\",\"q-3-8\":\"\",\"q-3-9\":\"\",\"q-3-10\":\"\",\"q-3-11\":\"\",\"q-3-12\":\"\",\"q-3-13\":\"\",\"q-3-14\":\"\",\"q-4-1\":\"\",\"q-4-2\":\"\",\"q-4-3\":\"\",\"q-4-4\":\"\",\"q-4-5\":\"\",\"q-4-6\":\"\",\"q-4-7\":\"\",\"q-4-8\":\"\",\"q-4-9\":\"\",\"q-4-10\":\"\",\"q-4-11\":\"\",\"q-4-12\":\"\",\"q-4-13\":\"\",\"q-4-14\":\"\",\"q-5-1\":\"\",\"q-5-2\":\"\",\"q-5-3\":\"\",\"q-5-4\":\"\",\"q-5-5\":\"\",\"q-5-6\":\"\",\"q-5-7\":\"\",\"q-5-8\":\"\",\"q-5-9\":\"\",\"q-5-10\":\"\",\"q-5-11\":\"\",\"q-5-12\":\"\",\"q-5-13\":\"\",\"q-5-14\":\"\",\"z-1-d\":\"\",\"z-1-x\":\"\",\"z-2-d\":\"\",\"z-2-s\":\"\",\"z-3-l\":\"\",\"z-3-f\":\"\",\"z-3-h\":\"\",\"z-4-q\":\"on\",\"z-4-z\":\"on\",\"z-4-0\":\"\",\"z-4-1\":\"\",\"z-4-2\":\"\",\"z-4-3\":\"\",\"z-4-4\":\"\"}";
        JSONObject o=JSONObject.parseObject(str);
        System.out.println(String.valueOf(o.get("z-4-h")));
    }
}
