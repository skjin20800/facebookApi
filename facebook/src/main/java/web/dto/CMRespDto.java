package web.dto;

public class CMRespDto<T> {
	private String msg; // 메세지
	private int statusCode; //-1 실패, 1성공
	private T data; //결과 데이터
	
	public CMRespDto(int statusCode,  String msg, T data) {
		this.statusCode = statusCode;
		this.data = data;
		this.msg = msg;
	}
	
	public CMRespDto(int statusCode, T data) {
		this.statusCode = statusCode;
		this.data = data;
	}
	
	public CMRespDto(int statusCode, String msg) {
		this.statusCode = statusCode;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	
}