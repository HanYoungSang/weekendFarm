package project;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class BBSList {
	private ArrayList<Integer> seqNoList = new ArrayList<Integer>();
	private ArrayList<String> titleList = new ArrayList<String>();
	private ArrayList<String> writerList = new ArrayList<String>();
	private ArrayList<Date> dateList = new ArrayList<Date>();
	private ArrayList<Time> timeList = new ArrayList<Time>();
	private ArrayList<Integer> countList = new ArrayList<Integer>();
	private boolean lastPage = false;
	private boolean firstPage = false;
	
	
public BBSList() {}

	public void setSeqNo(int index, Integer seqNo) {
		this.seqNoList.add(index, seqNo);
	}
	public void setTitle(int index, String title) {
		this.titleList.add(index, title);
	}
	public void setWriter(int index, String writer) {
		this.writerList.add(index, writer);
	}
	public void setDate(int index, Date date) {
		this.dateList.add(index, date);
	}
	public void setTime(int index, Time date) {
		this.timeList.add(index, date);
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
	
	public void setCount(int index, Integer count) {
		this.countList.add(index, count);
	}
	public Integer[] getSeqNo() {
//		return (Integer[]) seqNoList.toArray();
		return seqNoList.toArray(new Integer[seqNoList.size()]);
	}
	public String[] getTitle() {
		return titleList.toArray(new String[titleList.size()]);
	}
		
	public String[] getWriter() {
		return writerList.toArray(new String[writerList.size()]);
	}

	public Date[] getDate() {
		return dateList.toArray(new Date[dateList.size()]);
	}
	public Time[] getTime() {
		return timeList.toArray(new Time[timeList.size()]);
	}
	public Integer[] getCount() {
		return countList.toArray(new Integer[countList.size()]);
	}
	
	public boolean isFirstPage() {
		return firstPage;
	}
	
	public boolean isLastPage() {
		return lastPage;
	}
	
	public int getListSize() { //게시글 수 리턴
//		System.out.println("BBSList:getListSize:" + seqNoList.size());
		return seqNoList.size();
	}
	
	
	
}
