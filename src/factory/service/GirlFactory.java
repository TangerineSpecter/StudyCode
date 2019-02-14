package factory.service;

import factory.pojo.FatGirl;
import factory.pojo.ThinGirl;

/**
 * 妹子工厂
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class GirlFactory {

	public Girl createGirl(String whatYouLike) {
		Girl girl = null;
		if (whatYouLike == "fat") {
			girl = new FatGirl();
		} else if (whatYouLike == "thin") {
			girl = new ThinGirl();
		}
		return girl;
	}
}
