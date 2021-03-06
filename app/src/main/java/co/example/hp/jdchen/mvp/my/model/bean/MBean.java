package co.example.hp.jdchen.mvp.my.model.bean;

/**
 * Created by hp on 2018/7/23.
 */

public class MBean {

    /**
     * msg : 获取用户信息成功
     * code : 0
     * data : {"age":null,"appkey":"39d81339d5f720f3","appsecret":"08265FF0A6608A8767E1AF9ADA398495","createtime":"2018-07-23T15:57:02","email":null,"fans":0,"follow":0,"gender":null,"icon":"https://www.zhaoapi.cn/images/1532328244789photo.png","latitude":null,"longitude":null,"mobile":"17610201318","money":null,"nickname":"小忍者","password":"7792A1F7048ECB10FE716CE6FD38E884","praiseNum":null,"token":"86B23D3CBFE753B058649C67EB2161AC","uid":15727,"userId":null,"username":"17610201318"}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : 39d81339d5f720f3
         * appsecret : 08265FF0A6608A8767E1AF9ADA398495
         * createtime : 2018-07-23T15:57:02
         * email : null
         * fans : 0
         * follow : 0
         * gender : null
         * icon : https://www.zhaoapi.cn/images/1532328244789photo.png
         * latitude : null
         * longitude : null
         * mobile : 17610201318
         * money : null
         * nickname : 小忍者
         * password : 7792A1F7048ECB10FE716CE6FD38E884
         * praiseNum : null
         * token : 86B23D3CBFE753B058649C67EB2161AC
         * uid : 15727
         * userId : null
         * username : 17610201318
         */

        private Object age;
        private String appkey;
        private String appsecret;
        private String createtime;
        private Object email;
        private int fans;
        private int follow;
        private Object gender;
        private String icon;
        private Object latitude;
        private Object longitude;
        private String mobile;
        private Object money;
        private String nickname;
        private String password;
        private Object praiseNum;
        private String token;
        private int uid;
        private Object userId;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
