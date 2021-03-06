package constants;

public enum Message {

	MAIN_DATABASE_PATH("E:\\Git\\AutoTest\\databases\\auto_test.db"),
	STUDENT_DATABASE_PATH("E:\\Git\\AutoTest\\databases\\student.db"),
	OPEN_DATABASE_CONNECTION_ERROR("خطأ أثناء محاولة الاتصال بقاعدة البيانات"),
	OPEN_DATABASE_SESSION_ERROR("خطأ أثناء محاولة فتح قاعدة البيانات"),

	GENERAL_BACK_GROUND_PATH("Images/GBG.jpg"),
	QUESTION_BACK_GROUND_PATH("Images/QBG.jpg"),

	PASSWORD_WRONG("كلمة السر خطأ"),
	ASK_ADMIN_PASSWORD("أدخل كلمة السر للأدمن"),
	ADMIN_PASSWORD("admin"),
	ASK_TEACHER_PASSWORD("أدخل كلمة السر للمدرس"),
	TEACHER_PASSWORD("teacher"),

	FIND_ALL_UNITS_ERROR("خطأ أثناء محاولة احضار الوحدات من قاعدة البيانات"),
	FIND_UNIT_BY_ID_ERROR("خطأ أثناء محاولة ايجاد وحده من قاعدة البيانات بوسطة رقم الوحده"),
	FIND_UNIT_BY_DESCRIPTION_ERROR("خطأ أثناء محاولة ايجاد وحده من قاعدة البيانات بوسطة وصف الوحده"),
	FIND_UNIT_BY_PASSWORD_ERROR("خطأ أثناء محاولة ايجاد وحده من قاعدة البيانات بوسطة كلمة سر الوحده"),
	SAVE_UNIT_ERROR("خطأ أثناء محاولة حفظ وحده في قاعدة البيانات"),
	UPDATE_UNIT_ERROR("خطأ أثناء محاولة تعديل وحده في قاعدة البيانات"),
	DELETE_UNIT_ERROR("خطأ أثناء محاولة مسح وحده من قاعدة البيانات"),
	UNIT_DESCRIPTION_INVALID_ERROR("اسم الوحده فارغ"),
	UNIT_PASSWORD_INVALID_ERROR("كلمة سر الوحده فارغ"),
	UNIT_DESCRIPTION_ALLREADY_EXIST_ERROR("اسم الوحده مستخدم بالفعل"),
	UNIT_PASSWORD_ALLREADY_EXIST_ERROR("برجاء ادخال كلمة سر اخري"),
	UNIT_SAVED_SUCCESSFULLY("تم حفظ الوحده بنجاح"),
	UNIT_UPDATED_SUCCESSFULLY("تم تعديل الوحده بنجاح"),
	ASK_UNIT_DESCRIPTION("أدخل اسم الوحده"),
	ASK_UNIT_NEW_PASSWORD("ادخل كلمة السر الجديده للوحده"),
	ASK_UNIT_PASSWORD("أدخل كلمة السر للوحده"),
	DELETE_UNIT_CONFIRM("هل تريد مسح الوحده ؟"),
	DELETE_UNIT_SUCCESSFULLY("تم مسح الوحده بنجاح"),
	
	DELETE_QUESTION_CONFIRM("هل تريد مسح السؤال ؟"),
	
	INIT_CONNECT("وصل"),
	
	FIND_ALL_QUESTION_ERROR("خطأ أثناء احضار اسئلة من قاعدة البيانات"),
	FIND_QUESTION_BY_ID_ERROR("خطأ أثناء محاولة ايجاد سؤال  من قاعدة البيانات بوسطة رقم السؤال"),
	FIND_QUESTION_BY_DESCRIPTION_ERROR("خطأ أثناء محاولة ايجاد سؤال من قاعدة البيانات بوسطة وصف السؤال"),
	SAVE_QUESTION_ERROR("خطأ أثناء محاولة حفظ سؤال في قاعدة البيانات"),
	DELETE_QUESTION_ERROR("خطأ أثناء محاولة مسح سؤال من قاعدة البيانات"),
	
	FIND_ALL_EXAMS_ERROR("خطأ أثناء محاولة احضار الامتحانات من قاعدة البيانات"),
	FIND_EXAM_BY_ID_ERROR("خطأ أثناء محاولة ايجاد امتحان من قاعدة البيانات بواسطة رقم الامتحان"),
	FIND_EXAM_BY_NAME_ERROR("خطأ أثناء محاولة ايجاد امتحان من قاعدة البيانات بواسطة وصف الامتحان"),
	ADD_EXAM_QUESTION_ERROR("خطأ أثناء محاولة اضافة سؤال لامتحان في قاعدة البيانات"),
	DELETE_EXAM_QUESTION_ERROR("خطأ أثناء محاولة مسح سؤال من امتحان من قاعدة البيانات"),
	DELETE_EXAMS_QUESTION_ERROR("خطأ أثناء محاولة مسح سؤال من الامتحانات المدرج فيها من قاعدة البيانات"),
	SAVE_EXAM_ERROR("خطأ أثناء محاولة حفظ الامتحان في قاعدة البيانات"),
	UPDATE_EXAM_ERROR("خطأ أثناء محاولة تعديل امتحان في قاعدة البيانات"),
	DELETE_EXAM_ERROR("خطأ أثناء محاولة مسح امتحان من قاعدة البيانات"),
	SAVE_EXAM_PERCENTAGE_ERROR("خطأ أثناء محاولة حفظ نسبة امتحان اخر في قاعدة البيانات"),
	DELETE_EXAM_PERCENTAGE_ERROR("خطأ أثناء محاولة مسح نسبة امتحان اخر من قاعدة البيانات"),
	DELETE_EXAMS_PERCENTAGE_BY_EXAM_ID_ERROR("خطأ أثناء محاولة مسح نسب امتحانات اخري من قاعدة البيانات"),
	EXAM_NAME_ALLREADY_EXIST_ERROR("اسم الامتحان مستخدم بالفعل"),
	ASK_EXAM_PASSWORD("أدخل كلمة السر للامتحان"),
	
	FIND_ALL_STUDENTS_ERROR("خطأ أثناء محاولة احضار الطلاب من قاعدة البيانات"),
	SAVE_STUDENT_ERROR("خطأ أثناء محاولة حفظ طالب في قاعدة البيانات"),
	ASK_STUDENT_ID("أدخل رقم جلوس الطالب"),
	STUDENT_ID_NOT_NUMBER_ERROR("خطأ رقم الجلوس فارغ او ليس رقم"),
	
	FINAL_SHEET_PATH_CHOOSER_MESSAGE("أختر المكان لدمج الملفات"),
	FINAL_SHEET_HEADER_ID_COLUMN("رقم الجلوس"),
	FINAL_SHEET_HEADER_MARK("الدرجه"),
	FINAL_SHEET_HEADER_EXAM_NAME("اسم الامتحان"),
	
	EXIST_SYSTEM_CONFIRM_MESSAGE("هل تريد الخروج من البرنامج ؟");

	private String value;

	private Message(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}