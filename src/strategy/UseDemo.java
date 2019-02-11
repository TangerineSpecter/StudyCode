package strategy;

import strategy.pojo.Calculator;
import strategy.service.impl.OperationAdd;

/**
 * ����ģʽ
 * 
 * @author TangerineSpecter
 * @Date 2019��2��11��
 */
public class UseDemo {
	
	/**
	 * ����ģʽ�ܺõķ����˴���ġ�����ԭ��;
	 * �����޸Ĺرգ�����չ����;
	 * �����ӣ�����������޷�����֮��ļ����ʱ��
	 * ���������µĹ��ܣ��Ϳ����Լ�����һ����ʵ��Operation�ӿ�
	 * 
	 * �ŵ㣺���Ͽ���ԭ����չ�Ժá�
	 * 
	 * ȱ�㣺���Ų������ӣ���Ӧ����Ҳ���ӣ�û���ĵ����ں������ʹ�ù���
	 * 
	 * Shiro��ʹ���˲���ģʽ
	 * 
	 * Shiro�о��ṩ�����ֲ��ԣ�
	 * AtLeastOneSuccessfulStrategy ��ֻҪ��һ����֤ͨ������ô������֤�������ͨ����
	 * FirstSuccessfulStrategy ��ֻ�е�һ���ɹ�����֤�� Realm ���ص���Ϣ����ʹ�ã����н�һ���� Realm �������ԣ����û��һ����֤�ɹ��������峢��ʧ�ܡ�
	 * AllSucessfulStrategy ��������֤����������֤�ɹ���
	 */
	public static void main(String[] args) {
		// ����һ��������
		Calculator calculator = new Calculator();
		// ���ü��㷽��
		calculator.setOperation(new OperationAdd());
		// ִ������
		int result = calculator.doOperation(1, 2);
		System.out.println(result);
	}
}
