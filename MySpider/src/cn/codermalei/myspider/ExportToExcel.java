package cn.codermalei.myspider;

/**
 * 导出数据到excel
 * @author codermalei.cn
 * 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import jxl.Workbook;
import jxl.write.*;

public class ExportToExcel {
	/**
	 * @param fileName
	 *            EXCEL文件名称
	 * @param listTitle
	 *            EXCEL文件第一行列标题集合
	 * @param listContent
	 *            EXCEL文件正文数据集合
	 * @return
	 */
	public final static String exportExcel(String fileName, String[] Title, List<Book> listContent) {
		String result = "Excel文件导出成功！";
		try {
			File file = new File(fileName);
			FileOutputStream os = new FileOutputStream(file);

			// 创建工作簿
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			// 创建工作表

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			// 设置纵横打印（默认为纵打）、打印纸
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			// EXCEL第一行标题
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0, Title[i]));
			}
			// EXCEL正文数据
			Field[] fields = null;
			int i = 1;
			for (Object obj : listContent) {
				fields = obj.getClass().getDeclaredFields();
				int j = 0;
				for (Field v : fields) {
					v.setAccessible(true);
					Object va = v.get(obj);
					if (va == null) {
						va = "";
					}
					sheet.addCell(new Label(j, i, va.toString()));
					System.out.println(j + "---" + i + "---" + va.toString() + "---");
					j++;
				}
				i++;
			}
			workbook.write();
			workbook.close();

		} catch (Exception e) {
			result = "Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
}