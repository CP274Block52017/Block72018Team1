
public class FactoryFactory {
	public static enum FactoryType {
		NormalFactory,
		FactoryFactory
	};
	
	public static FactoryFactory factoryFactoryFactory() {
		return new FactoryFactory();
	}
}
