package cn.codermalei.myspider;

/**
 * Book类
 * 
 * @author codermalei.cn
 */

public class Book implements Comparable<Book> {

	/**
	 * 序号
	 */
	private int id;

	/**
	 * 书名
	 */
	private String title;
	/**
	 * 评分
	 */
	private float ratingNum;
	/**
	 * 评价人数
	 */
	private String numOfPeople;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 出版社
	 */
	private String publisher;
	/**
	 * 出版日期
	 */
	private String publishDate;
	/**
	 * 价格
	 */
	private String prize;

	public Book() {
	}

	public Book(int id, String title, float ratingNum, String numOfPeople, String author, String publisher, String

	publishDate, String prize) {
		this.id = id;
		this.title = title;
		this.ratingNum = ratingNum;
		this.numOfPeople = numOfPeople;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.prize = prize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getRatingNum() {
		return ratingNum;
	}

	public void setRatingNum(float ratingNum) {
		this.ratingNum = ratingNum;
	}

	public String getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(String numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", ratingNum=" + ratingNum + ", numOfPeople=" +

				numOfPeople + ", author=" + author + ", publisher=" + publisher + ", publishDate=" +

				publishDate + ", prize=" + prize + "]";
	}

	@Override
	public int compareTo(Book b) {
		return this.ratingNum > b.ratingNum ? -1 : this.ratingNum < b.ratingNum ? 1 : 0;
	}
}
