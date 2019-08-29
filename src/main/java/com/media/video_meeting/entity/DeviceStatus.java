package com.media.video_meeting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 1.0
 * @user ken
 * @date 2019/8/28 17:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceStatus implements Serializable {

    private Integer total;//总设备数
    private Integer normal;//正常数
    private Integer fault;//故障数
    private Integer offline;//离线数
    private Integer alert;//警告
}
