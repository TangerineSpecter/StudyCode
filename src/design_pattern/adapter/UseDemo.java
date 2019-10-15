package design_pattern.adapter;

import design_pattern.adapter.service.MusicPlayer;
import design_pattern.adapter.service.impl.PlayerAdapter;

/**
 * 适配器模式
 * 
 * @author TangerineSpecter
 * @Datetime 2019年2月14日
 *
 */
public class UseDemo {

	/**
	 * 适配器模式
	 * 
	 * 分为两种：1、类适配器；2、对象适配器。
	 * 
	 * 适配器适合用于解决新旧系统（ 或新旧接口 ）之间的兼容问题，而不建议在一开始就直接使用。
	 * 
	 * 优点：可以让两个不相干的类、接口运行起来，无需修改旧代码，灵活性好。
	 * 
	 * 缺点：适配器模式多了会使整个系统变得复杂，你无法知道适配器内部做了多少次转换操作。如果能用别的方法解决，尽量少用适配器模式。
	 * 
	 */
	public static void main(String[] args) {
		MusicPlayer player = new PlayerAdapter();
		player.play("mp3", "music");
		player.play("wma", "wahaha");
	}
}
