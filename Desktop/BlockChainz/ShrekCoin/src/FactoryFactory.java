
public class FactoryFactory {
	public static enum FactoryType {
		FactoryFactory,
	};
	
	public static FactoryFactory factoryFactoryFactory() {
		return new FactoryFactory();
	}
}
