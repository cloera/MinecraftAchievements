package com.dyn.achievements.gui;

import com.dyn.achievements.achievement.AchievementMap;
import com.rabbit.gui.background.DefaultBackground;
import com.rabbit.gui.component.display.Picture;
import com.rabbit.gui.show.Show;

import net.minecraft.util.ResourceLocation;

public class AchMap extends Show {
	
	AchievementMap achMap;

	public AchMap(AchievementMap map) {
		this.setBackground(new DefaultBackground());
		this.title = "Achievement Map Gui";
		this.achMap = map;
	}

	@Override
	public void setup() {
		super.setup();

		

		// The background
		this.registerComponent(new Picture(this.width / 8, (int) (this.height * .05), (int) (this.width * (6.0 / 8.0)),
				(int) (this.height * .9), new ResourceLocation("dyn", "textures/gui/background3.png")));
	}

}
