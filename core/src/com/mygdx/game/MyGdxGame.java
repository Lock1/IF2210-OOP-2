package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MyGdxGame extends ApplicationAdapter {
	Stage stage;
	SpriteBatch batch;
	Texture img;
	Label titleLabel;
	Table table;
	
	@Override
	public void create () {
		int row_height = Gdx.graphics.getWidth() / 12;
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		// Title Label
		Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
		titleLabelStyle.font = new BitmapFont();
		titleLabelStyle.fontColor = Color.BLACK;
		titleLabel = new Label("Engimon Factory", titleLabelStyle);
		titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
		titleLabel.setPosition(0,Gdx.graphics.getHeight()-row_height*2);
		titleLabel.setAlignment(Align.center);
		stage.addActor(titleLabel);

		Label oneLabel = new Label("New\nGame", titleLabelStyle);
		oneLabel.setAlignment(Align.center);
		Label twoLabel = new Label("Load\nGame", titleLabelStyle);
		twoLabel.setAlignment(Align.center);
		Label threeLabel = new Label("Help", titleLabelStyle);
		threeLabel.setAlignment(Align.center);
		Label fourLabel = new Label("Exit", titleLabelStyle);
		fourLabel.setAlignment(Align.center);

		table = new Table();
		table.add(oneLabel).width(100).height(200);
		table.add(twoLabel).width(100).height(200);
		Table table2 = new Table();
		table2.add(threeLabel).height(100).width(100);
		table2.row();
		table2.add(fourLabel).height(100).width(100);
		table.add(table2);
		table.setFillParent(true);
//		table.setBackground(Color.BLACK);
		stage.addActor(table);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.act();
		stage.draw();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
