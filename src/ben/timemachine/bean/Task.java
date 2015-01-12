package ben.timemachine.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务
 * @author xuben
 *
 */
public class Task implements Serializable{
	//任务时间
	private Date date;
	//任务名
	private String name;
	//子任务名
	private String subName;
	//任务信息
	private String info;
	//任务类型
	private int type;
	//任务状态
	private int state;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
}
