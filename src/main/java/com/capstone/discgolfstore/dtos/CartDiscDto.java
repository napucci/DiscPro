package com.capstone.discgolfstore.dtos;
import com.capstone.discgolfstore.entities.CartDisc;
import com.capstone.discgolfstore.entities.Disc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDiscDto {

    private Long id;
    private int quantity;
    private Disc disc;

    public CartDiscDto(CartDisc cartDisc){
        if(cartDisc.getQuantity() > 0){
            this.quantity = cartDisc.getQuantity();
        }
    }


}
