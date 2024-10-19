package cn.lmu.candy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = "handler")
public class UserInfo {
    private int id;
    private String userName;
    private String password;
    private String gender;
    private String email;
    private String telephone;
    private String introduce;
    private int state;
    private List<Role> roleList;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date registTime;
    private Date lastPasswordResetDate;
    private String token;

}
