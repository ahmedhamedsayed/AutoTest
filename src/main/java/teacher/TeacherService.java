package teacher;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import util.shape.Chooser;
import util.shape.PDFCell;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import configuration.databaseConfiguration.DatabaseEngine;
import exam.Exam;
import exam.ExamRepository;
import exam.examexecute.Student;
import exam.examexecute.StudentRepository;

public class TeacherService {

	private static TeacherService teacherService;

	public static synchronized TeacherService getInstance() {
		if (teacherService == null)
			return teacherService = new TeacherService();
		return teacherService;
	}

	public void generateFinalSheet() {
		String path = Chooser.chooser(TeacherMessage.finalSheetPathChooserMessage);
		HashMap<Integer, HashMap<String, Integer>> exams = new HashMap<Integer, HashMap<String,Integer>>();
		File folder = new File(path);
		File[] databases = folder.listFiles();
		for (int i = 0; i < databases.length; i++) {
			if (!databases[i].isFile())
				continue;
			String databasePath = path + "\\" + databases[i].getName();
			DatabaseEngine.getInstance().configureDatabase("studentPersistence.xml", databasePath);
			List<Student> currentStudents = StudentRepository.getInstance().findAll(databasePath);
			if (currentStudents == null)
				continue;
			for (Student student : currentStudents) {
				if (!exams.containsKey(student.getExamId())) {
					exams.put(student.getExamId(), new HashMap<String, Integer>());
					exams.get(student.getExamId()).put(student.getStudentId(), student.getMark());
				} else if (!exams.get(student.getExamId()).containsKey(student.getStudentId())) {
					exams.get(student.getExamId()).put(student.getStudentId(), student.getMark());
				} else {
					Integer minMark = Math.min(student.getMark(), exams.get(student.getExamId()).get(student.getStudentId()));
					exams.get(student.getExamId()).put(student.getStudentId(), minMark);
				}
			}
		}
		for (Entry<Integer, HashMap<String, Integer>> entry : exams.entrySet()) {
		    Integer examId = entry.getKey();
		    Exam exam = ExamRepository.getInstance().findOneById(examId);
		    HashMap<String, Integer> examStudents = entry.getValue();
		    writeSheet(exam, examStudents, path);
		}
	}
	
	private void writeSheet(Exam exam, HashMap<String, Integer> examStudents, String path) {
		float[] columnWidths = { 1f, 0.7f, 0.7f };
		try {
			Document document = new Document();
			String finalSheetPath = path + "\\" + String.valueOf(exam.getId()) + "_" + exam.getName() + ".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(finalSheetPath));
			document.open();
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(50);
			table.setWidths(columnWidths);
			for (int i = 0; i < 3; ++i) {
				String cellText = TeacherMessage.finalSheetHeader[i] + (i == 1 ? String.valueOf(exam.getMark()) : "");
				PdfPCell cell = PDFCell.pdfCell(11, true, cellText, 20f);
				table.addCell(cell);
			}
			SortedSet<String> studentsName = new TreeSet<String>(examStudents.keySet());
			for (String studentName : studentsName) {
				PdfPCell examNameCell = PDFCell.pdfCell(9, false, exam.getName(), 15f);
				PdfPCell markCell = PDFCell.pdfCell(9, false, String.valueOf(examStudents.get(studentName)), 15f);
				PdfPCell studentIdCell = PDFCell.pdfCell(9, false, studentName, 15f);
				table.addCell(examNameCell);
				table.addCell(markCell);
				table.addCell(studentIdCell);
			}
			document.add(table);
			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
