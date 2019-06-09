package com.media.video_meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMsg implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userid;
    private String id;
    private Integer gid = 1;
    private String terminalname;
    private String mac;
    private String ip;
    private String serverip;
    private String volume = "10";
    private String intercomenable;
    private String cameraintercomenable;
    private String updateStatus;
    private String dhcpstatus;
    private String productsModel;
    private Integer status;
    private String limits;

    @TableField(exist = false)
    private List<ClientMsg> clientMsgs;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}