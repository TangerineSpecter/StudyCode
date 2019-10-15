package design_pattern.observer.pojo;

import design_pattern.observer.service.BaseSubject;

/**
 * 报社（被观察者）
 * 
 * @author TangerineSpecter
 * @Date 2019年2月12日
 */
public class NewsOffice extends BaseSubject {

	// 模拟报纸ֽ
	public void newsPaperCome() {
		this.notifyAllObservers();
	}
}
