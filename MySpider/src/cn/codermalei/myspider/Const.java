package cn.codermalei.myspider;

/**
 * 常量
 * @author codermalei.cn
 * 
 */

public class Const {
	/**
	 * 导出的excel文件的第一行的标题
	 */
	public static final String[] titles = { "序号", "书名", "评分", "评价人数", "作者", "出版社", "出版日期", "价格" };

	/**
	 * 枚举常量
	 */
	public static enum Url {
		/**
		 * 标签页URL
		 */
		tagPage("https://book.douban.com/tag/"),
		/**
		 * 详情页URL
		 */
		detailPage("https://book.douban.com/tag/bookType?type=S"),
		/**
		 * 每页数据数目
		 */
		pageNum("20"),
		/**
		 * 分页URL
		 */
		pageStart("&start=");

		private String name;

		private Url(String name) {
			this.name = name;
		}

		public String getValue() {
			return name;
		}
	}
}
