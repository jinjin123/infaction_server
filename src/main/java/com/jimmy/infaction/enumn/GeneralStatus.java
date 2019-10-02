package com.jimmy.infaction.enumn;

/**
 * @author jimmy on 2019/9/30 7:45
 */
public  enum GeneralStatus {
	FAILD(-100,"提交失败"),
	NOT_FIND(101,"找不到chrome");

	private  int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.name() + "(" + this.code + "," + this.message + ")";
	}
	/**
	 * 根据code获取message
	 * @param code
	 * @return
	 */
	public static String getMessage(int code){
		//通过enum.values()获取所有的枚举值
		for(GeneralStatus codeAndMessage : GeneralStatus.values()){
			//通过enum.get获取字段值
			if(codeAndMessage.getCode() == code){
				return codeAndMessage.message;
			}
		}
		return null;
	}
	/**
	 * 根据code获取CodeAndMessage
	 * @param code
	 * @return
	 */
	public static GeneralStatus getCodeAndMessage(int code){
		for(GeneralStatus codeAndMessage : GeneralStatus.values()){
			if(codeAndMessage.getCode() == code){
				return codeAndMessage;
			}
		}
		return null;
	}
	private GeneralStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

}
