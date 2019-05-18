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
    private String volume;
    private String intercomenable;
    private String cameraintercomenable;
    private String updatestatus;
    private String dhcpstatus;
    private String productsmodel;
    private Integer status;
    private String limits;

    @TableField(exist = false)
    private List<ClientMsg> clientMsgs;
    private static final long serialVersionUID = 1L;


}