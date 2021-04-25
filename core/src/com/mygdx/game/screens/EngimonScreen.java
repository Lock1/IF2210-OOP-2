package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.EngimonInventory;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.SkillInventory;

import java.util.ArrayList;

public class EngimonScreen implements Screen {
    private Stage stage;
    private Game game;
    private SpriteBatch batch;
    private Label titleLabel;
    private Table table;
    private String[] engimonDummy = {"Pikachu", "IceLord", "Ragnarok", "Boulder"};
    private String[] statsDummy =
            {"Lightning",
            "Ice",
            "Fire",
            "Earth"};
    private String currentStats = "";
    private Label statsLabel;
    private EngimonInventory engimonInventory;
    private ArrayList<Engimon> engimonList;
    private Player currentPlayer;

    public void getEngimonList() {
        engimonInventory = new EngimonInventory(50);
        engimonList = engimonInventory.getItemList();
    }

    public EngimonScreen(Game aGame, final Player currentPlayer) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());
        this.currentPlayer = currentPlayer;

        int row_height = Gdx.graphics.getWidth() / 12;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        // Style untuk Label
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont();
        titleLabelStyle.fontColor = Color.BLACK;

        // Title Label
        titleLabel = new Label("Engimon", titleLabelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
        titleLabel.setPosition(0,Gdx.graphics.getHeight()-row_height*1);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);

        // Style untuk TextButtons
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont();
        menuButtonStyle.fontColor = Color.BLACK;

        // Definisi dan Implementasi TextButtons
        TextButton engimonButton = new TextButton("Your Engimon", menuButtonStyle);
        TextButton statButton = new TextButton("Stat", menuButtonStyle);
        TextButton backButton = new TextButton("<< Back", menuButtonStyle);
        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainGameScreen(game, currentPlayer));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        backButton.setPosition(100, Gdx.graphics.getHeight()-row_height*1);
        stage.addActor(backButton);

        // NinePatch untuk border Buttons
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("background-tall.png")),
                1, 1, 3, 3);
        NinePatchDrawable background = new NinePatchDrawable(patch);


        // Tables untuk menyusun TextButtons
        table = new Table();

        table.add(engimonButton).width(400).spaceRight(60);
        table.add(statButton).width(200);
        table.row();

        Table tableEngimon = new Table();
        tableEngimon.setBackground(background);
        tableEngimon.top().padTop(20);
        for(int i=0;i<engimonDummy.length;i++) {
            TextButton itemButton = new TextButton(engimonDummy[i], menuButtonStyle);
            tableEngimon.add(itemButton).padTop(10).padBottom(10);
            final int j = i;
            itemButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    currentStats = statsDummy[j];
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            tableEngimon.row();
        }
        table.add(tableEngimon).width(400).height(400).spaceRight(40);

        Table tableStats = new Table();
        tableStats.setBackground(background);
        statsLabel = new Label(currentStats, titleLabelStyle);
        statsLabel.setWrap(true);
        statsLabel.setWidth(240);
        statsLabel.setSize(Gdx.graphics.getWidth(),row_height);
        statsLabel.setAlignment(Align.center);
        tableStats.add(statsLabel).width(240);
        table.add(tableStats).width(300).height(400);

        table.setFillParent(true);

        // Menambahkan table ke dalam stage
        stage.addActor(table);
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
        batch.begin();
        stage.act();
        statsLabel.setText(currentStats);
        stage.draw();
        batch.end();
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
