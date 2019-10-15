package design_pattern.adapter.service.impl;

import design_pattern.adapter.service.MusicPlayer;

public class PlayerAdapter implements MusicPlayer {
	// 在适配器中使用旧接口
	private ExistPlayer player;

	public PlayerAdapter() {
		player = new ExistPlayer();
	}

	@Override
	public void play(String type, String filename) {
		if (type == "mp3") {
			player.playMp3(filename);
		} else if (type == "wma") {
			player.playWma(filename);
		}
	}

}
