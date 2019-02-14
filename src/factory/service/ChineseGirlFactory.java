package factory.service;

import factory.pojo.FatGirl;
import factory.pojo.ThinGirl;

/**
 * 中国妹子工厂
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class ChineseGirlFactory extends AbstractGirlFactory {

	private String city = "中国";

	public Girl createGirl(String whatYouLike) {
		Girl girl = null;
		if (whatYouLike == "fat") {
			girl = new FatGirl(city);
		} else if (whatYouLike == "thin") {
			girl = new ThinGirl(city);
		}
		return girl;
	}
}
