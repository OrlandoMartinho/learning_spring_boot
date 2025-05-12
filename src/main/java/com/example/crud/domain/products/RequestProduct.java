package com.example.crud.domain.products;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RequestProduct(
     String id,
	@NotBlank(message = "Name is required") String name,
	@NotNull(message = "Price is required") Integer price_in_cents
) {

  
  
}
