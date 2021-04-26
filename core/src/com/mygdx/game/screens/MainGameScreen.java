package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

import com.mygdx.game.KeyboardInput;
import com.mygdx.game.GameLogic;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.EngimonInventory;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.SkillInventory;
import com.mygdx.game.entity.attributes.Skill;
import com.mygdx.game.maps.OrthogonalTiledMapRendererWithSprites;

import java.util.ArrayList;

public class MainGameScreen extends ApplicationAdapter implements Screen, InputProcessor {
    private Stage stage;
    private Game game;
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Label titleLabel;
    private Table table;
    private TiledMap map;
    private OrthogonalTiledMapRendererWithSprites renderer;
    private OrthographicCamera camera;
    private KeyboardInput playerKeyboardInput;
    private GameLogic mainGameLogic;
    private int tileWidth;
    private int tileHeight;
    private int minTile = 0;
    private int maxTile = 48;
    private SpriteBatch spBatch = new SpriteBatch();
    private MainGameScreen mainScreenReference;

    // Inventories
    private EngimonInventory engimonInventory;
    private SkillInventory skillInventory;
    private ArrayList<Engimon> engimonList;
    private ArrayList<Skill> skillList;

    // Player
    private Player currentPlayer;

    public void getDatabaseData() {
        engimonInventory = new EngimonInventory(50);
        engimonList = engimonInventory.getItemList();

        skillInventory = new SkillInventory(50);
        skillList = skillInventory.getItemList();
    }

    public MainGameScreen(Game aGame, final Player currentPlayer) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());

        mainScreenReference = this;
//        getDatabaseData();
        this.currentPlayer = currentPlayer;
        currentPlayer.setEntityTileSize(tileWidth, tileHeight);
        currentPlayer.setTexture(new Texture(Gdx.files.internal("./sprites/player/idle.png")));
        currentPlayer.setSprite(new Sprite(currentPlayer.getTexture()));

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
                game.setScreen(new PauseScreen(game, currentPlayer, mainScreenReference));
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

        NinePatch patch3 = new NinePatch(new Texture(Gdx.files.internal("background-button.png")),
                1, 1, 1, 1);
        NinePatchDrawable background3 = new NinePatchDrawable(patch3);


        // Tables untuk menyusun TextButtons
        table = new Table();
        table.setPosition(310,-30);

        Table tableMap = new Table();
        tableMap.setBackground(background);
        table.add(tableMap).width(300).height(400).right().center();

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
        Table tableButtons = new Table();

        Table tableEngimon = new Table();
        tableEngimon.add(engimonButton);
        tableEngimon.setBackground(background3);
        tableEngimon.setTouchable(Touchable.enabled);
        tableEngimon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new EngimonScreen(game, currentPlayer, mainScreenReference));
            }
        });
        tableButtons.add(tableEngimon).width(95).height(70).spaceTop(30).spaceBottom(15);

        Table tableBreed = new Table();
        tableBreed.add(breedButton);
        tableBreed.setBackground(background3);
        tableBreed.setTouchable(Touchable.enabled);
        tableBreed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new BreedScreen(game, currentPlayer, mainScreenReference));
            }
        });
        tableButtons.add(tableBreed).width(95).height(70).spaceTop(15).spaceBottom(15);

        Table tableInventory = new Table();
        tableInventory.add(inventoryButton);
        tableInventory.setBackground(background3);
        tableInventory.setTouchable(Touchable.enabled);
        tableInventory.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InventoryScreen(game, currentPlayer, mainScreenReference));
            }
        });
        tableButtons.add(tableInventory).width(95).height(70).spaceTop(15).spaceBottom(15);

        table.row();
        table.add(tableButtons);

        table.setFillParent(true);

        // Menambahkan table ke dalam stage
        stage.addActor(table);

        // Keyboard input setup
        playerKeyboardInput = new KeyboardInput();
        playerKeyboardInput.start();

        mainGameLogic = new GameLogic(currentPlayer, renderer);
    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen","show");
//        Gdx.input.setInputProcessor(stage);
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("Map.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map);
        tileWidth = map.getProperties().get("tilewidth", Integer.class);
        tileHeight = map.getProperties().get("tileheight", Integer.class);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,1500,1150);
        camera.update();

        // testposx = 30;
        // testposy = 30;
        // testtexture = new Texture(Gdx.files.internal("./sprites/electric/left/move/1.png"));
        // testsprite = new Sprite(testtexture);
        // testsprite.setPosition(tileWidth*testposx,tileHeight*testposy);

        renderer = new OrthogonalTiledMapRendererWithSprites(map, spBatch);
        renderer.addSprite(currentPlayer.getSprite());

//        Gdx.input.setInputProcessor(this);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Merender Batches dan Stages
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.act();
        stage.draw();

        currentPlayer.getSprite().setPosition(tileWidth*currentPlayer.getPosition().x,tileHeight*currentPlayer.getPosition().y);

        renderer.setView(camera);
        renderer.render();

        batch.begin();
//        sprite.draw(batch);
        batch.end();

        String keydata = playerKeyboardInput.getKeypress();
        if (keydata != null) {
            mainGameLogic.playerInput(keydata);
            System.out.println(currentPlayer.getPosition().x);
            System.out.println(currentPlayer.getPosition().y);
            // testsprite.setPosition(tileWidth*testposx,tileHeight*testposy);
        }
       // isometricRenderer.setView(camera);
       // isometricRenderer.render();
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
        playerKeyboardInput.stopThread();
    }

    @Override public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT) {
            Texture righttexture = new Texture(Gdx.files.internal("./sprites/electric/left/move/1.png"));
            sprite.setTexture(righttexture);
            if (sprite.getX() != minTile * tileWidth) {
                sprite.translate(-tileWidth, 0);
            }
        }
        if(keycode == Input.Keys.RIGHT) {
            Texture righttexture = new Texture(Gdx.files.internal("./sprites/electric/right/move/1.png"));
            sprite.setTexture(righttexture);
            if (sprite.getX() != maxTile * tileWidth) {
                sprite.translate(tileWidth, 0);
            }
        }
        if(keycode == Input.Keys.UP)
            if(sprite.getY() != maxTile*tileHeight) {
                sprite.translate(0,tileHeight);
            }
        if(keycode == Input.Keys.DOWN)
            if(sprite.getY() != minTile*tileHeight) {
                sprite.translate(0,-tileHeight);
            }
//        if(keycode == Input.Keys.NUM_1)
//            map.getLayers().get(0).setVisible(!map.getLayers().get(0).isVisible());
//        if(keycode == Input.Keys.NUM_2)
//            map.getLayers().get(1).setVisible(!map.getLayers().get(1).isVisible());
        return false;
    }
    @Override public boolean keyUp(int keycode) {
        return false;
    }
    @Override public boolean keyTyped(char character) {
        return false;
    }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    public boolean scrolled(float x, float y) {
        return false;
    }
}
