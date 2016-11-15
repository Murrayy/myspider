package cn.codermalei.myspider;

/**
 * 爬虫程序入口
 * @author codermalei.cn
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


public class GetBook {

	public static void main(String[] args) {
		List<String> tags = new ArrayList<>();
		tags.add("互联网");
		tags.add("编程");
		tags.add("算法");
		getBook(tags);
	}

	/**
	 * 遍历标签List并启动线程根据标签重写URL进行抓取
	 * 
	 * @param tagLists
	 */
	private static void getBook(List<String> tagLists) {
		ThreadPoolExecutor executor = ThreadPool.getInstance();
		try {
			// 遍历所有标签并拼接相应的URL
			for (String tagName : tagLists) {
				String url = Const.Url.detailPage.getValue().replaceAll("bookType", tagName);
				GetBookThread task = new GetBookThread();
				task.setUrl(url);
				task.setFileName(tagName);
				task.setTagName(tagName);
				executor.execute(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}