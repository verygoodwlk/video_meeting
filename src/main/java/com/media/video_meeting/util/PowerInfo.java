package com.media.video_meeting.util;

/**
 * @Author ken
 * @Time 2019/3/12 11:11
 * @Version 1.0
 */
public enum PowerInfo {

    POWER1(1,"定时音乐"),
    POWER2(2,"定时采集"),
    POWER3(3,"语音合成"),
    POWER4(4,"实时寻呼"),
    POWER5(5,"上传曲目"),
    POWER6(6,"实时音乐");

    private int id;
    private String showinfo;

    PowerInfo(int id, String showinfo) {
        this.id = id;
        this.showinfo = showinfo;
    }

    //根据id查找对应的权限
    public static PowerInfo getPower(Integer id){

        PowerInfo[] values = PowerInfo.values();
        for (PowerInfo powerInfo : values){
            if(powerInfo.getId() == id){
                return powerInfo;
            }
        }

        return null;
    }

    //将所有权限集合转换成json字符串
    public static String toJson(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (PowerInfo powerInfo : values()){
            if(stringBuilder.length() > 1){
                stringBuilder.append(",");
            }
            stringBuilder.append(powerInfo.toString());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShowinfo() {
        return showinfo;
    }

    public void setShowinfo(String showinfo) {
        this.showinfo = showinfo;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ", \"showinfo\":\"" + showinfo + "\"}";
    }
}
