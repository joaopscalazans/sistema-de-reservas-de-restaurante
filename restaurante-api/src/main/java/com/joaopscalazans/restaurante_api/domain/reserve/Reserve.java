package com.joaopscalazans.restaurante_api.domain.reserve;

import java.time.LocalDateTime;

import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTable;
import com.joaopscalazans.restaurante_api.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_reserve")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus status;
    
    @ManyToOne
    @JoinColumn(name = "user_id",
        referencedColumnName = "id",
        nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "dining_table_id",
        referencedColumnName = "id",
        nullable = false)
    private DiningTable diningTable;

    public Reserve(LocalDateTime date,User user,DiningTable diningTable,ReserveStatus status){
        this.date = date;
        this.user = user;
        this.diningTable = diningTable;
        this.status = status;
    }

}
