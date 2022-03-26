package practice.backend.dto;

public class Article {
	private int no;
	private String userid;
	private String subject;
	private String content;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [no=" + no + ", userid=" + userid + ", subject=" + subject + ", content=" + content + "]";
	}

	public Article(int no, String userid, String subject, String content) {
		super();
		this.no = no;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
	}

	public Article() {
		super();
	}

}
