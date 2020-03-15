package design_pattern.observer.pojo;

import java.util.Observable;

public class NewsOffice2 extends Observable {

	// 模拟报纸ֽ
	public void newsPaperCome() {
		this.setChanged();
		this.notifyObservers();
	}
}
