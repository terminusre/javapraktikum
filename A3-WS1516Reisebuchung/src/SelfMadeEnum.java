
public class SelfMadeEnum {
	
	public static final SelfMadeEnum ENUM_1=  new SelfMadeEnum("1");
	public static final SelfMadeEnum ENUM_2 = new SelfMadeEnum("2");
	public static final SelfMadeEnum ENUM_3 = new SelfMadeEnum("3");

	private String id;
	
	private SelfMadeEnum(String id){ this.id = id;}

	@Override
	public String toString() {
		return id;
	}
}
