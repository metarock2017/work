package adapter;


class Adaptee {
	public void specificRequest() {
		System.out.println("����������� ���⹦��...");
	}
}

interface Target {
	public void request();
}

class ConcreteTarget implements Target {
	public void request() {
		System.out.println("��ͨ�� ���� ��ͨ����...");
	}
}

class Adapter implements Target{
	private Adaptee adaptee;
	
	public Adapter (Adaptee adaptee) {
		this.adaptee = adaptee;
	}
	
	public void request() {
		this.adaptee.specificRequest();
	}
}

public class Client {
    public static void main(String[] args) {
    	Target concreteTarget = new ConcreteTarget();
    	concreteTarget.request();
    	
    	Target adapter = new Adapter(new Adaptee());
    	adapter.request();
    }

}
