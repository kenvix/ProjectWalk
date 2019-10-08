package com.kenvix.walk.pojo.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecognizerRequest {

    /**
     * log_id : 1780960375137620104
     * result : [{"calorie":"99","has_calorie":true,"high_salt_and_oil":true,"name":"青椒肉丝","probability":"0.698383"},{"calorie":"101","has_calorie":true,"high_salt_and_oil":true,"name":"椒炒肉丝","probability":"0.0932402"},{"calorie":"154","has_calorie":true,"high_salt_and_oil":true,"name":"鱼香肉丝","probability":"0.0458037"},{"calorie":"99","has_calorie":true,"high_salt_and_oil":true,"name":"辣椒炒肉","probability":"0.0353788"},{"calorie":"174","has_calorie":true,"high_salt_and_oil":true,"name":"肉丝","probability":"0.0298944"}]
     * result_num : 5
     */

    @SerializedName("log_id")
    private long logId;
    @SerializedName("result_num")
    private int resultNum;
    @SerializedName("result")
    private List<ResultBean> result;

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public int getResultNum() {
        return resultNum;
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * calorie : 99
         * has_calorie : true
         * high_salt_and_oil : true
         * name : 青椒肉丝
         * probability : 0.698383
         */

        @SerializedName("calorie")
        private String calorie;
        @SerializedName("has_calorie")
        private boolean hasCalorie;
        @SerializedName("high_salt_and_oil")
        private boolean highSaltAndOil;
        @SerializedName("name")
        private String name;
        @SerializedName("probability")
        private String probability;

        public String getCalorie() {
            return calorie;
        }

        public void setCalorie(String calorie) {
            this.calorie = calorie;
        }

        public boolean isHasCalorie() {
            return hasCalorie;
        }

        public void setHasCalorie(boolean hasCalorie) {
            this.hasCalorie = hasCalorie;
        }

        public boolean isHighSaltAndOil() {
            return highSaltAndOil;
        }

        public void setHighSaltAndOil(boolean highSaltAndOil) {
            this.highSaltAndOil = highSaltAndOil;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProbability() {
            return probability;
        }

        public void setProbability(String probability) {
            this.probability = probability;
        }
    }
}
