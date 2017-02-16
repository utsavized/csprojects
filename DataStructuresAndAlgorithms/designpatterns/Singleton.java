package designpatterns;

class Singleton {
	private static Singleton instance = null;
	String value;
    
	private Singleton(String value) {
		this.value = value;
    }
    
    public static Singleton getInstance(String value) {
        if(instance == null) {
            return new Singleton(value);
        }
        return instance;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
