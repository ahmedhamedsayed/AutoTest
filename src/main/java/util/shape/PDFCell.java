package util.shape;

import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

public class PDFCell {

	public static PdfPCell pdfCell(int fontSize, boolean isBold, String phrase, float height) {
		try {
			BaseFont baseFont = BaseFont.createFont("c://windows/fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(baseFont, fontSize);
			if (isBold)
				font.setStyle(Font.BOLD);
			PdfPCell cell = new PdfPCell(new Paragraph(new Phrase(phrase, font)));
			cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setFixedHeight(height);
			return cell;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
