package com.absolute.bookrecommend.result;

public enum ResultCode {
	SUCCESS(200),
	FAIL(400),
	EXITED(100),
	UNAUTHORIZED(401),
	NOT_FOUND(404),
	INTERNAL_SERVER_ERROR(500);

	public int code;

	ResultCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
