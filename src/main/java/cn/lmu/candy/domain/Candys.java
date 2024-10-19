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
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date addtime;
    int state;
    String imguid;
}
