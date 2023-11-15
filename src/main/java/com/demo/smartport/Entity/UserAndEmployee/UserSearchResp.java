package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSearchResp implements Serializable {
    private boolean flag;
    private int grade;
}
