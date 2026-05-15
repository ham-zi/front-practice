package com.kh.web.ajax.model.dto;

public class ResponseDto {
	private String code;
	private String message;
	private Object data; //private T data (제네릭)
	
	public ResponseDto(String code, String message, Object data) {
		super();
		this.code = code; //응답메세지의 식별코드
		this.message = message; //응답메세지
		this.data = data; //응답해서 돌아갈 타입
	}
	
}
