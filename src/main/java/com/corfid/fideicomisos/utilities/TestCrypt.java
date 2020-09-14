package com.corfid.fideicomisos.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//esta clase es solo para encriptar y mostrar una clave por consola
public class TestCrypt {
	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("123"));//esto es para generar codificada la clave "user" correrlo como run java aplication
	}
}
