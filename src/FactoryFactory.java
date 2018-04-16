
public class FactoryFactory {
	public static enum FactoryType {
		FactoryFactory, // as in a factory that is factor-y
	};
	
	public static FactoryFactory factoryFactoryFactory(FactoryFactory.FactoryType factoryType) {
		switch(factoryType) {
		case FactoryFactory:
			return new FactoryFactory();
		default:
			return null; // ?
		}
	}
}
