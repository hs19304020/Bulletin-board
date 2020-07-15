//responce専用のbeanファイル？
package info;

public class ResListProfile{
	private String id;
	private String res_no;
	private String thread_id;
	private String name;
	private String date;
	private String text;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}

	public void setRes_no(String res_no){
		this.res_no=res_no;
	}
	public String getRes_no(){
		return res_no;
	}

	public void setThread_id(String thread_id){
		this.thread_id=thread_id;
	}
	public String getThread_id(){
		return thread_id;
	}

  public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

	public void setDate(String date){
		this.date=date;
	}
	public String getDate(){
		return date;
	}

	public void setText(String text){
		this.text=text;
	}
	public String getText(){
		return text;
	}

}
