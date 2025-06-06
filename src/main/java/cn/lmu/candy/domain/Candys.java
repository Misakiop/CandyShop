package cn.lmu.candy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Candys {
    private String id;
    private String name;
    private String comment;
    private float price;
    private int num;
    private String kgs;
    private String size;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date creationdate;
    private int expirationdate;
    private String storagemethod;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private Date addtime;
    private int state;
    private String imguid;
    private Category category;
}
