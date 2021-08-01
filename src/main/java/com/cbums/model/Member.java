package com.cbums.model;


import com.cbums.type.UserRoleType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;

@Entity
@Getter
@Setter
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickName;

    private Integer registerNumber;

    @Column(nullable = false)
    private Integer classNumber;

    @Column(nullable = false)
    private String department;

    private String profileImage;

    private String introduce;

    @Enumerated(EnumType.STRING)
    private UserRoleType userRoleType = UserRoleType.VISITANT;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
