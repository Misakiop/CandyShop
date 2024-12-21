package cn.lmu.candy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@JsonIgnoreProperties(value ={"handler"})
public class OrderCondition {
    private String id;
    private float money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private Integer payState;
    private Integer orderState;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date endDate;
    private UserInfo userInfo;
}
