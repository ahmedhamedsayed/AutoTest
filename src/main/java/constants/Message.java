package constants;

public enum Message {
	OPEN_DATABASE_CONNECTION_ERROR("خطأ أثناء محاولة الاتصال بقاعدة البيانات"),
	OPEN_DATABASE_SESSION_ERROR("خطأ أثناء محاولة فتح قاعدة البيانات"),
	
	PASSWORDWRONG("كلمة السر خطأ"),
	ASK_ADMIN_PASSWORD("أدخل كلمة السر للأدمن"),
	ADMIN_PASSWORD("admin"),
	ASK_TEACHER_PASSWORD("أدخل كلمة السر للمدرس"),
	TEACHER_PASSWORD("teacher"),
	
	GET_ALL_UNIT_ERROR("خطأ أثناء محاولة احضار الوحدات من قاعدة البيانات");
	
	private String value;
	
	private Message(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
