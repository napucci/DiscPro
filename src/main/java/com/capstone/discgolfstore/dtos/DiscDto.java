package com.capstone.discgolfstore.dtos;
import com.capstone.discgolfstore.entities.Disc;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscDto implements Serializable {
    private Long id;
    private String brand;
    private String mold;
    private String type;
    private int speed;
    private int glide;
    private int turn;
    private int fade;
    private double price;
    private String photo;



    public DiscDto(Disc disc){
        if(disc.getId() != null){
            this.id = disc.getId();
        }
        if(disc.getBrand() != null){
            this.brand = disc.getBrand();
        }
        if(disc.getMold() != null){
            this.mold = disc.getMold();
        }
        if(disc.getType() != null){
            this.type = disc.getType();
        }
        if(disc.getPhoto() != null){
            this.photo = disc.getPhoto();
        }
        if(disc.getFade() > -5){
            this.fade = disc.getFade();
        }
        if(disc.getGlide() > -5) {
            this.glide = disc.getGlide();
        }
        if(disc.getTurn() > -5) {
            this.turn = disc.getTurn();
        }
        if(disc.getSpeed() > -5) {
            this.speed = disc.getSpeed();
        }
        if(disc.getPrice() > 0.00) {
            this.price = disc.getPrice();
        }

    }

}
