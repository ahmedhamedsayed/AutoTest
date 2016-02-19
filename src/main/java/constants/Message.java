package constants;

public enum Message {
	MAIN_DATABASE_PATH("E:\\Git\\AutoTest\\databases\\auto_test.db"),
	STUDENT_DATABASE_PATH("E:\\Git\\AutoTest\\databases\\student.db"),
	OPEN_DATABASE_CONNECTION_ERROR("خطأ أثناء محاولة الاتصال بقاعدة البيانات"),
	OPEN_DATABASE_SESSION_ERROR("خطأ أثناء محاولة فتح قاعدة البيانات"),
	
	GENERAL_BACK_GROUND_PATH("Images/GBG.jpg"),
	QUESTION_BACK_GROUND_PATH("Images/QBG.jpg"),
	
	PASSWORDWRONG("كلمة السر خطأ"),
	ASK_ADMIN_PASSWORD("أدخل كلمة السر للأدمن"),
	ADMIN_PASSWORD("admin"),
	ASK_TEACHER_PASSWORD("أدخل كلمة السر للمدرس"),
	TEACHER_PASSWORD("teacher"),
	
	GET_ALL_UNIT_ERROR("خطأ أثناء محاولة احضار الوحدات من قاعدة البيانات"),
	
	FINAL_SHEET_PATH_CHOOSER_MESSAGE("أختر المكان لدمج الملفات"),
	FINAL_SHEET_HEADER_ID_COLUMN("رقم الجلوس"),
	FINAL_SHEET_HEADER_MARK("الدرجه"),
	FINAL_SHEET_HEADER_EXAM_NAME("اسم الامتحان");
	
	private String value;
	
	private Message(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
