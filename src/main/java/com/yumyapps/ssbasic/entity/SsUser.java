package com.yumyapps.ssbasic.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class SsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    private String[] authorities;

    @Column(name = "acc_enable")
    private Boolean enable;

    @Column(name = "acc_notLock")
    private Boolean notLocked;
    
}
