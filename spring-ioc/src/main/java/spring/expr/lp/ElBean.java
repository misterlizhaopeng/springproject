package spring.expr.lp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("elBean")
public class ElBean {
	@Value("#{role.id}")
	private Long id;
	@Value("#{role}")
	private Role note;
	@Value("#{role.roleName}")
	private String name;
	@Value("#{role.getNote()?.toString()}")
	private  String elNote;
	//3.运算（整数加法、字符串相加、真假判断、以及三目运算）
	@Value("#{role.id+1}")
	private int num;
	@Value("#{role.note+'--expr--'+role.roleName}")
	private  String str;
	@Value("#{role.id>0}")
	private boolean isTrue;
	@Value("#{role.id>1000}")
	private boolean isFalse;
	@Value("#{role.id>1000?'output':'no-output'}")
	private String output;
  
	@Override
	public String toString() {
		return "ElBean [id=" + id + ", note=" + note + ", name=" + name + ", elNote=" + elNote + ", num=" + num
				+ ", str=" + str + ", isTrue=" + isTrue + ", isFalse=" + isFalse + ", output=" + output + "]";
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public boolean isFalse() {
		return isFalse;
	}

	public void setFalse(boolean isFalse) {
		this.isFalse = isFalse;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getElNote() {
		return elNote;
	}

	public void setElNote(String elNote) {
		this.elNote = elNote;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getNote() {
		return note;
	}

	public void setNote(Role note) {
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
