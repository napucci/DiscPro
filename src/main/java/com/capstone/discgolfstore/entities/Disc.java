package com.capstone.discgolfstore.entities;

import com.capstone.discgolfstore.dtos.DiscDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "discs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;

    @Column
    private String mold;

    @Column
    private String type;

    @Column
    private int speed;

    @Column
    private int glide;

    @Column
    private int turn;

    @Column
    private int fade;

    @Column
    private double price;

    @Column
    private String photo;

    @OneToOne
    @JsonBackReference
    private CartDisc cartDisc;

    public Disc(DiscDto discDto) {
        if (discDto.getBrand() != null) {
            this.brand = discDto.getBrand();
        }
        if (discDto.getMold() != null) {
            this.mold = discDto.getMold();
        }
        if (discDto.getType() != null) {
            this.type = discDto.getType();
        }
        if (discDto.getPhoto() != null) {
            this.photo = discDto.getPhoto();
        }
        if(discDto.getFade() > -5){
            this.fade = discDto.getFade();
        }
        if(discDto.getGlide() > -5) {
            this.glide = discDto.getGlide();
        }
        if(discDto.getTurn() > -5) {
            this.turn = discDto.getTurn();
        }
        if(discDto.getSpeed() > -5) {
            this.speed = discDto.getSpeed();
        }
        if(discDto.getPrice() > 0.00) {
            this.price = discDto.getPrice();
        }
    }

    }


