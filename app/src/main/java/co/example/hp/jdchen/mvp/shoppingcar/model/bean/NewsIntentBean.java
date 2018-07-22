package co.example.hp.jdchen.mvp.shoppingcar.model.bean;

import java.util.List;

/**
 * Created by hp on 2018/7/20.
 */

public class NewsIntentBean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2018-07-20T11:17:29","orderid":11113,"price":5199,"status":0,"title":"订单测试标题15727","uid":15727}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2018-07-20T11:17:29
         * orderid : 11113
         * price : 5199.0
         * status : 0
         * title : 订单测试标题15727
         * uid : 15727
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
