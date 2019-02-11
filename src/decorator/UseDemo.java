package decorator;

import decorator.service.Person;
import decorator.service.impl.WearHat;
import decorator.service.impl.WearJacket;
import decorator.service.impl.ZhangJiaHui;

/**
 * װ����ģʽ
 * 
 * @author TangerineSpecter
 * @Date 2019��2��11��
 */
public class UseDemo {

	/**
	 * װ�����ͱ�װ����Ҫʵ��ͬһ���ӿڣ���ʵ�������ۼ�Ч��
	 * 
	 * ���ã���̬����������һЩ���ܣ�������Ҫ�޸Ķ�����
	 * 
	 * �ŵ㣺��չ��ÿһ��װ�����������޸�ʱ�����໥Ӱ�졣
	 * 
	 * ȱ�㣺���װ�αȽϸ��ӣ������ֲ��Ѻã�
	 */
	public static void main(String[] args) {
		//����һ��������
		Person zhangjiahui = new ZhangJiaHui();
		//���ϼп�
		zhangjiahui = new WearJacket(zhangjiahui);
		//����ñ��
		zhangjiahui = new WearHat(zhangjiahui);
		
		zhangjiahui.show();
	}
}
