package project;

import java.util.Date;

public class ResourceItem {
	private int seq;
	private int year;
	private int row;
	private int column;
	private String book_yn;
	private int book_seq;
	private Date final_mod_date;
	private Date final_mod_time;
	private String final_person;
	
	public ResourceItem() {}

	public ResourceItem(int seq, int year, int row, int column, String book_yn,
			int book_seq, Date final_mod_date, Date final_mod_time,
			String final_person) {
		super();
		this.seq = seq;
		this.year = year;
		this.row = row;
		this.column = column;
		this.book_yn = book_yn;
		this.book_seq = book_seq;
		this.final_mod_date = final_mod_date;
		this.final_mod_time = final_mod_time;
		this.final_person = final_person;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getBook_yn() {
		return book_yn;
	}

	public void setBook_yn(String book_yn) {
		this.book_yn = book_yn;
	}

	public int getBook_seq() {
		return book_seq;
	}

	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
	}

	public Date getFinal_mod_date() {
		return final_mod_date;
	}

	public void setFinal_mod_date(Date final_mod_date) {
		this.final_mod_date = final_mod_date;
	}

	public Date getFinal_mod_time() {
		return final_mod_time;
	}

	public void setFinal_mod_time(Date final_mod_time) {
		this.final_mod_time = final_mod_time;
	}

	public String getFinal_person() {
		return final_person;
	}

	public void setFinal_person(String final_person) {
		this.final_person = final_person;
	}
	public String toString() {
		System.out.println("toString() 시작");
		String ret = String.format(
				"SEQ:%d, YEAR:%d, row:%d, column:%d, book_yn:%s, book_seq:%s, final_mod_date:%TT %TF, final_person:%s",
				this.seq, this.year, this.row, this.column, this.book_yn, this.book_seq, this.final_mod_date,this.final_mod_time,this.final_person);
				
//				"SEQ:" + this.seq + ", YEAR:" + this.year + ", row:" + this.row + ", column:"+ this.column + "\n" 
//	                 + ", book_yn:" + this.book_yn + ", book_seq:" + this.book_seq  + "\n"
//	                 + ", final_mod_date:" + this.final_mod_date + ", final_mod_time:" + this.final_mod_time + ", final_person:" + this.final_person;
		
		return ret ;
	}
}
