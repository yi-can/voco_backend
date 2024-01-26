package com.voco_task.repository.entity;


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
public class Restaurant extends BaseEntity{
    @Id
    private String restaurantId;
    private String name;
    private String avatar;
    private List<String> phone;//birden fazla restaurant şubesi olabileceği düşünülmüştür!!!
    private List<String> address;
    private List<String> latitude;
    private List<String> longitude;
    private List<String> foodId;
    private List<String> commentId;
    private List<String> likeId;
    private List<String> orders;
    private String kitchen;
}
