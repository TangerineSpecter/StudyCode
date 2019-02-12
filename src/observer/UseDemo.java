package observer;

import observer.pojo.NewsOffice;
import observer.service.impl.CustomerA;
import observer.service.impl.CustomerB;

/**
 * �۲���ģʽ
 * 
 * @author TangerineSpecter
 * @Date 2019��2��12��
 */
public class UseDemo {

	public static void main(String[] args) {
		NewsOffice office = new NewsOffice();
		CustomerA customerA = new CustomerA();
		CustomerB customerB = new CustomerB();
		//�ͻ�A���ı�ֽ
		office.addCustomer(customerA);
		office.addCustomer(customerB);
		//���ͱ�ֽ
		office.newsPaperCome();
	}
}
