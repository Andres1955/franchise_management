package com.franchise.management.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("franchises")
public class Franchise {
    @Id
    private Long id;
    private String name;

    @Builder.Default
    private Set<Branch> branches = new HashSet<>();
}
