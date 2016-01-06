package com.dyn.achievements.gui;

import java.util.ArrayList;

import com.dyn.achievements.achievement.AchievementHandler;
import com.dyn.achievements.achievement.AchievementPlus;
import com.rabbit.gui.component.control.PictureButton;
import com.rabbit.gui.component.display.Picture;
import com.rabbit.gui.component.display.TextLabel;
import com.rabbit.gui.render.TextAlignment;
import com.rabbit.gui.show.Show;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ResourceLocation;

public class AchHome extends Show {

	public AchHome() {
		// this.setBackground(new DefaultBackground());
		this.title = "Achievement Gui";
	}

	@Override
	public void setup() {
		super.setup();

		this.registerComponent(new TextLabel(this.width / 3, (int) (this.height * .1), this.width / 3, 20,
				"Achievements", TextAlignment.CENTER));

		for (AchievementPlus a : AchievementHandler.getAllAchievements()) {
			ResourceLocation imgTexture = new ResourceLocation("minecraft", "textures/items/experience_bottle.png");
			this.registerComponent(new PictureButton(this.width / 6, (int) (this.height * .2), 25, 25, imgTexture)
					.setClickListener(btn -> this.getStage().display(new AchDisp(a, imgTexture))));
		}

		// The background
		this.registerComponent(new Picture(this.width / 8, (int) (this.height * .05), (int) (this.width * (6.0 / 8.0)),
				(int) (this.height * .9), new ResourceLocation("tutorial", "textures/gui/background.png")));
	}

}
