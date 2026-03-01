package com.joaopscalazans.restaurante_api.domain.diningtable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joaopscalazans.restaurante_api.domain.reserve.Reserve;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_dining_table")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DiningTableStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "diningTable")
    private List<Reserve> reserves;

}
