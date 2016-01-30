package configuration.commandConfiguration;

public class ButtonCommand {
	public static final String[] login = { "loginAdmin", "loginTeacher", "loginUser", "loginOut" };
	public static final String[] admin = { "adminNext", "adminNew", "adminUpdate", "adminDelete", "adminPrev", "adminOut", "examAdd", "examNew", "examUpdate", "examDelete" };
	public final static String[] newQuestion = { "newChoose", "newTrueOrFalse", "newComplete", "newConnect", "newMathProblem", "newModelProblem", "newBack" };
	public final static String[] exam = { "examOpenQuestion", "examDeleteQuestion", "examDone", "examBack", "oldExamAdd" };
	public static final String[] choose = { "chooseBack", "chooseDone", "next", "exit" };
	public static final String[] trueOrFalse = { "trueOrFalseDone", "trueOrFalseBack" };
	public static final String[] complete = { "completeDone", "completeBack" };
	public static final String[] connect = { "connectDone", "connectBack" };
	public static final String[] mathProblem = { "mathProblemDone", "mathProblemBack" };
	public static final String[] model = { "modelProblemDone", "modelProblemBack" };
}
