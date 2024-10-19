package cn.lmu.candy.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Token implements Serializable {
    private static final long serialVersionUID = 4784951536404964122L;
    private String token;   //要发送回客户端的令牌
    public Token(String token) {
        this.token = token;
    }
}
