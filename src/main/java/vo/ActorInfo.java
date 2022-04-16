package vo;

public class ActorInfo {
	private int actorId;
	private String Name;
	private String filmInfo;

	@Override
	public String toString() {//toString 오버라이드 설정
		return "ActorInfo [actorId=" + actorId + ", Name=" + Name + ", filmInfo=" + filmInfo + "]";
	}
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getFilmInfo() {
		return filmInfo;
	}
	public void setFilmInfo(String filmInfo) {
		this.filmInfo = filmInfo;
	}
	
	
}
