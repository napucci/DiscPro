package com.capstone.discgolfstore.entities;

import com.capstone.discgolfstore.dtos.CartDiscDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cartdiscs" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDisc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @ManyToOne
    @JsonBackReference
    private User user;

//   @OneToOne
//           (mappedBy = "user",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
//    @JsonManagedReference
    @Column
    private int discId;

    public CartDisc(CartDiscDto cartDiscDto){
       if(cartDiscDto.getQuantity() > 0){
           this.quantity = cartDiscDto.getQuantity();
       }
       if(cartDiscDto.getDiscId() > 0){
           this.discId = cartDiscDto.getDiscId();
       }
       else{
           this.discId = 1;
       }
    }


}
