package br.com.jonatas.aula.criptografia;

import org.jasypt.util.text.BasicTextEncryptor;

public class Criptografia {

	private BasicTextEncryptor encryptor;

	public Criptografia(BasicTextEncryptor basicTextEncryptor) {
		encryptor = basicTextEncryptor;
		encryptor.setPassword("criptografia");
	}

	public String encrypt(String texto) {
		return encryptor.encrypt(texto);
	}

	public String decrypt(String texto) {
		return encryptor.decrypt(texto);
	}

}
