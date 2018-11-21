package com.hbck.dictionary;

import java.util.List;

/**
 * @Date 2018-11-21.
 */
public class ResultBean {


    /**
     * translation : ["英语"]
     * basic : {"us-phonetic":"'ɪŋɡlɪʃ","phonetic":"'ɪŋɡlɪʃ","uk-phonetic":"'ɪŋɡlɪʃ","explains":["n. 英语；英国人；英文；英格兰人","adj. 英国人的；英国的；英文的","vt. 把\u2026译成英语"]}
     * query : English
     * errorCode : 0
     * web : [{"value":["英语","语言","英文"],"key":"English"},{"value":["气管炎","软骨病","英国病"],"key":"English disease"},{"value":["英语语法","英语语法","英文文法"],"key":"English grammar"}]
     */

    public BasicBean basic;
    public String query;
    public int errorCode;
    public List<String> translation;
    public List<WebBean> web;

    public static class BasicBean {
        /**
         * us-phonetic : 'ɪŋɡlɪʃ
         * phonetic : 'ɪŋɡlɪʃ
         * uk-phonetic : 'ɪŋɡlɪʃ
         * explains : ["n. 英语；英国人；英文；英格兰人","adj. 英国人的；英国的；英文的","vt. 把\u2026译成英语"]
         */

        public String usphonetic;
        public String phonetic;
        public String ukphonetic;
        public List<String> explains;

        @Override
        public String toString() {
            return "BasicBean{" +
                    "usphonetic='" + usphonetic + '\'' +
                    ", phonetic='" + phonetic + '\'' +
                    ", ukphonetic='" + ukphonetic + '\'' +
                    ", explains=" + explains +
                    '}';
        }
    }

    public static class WebBean {
        /**
         * value : ["英语","语言","英文"]
         * key : English
         */

        public String key;
        public List<String> value;

        @Override
        public String toString() {
            return "WebBean{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "ResultBean{" +
                "basic=" + basic +
                ", query='" + query + '\'' +
                ", errorCode=" + errorCode +
                ", translation=" + translation +
                ", web=" + web +
                '}';
    }
}
