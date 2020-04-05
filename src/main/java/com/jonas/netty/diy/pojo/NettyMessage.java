package com.jonas.netty.diy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NettyMessage
 *
 * @author shenjy
 * @version 1.0
 * @date 2020-04-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NettyMessage {
    private Header header;
    private Object body;
}
