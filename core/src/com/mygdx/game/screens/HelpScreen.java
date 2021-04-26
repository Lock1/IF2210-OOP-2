package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HelpScreen implements Screen {
    private Stage stage;
    private Game game;

    public HelpScreen(Game aGame) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());

        // Style untuk Label
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont();
        titleLabelStyle.fontColor = Color.BLACK;

        Label.LabelStyle answerLabelStyle = new Label.LabelStyle();
        answerLabelStyle.font = new BitmapFont();
        answerLabelStyle.fontColor = Color.RED;

        // Style untuk TextButtons
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont();
        menuButtonStyle.fontColor = Color.BLACK;

        TextButton menuButton = new TextButton("<< Menu", menuButtonStyle);
        menuButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        int row_height = Gdx.graphics.getWidth() / 12;
        menuButton.setPosition(100, Gdx.graphics.getHeight()-row_height*1);
        stage.addActor(menuButton);


        // NinePatch untuk border Buttons
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("background-tall.png")),
                1, 1, 3, 3);
        NinePatchDrawable background = new NinePatchDrawable(patch);

        Table mainTable = new Table();

        Label titleLabel = new Label("Help", titleLabelStyle);
        titleLabel.setAlignment(Align.center);
        mainTable.add(titleLabel);
        mainTable.row();

        Table helpTable = new Table();
        helpTable.setBackground(background);
        helpTable.setSize(500, 500);

        Label questionLabel1 = new Label("Bagaimana Membuat Game Baru?", titleLabelStyle);
        questionLabel1.setAlignment(Align.center);

        helpTable.add(questionLabel1).padBottom(3);
        helpTable.row();

        Label answerLabel1 = new Label("Tekan New Game Pada Main Menu", answerLabelStyle);
        answerLabel1.setAlignment(Align.center);

        helpTable.add(answerLabel1).padBottom(30);
        helpTable.row();

        Label questionLabel2 = new Label("Bagaimana Meload Game Lama?", titleLabelStyle);
        questionLabel2.setAlignment(Align.center);

        helpTable.add(questionLabel2).padBottom(3);
        helpTable.row();

        Label answerLabel2 = new Label("Tekan Load Game Pada Main Menu", answerLabelStyle);
        answerLabel2.setAlignment(Align.center);

        helpTable.add(answerLabel2).padBottom(30);
        helpTable.row();

        Label questionLabel3 = new Label("Bagaimana Cara Mengendalikan Pemain?", titleLabelStyle);
        questionLabel3.setAlignment(Align.center);

        helpTable.add(questionLabel3).padBottom(3);
        helpTable.row();

        Label answerLabel3 = new Label("Gunakan WASD Pada Map", answerLabelStyle);
        answerLabel3.setAlignment(Align.center);

        helpTable.add(answerLabel3).padBottom(30);
        helpTable.row();

        Label questionLabel4 = new Label("Kapan Permainan Berakhir?", titleLabelStyle);
        questionLabel4.setAlignment(Align.center);

        helpTable.add(questionLabel4).padBottom(3);
        helpTable.row();

        Label answerLabel4 = new Label("Saat Semua Engimon Player Telah Habis", answerLabelStyle);
        answerLabel4.setAlignment(Align.center);

        helpTable.add(answerLabel4).padBottom(30);
        helpTable.row();

        Label questionLabel5 = new Label("Apa Syarat Breed?", titleLabelStyle);
        questionLabel5.setAlignment(Align.center);

        helpTable.add(questionLabel5).padBottom(3);
        helpTable.row();

        Label answerLabel5 = new Label("Kedua Parent Harus Minimal Level 4", answerLabelStyle);
        answerLabel5.setAlignment(Align.center);

        helpTable.add(answerLabel5).padBottom(30);
        helpTable.row();

        mainTable.add(helpTable).height(500);
        mainTable.setFillParent(true);

        stage.addActor(mainTable);

    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Merender Batches dan Stages
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
