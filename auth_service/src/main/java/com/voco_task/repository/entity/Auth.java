package com.voco_task.repository.entity;

import com.voco_task.repository.enums.ERole;
import com.voco_task.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Auth extends BaseEntity{
    @Id
    private String authId;
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String profilePicture;
    private String gender;
    private List<String> address;
    private ERole role;
    private EStatus status=EStatus.ACTIVE;
}
