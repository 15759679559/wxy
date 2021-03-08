package com.example.wxy;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;


public class Test {
    public static void main(String[] args) {
//        String test="{'license_code':'22','id_code':'12','result_msg':'SUCCESS','result_code':'SUCCESS','errors':[]}";
        String test = "{\"ack_code\":\"SUCCESS\",\"errors\":[],\"sign\":null,\"sign_method\":null,\"timestamp\":null,\"correlation_id\":\"555\",\"response_id\":\"666\",\n" +
                "\"data\":{\"responseData_list\":[\n" +
                "{\"license_code\":\"12\",\"id_code\":\"22\",\"result_msg\":\"SUCCESS\",\"result_code\":\"SUCCESS\",\"errors\":[]}]}}";
        JSONObject jsonTest = JSONObject.parseObject(test);
//        String newParam = test.replaceAll("'","\"");
        String str = "{\"labelId\":\"001\",\"labelId\":\"022\",\"labelId\":\"003\",\"labelId\":\"004\"}";
        String[] split = str.split(",");

//        JSONObject jsonTest = null;
//        for (String s : split) {
//            String[] split1 = s.split(":");
//            jsonTest.put(split1[0],JSONObject.parseObject(s));
//        }

        JSONObject jsonData = JSONObject.parseObject(jsonTest.get("data").toString());
        JSONArray jsonResponseDataList = JSONObject.parseArray(jsonData.get("responseData_list").toString());

        for (int i = 0; i < jsonResponseDataList.size(); i++) {
            JSONObject jsonO = JSONObject.parseObject(jsonResponseDataList.get(i).toString());
            System.out.println(jsonO.get("id_code"));
        }

    }
}
