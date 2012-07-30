package common.utils;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {
	public static OutputStream createXls(String[] attr,String[][] DownList, 
			HttpServletResponse response) throws IOException, Exception,
			WriteException {
		//表头
		String[] head = attr;

		//下载数据
		String[][] data = DownList;

		OutputStream out = null;
		Label l = null;
		WritableFont detFont1 = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat detFormat1 = new WritableCellFormat(detFont1);
		WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat detFormat = new WritableCellFormat(detFont);
		int column = 0;
		int length = 0;
		int weigth = 0;
		if (0 == data.length && null != data) {
			length = 0;
			weigth = 0;
		} else {
			length = data.length;
			weigth = data[0].length;
		}
		try {
			out = response.getOutputStream();
			//out = new FileOutputStream("d:\\hello.xls");
			WritableWorkbook workbook = null;
			workbook = Workbook.createWorkbook(out);
			WritableSheet sheet = workbook.createSheet("text", 10);
			for (int i = 0; i < length + 1; i++) {
				column = 0;
				if (i == 0) {
					for (int j = 0; j < head.length; j++) {
						l = new Label(column++, i, head[j], detFormat1);
						sheet.addCell(l);
					}
				} else {
					for (int j = 0; j < weigth; j++) {
						if (null != data[i - 1][j]) {

							l = new Label(column++, i, data[i - 1][j],
									detFormat);
							sheet.addCell(l);
						} else {
							l = new Label(column++, i, "", detFormat);
							sheet.addCell(l);
						}
					}
				}

			}
			column = 0;
			for (int i = 0; i < length; i++) {
				sheet.setColumnView(column++, 20);
			}
			workbook.write();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return out;
	}
	public static void main(String args[]) throws WriteException, IOException, Exception{
		String[] attr = new String[]{"人","鬼","神"};
		String[][] data=new String[][]{{"1","2","3"},{"1","2","3"}};
		createXls(attr,data,null);
	}
}
