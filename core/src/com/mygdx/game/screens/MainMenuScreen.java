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
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.attributes.Skill;
import com.mygdx.game.entity.attributes.skill.Blizzard;
import com.mygdx.game.entity.attributes.skill.Ember;
import com.mygdx.game.entity.attributes.skill.HydroPump;
import com.mygdx.game.entity.attributes.skill.IceBeam;
import com.mygdx.game.entity.engimon.Beartic;
import com.mygdx.game.entity.engimon.Eevee;
import com.mygdx.game.entity.engimon.Jolteon;
import com.mygdx.game.entity.engimon.Luxio;
import com.mygdx.game.entity.engimon.Pinsir;
import com.mygdx.game.entity.engimon.Spheal;

public class MainMenuScreen implements Screen {
    private Stage stage;
    private Game game;
    private SpriteBatch batch;
    private Texture img;
    private Label titleLabel;
    private Table table;

    public Player createNewPlayer() {
        Player newPlayer = new Player(10, 30, 30);

        // Defaults
        newPlayer.addItem(new Engimon(new Beartic(), false));
//        newPlayer.addItem(new Engimon(new Eevee(), false));
//        newPlayer.addItem(new Engimon(new Spheal(), false));
        newPlayer.addItem(new Skill(new Blizzard()));
        newPlayer.addItem(new Skill(new IceBeam()));

        newPlayer.addItem(new Skill(new HydroPump()));
        newPlayer.getEngimonItem().get(0).xpGain(300);
//        newPlayer.getEngimonItem().get(1).xpGain(400);
        System.out.println(newPlayer.getSkillItem().size());
        newPlayer.changeEngimon(newPlayer.getEngimonItem().get(0));

        return newPlayer;
    }

    public MainMenuScreen(Game aGame) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());

        int row_height = Gdx.graphics.getWidth() / 12;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        // Style untuk Label
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont();
        titleLabelStyle.fontColor = Color.BLACK;

        // Title Label
        titleLabel = new Label("Engimon Factory", titleLabelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
        titleLabel.setPosition(0,Gdx.graphics.getHeight()-row_height*2);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);

        // Style untuk TextButtons
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont();
        menuButtonStyle.fontColor = Color.BLACK;

        // Definisi dan Implementasi TextButtons
        TextButton newButton = new TextButton("New\nGame", menuButtonStyle);
        TextButton loadButton = new TextButton("Load\nGame", menuButtonStyle);
        TextButton helpButton = new TextButton("Help", menuButtonStyle);
        TextButton exitButton = new TextButton("Exit", menuButtonStyle);

        // NinePatch untuk border Buttons
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("background-tall.png")),
                1, 1, 3, 3);
        NinePatchDrawable background = new NinePatchDrawable(patch);

        NinePatch patch2 = new NinePatch(new Texture(Gdx.files.internal("background.png")),
                1, 1, 1, 1);
        NinePatchDrawable background2 = new NinePatchDrawable(patch2);

        // Tables untuk menyusun TextButtons
        table = new Table();

        Table tableNewGame = new Table();
        tableNewGame.add(newButton);
        tableNewGame.setBackground(background);
        tableNewGame.setTouchable(Touchable.enabled);
        tableNewGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Player newPlayer = createNewPlayer();
                game.setScreen(new MainGameScreen(game, newPlayer));
            }
        });
        table.add(tableNewGame).width(100).height(200);

        Table tableLoadGame = new Table();
        tableLoadGame.add(loadButton);
        tableLoadGame.setBackground(background);
        table.add(tableLoadGame).width(100).height(200);
        tableLoadGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Player newPlayer = createNewPlayer();
                game.setScreen(MainGameScreen.loadState(game, newPlayer));
            }
        });

        Table tableHelp = new Table();
        tableHelp.add(helpButton);
        tableHelp.setBackground(background2);
        tableHelp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HelpScreen(game));
            }
        });

        Table tableExit = new Table();
        tableExit.add(exitButton);
        tableExit.setBackground(background2);

        Table table2 = new Table();
        table2.add(tableHelp).height(100).width(100);
        table2.row();
        table2.add(tableExit).height(100).width(100);
        table.add(table2);
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
