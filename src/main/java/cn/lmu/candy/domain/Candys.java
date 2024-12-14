package cn.lmu.candy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candys {
    private int id;
    private String name;
    private String comment;
    private String category;
    private float price;
    private int num;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    private boolean state;
    private String imguid;
}
