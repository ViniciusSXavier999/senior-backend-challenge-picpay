package br.com.vx.picpay.senior.backend.challenge.dtos;

import java.math.BigDecimal;

import br.com.vx.picpay.senior.backend.challenge.domain.user.UserType;

public record UserDTO (String firstName,String  lastName, String document, BigDecimal balance, String email, 
		String password, UserType userType) {
	
}