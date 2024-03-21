package com.cowrycode.smileapp.domains.easyfeed;

import com.cowrycode.smileapp.domains.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalData extends BaseEntity {
    private String userID;
    private String journal;
}
