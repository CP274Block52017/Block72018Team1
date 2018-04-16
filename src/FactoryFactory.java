
public class FactoryFactory {
	public static enum Factory {
		FACTORY_FACTORY, // as in a factory that is factor-y
		NOT_FACTORY_FACTORY // as in a factory that is not factor-y
	};
	
	public static FactoryFactory factoryFactoryFactory(FactoryFactory.Factory factory) { // yes thanks
		switch(factory) {
		case FACTORY_FACTORY:
			return new FactoryFactory();
		case NOT_FACTORY_FACTORY:
			return new FactoryFactory();
		default:
			return null;
		}
	}
}