package com.coconet.server.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    @Id
    @NotNull
    private int Num;
    private String profile;

}