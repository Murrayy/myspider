package cn.codermalei.myspider;

/**
 * 爬虫线程
 * @author codermalei.cn
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetBookThread<T> extends Thread {

	private String url;
	private String tagName;
	private String fileName;
	private List<Book> books;

	@Override
	public void run() {
		books = new ArrayList<>();
		try {
			String url_ = "";
			int count = 1;
			for (int i = 0; i < 20; i++) {
				int start = i * Integer.valueOf(Const.Url.pageNum.getValue());
				if (start > 0) {
					url_ = url + Const.Url.pageStart.getValue() + start;
				} else {
					url_ = url;
				}
				Document document = Jsoup
						.connect(url_).userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) "
								+ "AppleWebKit/537.36 (KHTML, like Gecko) " + "Chrome/42.0.2311.152 Safari/537.36")
						.timeout(30000).get();
				// 检索图书列表
				Elements lis = document.select("li.subject-item");
				Iterator<Element> lisIterator = lis.iterator();
				while (lisIterator.hasNext()) {
					String numEle = "";
					String numOfPeople = "";
					try {
						Element li = lisIterator.next();

						// 获取书籍信息
						String bookInfo = li.select("div.pub").text();

						// 获取评价人数
						numEle = li.select("span.pl").text();
						if (numEle != "") {
							numOfPeople = numEle.substring(numEle.indexOf("(") + 1, numEle.lastIndexOf(")") - 3);
							try {
								if (Integer.parseInt(numOfPeople) < 1000) {
									continue;
								}
							} catch (Exception e) {
								continue;
							}
						}
						// 获取评分
						String ratingNumStr = li.select("span.rating_nums").text();
						float ratingNum = 0;
						if (ratingNumStr != null && ratingNumStr != "") {
							ratingNum = Float.parseFloat(ratingNumStr.trim());
						}

						// 获取书名
						String title = li.select("a[title]").text();

						// 解析书籍信息字符串
						// 获取价格
						String[] bookInfoItem = bookInfo.split("/");
						String prize = bookInfoItem[bookInfoItem.length - 1].trim();
						String author = bookInfoItem[0].trim();
						String publishDate = bookInfoItem[bookInfoItem.length - 2].trim();
						String publisher = bookInfoItem[bookInfoItem.length - 3].trim();
						Book book = new Book(count++, title, ratingNum, numOfPeople, author, publisher, publishDate,
								prize);
						books.add(book);
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
			Collections.sort(books);
			ExportToExcel.exportExcel("E:/doubanbook/" + tagName + ".xls", Const.titles, books);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}