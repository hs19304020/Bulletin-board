//thread専用のbeanファイル？
package info;

public class ThreadListProfile{
	private String id;
	private String name;
  private String theme;
	private String date;
	private String description;
	private Integer count;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}

  public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}

  public void setTheme(String theme){
    this.theme=theme;
  }
  public String getTheme(){
    return theme;
  }

	public void setDate(String date){
		this.date=date;
	}
	public String getDate(){
		return date;
	}

	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}

	public void setCount(Integer count){
		this.count=count;
	}
	public Integer getCount(){
		return count;
	}

}
