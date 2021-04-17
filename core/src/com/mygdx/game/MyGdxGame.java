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

		Label newLabel = new Label("New\nGame", titleLabelStyle);
		newLabel.setAlignment(Align.center);
		Label loadLabel = new Label("Load\nGame", titleLabelStyle);
		loadLabel.setAlignment(Align.center);
		Label helpLabel = new Label("Help", titleLabelStyle);
		helpLabel.setAlignment(Align.center);
		Label exitLabel = new Label("Exit", titleLabelStyle);
		exitLabel.setAlignment(Align.center);

		NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("background-tall.png")),
				1, 1, 3, 3);
		NinePatchDrawable background = new NinePatchDrawable(patch);

		NinePatch patch2 = new NinePatch(new Texture(Gdx.files.internal("background.png")),
				1, 1, 1, 1);
		NinePatchDrawable background2 = new NinePatchDrawable(patch);

		table = new Table();

		Table tableNewGame = new Table();
		tableNewGame.add(newLabel);
		tableNewGame.setBackground(background);
		table.add(tableNewGame).width(100).height(200);

		Table tableLoadGame = new Table();
		tableLoadGame.add(loadLabel);
		tableLoadGame.setBackground(background);
		table.add(tableLoadGame).width(100).height(200);

		Table tableHelp = new Table();
		tableHelp.add(helpLabel);
		tableHelp.setBackground(background2);

		Table tableExit = new Table();
		tableExit.add(exitLabel);
		tableExit.setBackground(background2);

		Table table2 = new Table();
		table2.add(tableHelp).height(100).width(100);
		table2.row();
		table2.add(tableExit).height(100).width(100);
		table.add(table2);
		table.setFillParent(true);

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
