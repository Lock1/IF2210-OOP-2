package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
import com.badlogic.gdx.maps.tiled.*;

public class MainGameScreen implements Screen {
    private Stage stage;
    private Game game;
    private SpriteBatch batch;
    private Label titleLabel;
    private Table table;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public MainGameScreen(Game aGame) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());

        int row_height = Gdx.graphics.getWidth() / 12;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        // Style untuk Label
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont();
        titleLabelStyle.fontColor = Color.BLACK;

        // Title Label
        titleLabel = new Label("Engimon Factory", titleLabelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
        titleLabel.setPosition(0,Gdx.graphics.getHeight()-row_height*1);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);

        // Style untuk TextButtons
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont();
        menuButtonStyle.fontColor = Color.BLACK;

        // Definisi dan Implementasi TextButtons
        TextButton engimonButton = new TextButton("Engimon", menuButtonStyle);
        TextButton breedButton = new TextButton("Breed", menuButtonStyle);
        TextButton inventoryButton = new TextButton("Inventory", menuButtonStyle);
        TextButton menuButton = new TextButton("<< Menu", menuButtonStyle);
        menuButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PauseScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        menuButton.setPosition(100, Gdx.graphics.getHeight()-row_height*1);
        stage.addActor(menuButton);

        // NinePatch untuk border Buttons
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("background-tall.png")),
                1, 1, 3, 3);
        NinePatchDrawable background = new NinePatchDrawable(patch);

        NinePatch patch2 = new NinePatch(new Texture(Gdx.files.internal("background.png")),
                1, 1, 1, 1);
        NinePatchDrawable background2 = new NinePatchDrawable(patch2);


        // Tables untuk menyusun TextButtons
        table = new Table();
        table.setPosition(300,-30);

        Table tableMap = new Table();
        tableMap.setBackground(background);
        table.add(tableMap).width(300).height(510).right().center();

//        Table tableDescription = new Table();
//        Table tableCurrentEngimon = new Table();
//        tableCurrentEngimon.setBackground(background2);
//        Table tableMessage = new Table();
//        tableMessage.setBackground(background2);
//        Table tableBreedButton = new Table();
//        tableBreedButton.add(breedButton);
//        tableBreedButton.setBackground(background2);
//        tableDescription.add(tableCurrentEngimon).width(200).height(150);
//        tableDescription.row();
//        tableDescription.add(tableMessage).width(200).height(150);
//
//        table.add(tableDescription).width(200).height(300).spaceRight(30).top();
//
//        Table tableButtons = new Table();
//
//        Table tableEngimon = new Table();
//        tableEngimon.add(engimonButton);
//        tableEngimon.setBackground(background2);
//        tableEngimon.setTouchable(Touchable.enabled);
//        tableEngimon.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new EngimonScreen(game));
//            }
//        });
//        tableButtons.add(tableEngimon).width(100).height(70).spaceTop(30).spaceBottom(15);
//        tableButtons.row();
//
//        Table tableBreed = new Table();
//        tableBreed.add(breedButton);
//        tableBreed.setBackground(background2);
//        tableBreed.setTouchable(Touchable.enabled);
//        tableBreed.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new BreedScreen(game));
//            }
//        });
//        tableButtons.add(tableBreed).width(100).height(70).spaceTop(15).spaceBottom(15);
//        tableButtons.row();
//
//        Table tableInventory = new Table();
//        tableInventory.add(inventoryButton);
//        tableInventory.setBackground(background2);
//        tableInventory.setTouchable(Touchable.enabled);
//        tableInventory.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new InventoryScreen(game));
//            }
//        });
//        tableButtons.add(tableInventory).width(100).height(70).spaceTop(15).spaceBottom(15);
//
//        table.add(tableButtons).top();

        table.setFillParent(true);

        // Menambahkan table ke dalam stage
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");
        Gdx.input.setInputProcessor(stage);
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
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
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 1500;
        camera.viewportHeight = 1150;
        camera.position.set(600, 450, 0);
        camera.update();
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
        map.dispose();
        renderer.dispose();
    }
}
