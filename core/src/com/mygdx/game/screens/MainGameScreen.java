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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import com.mygdx.game.action.Battle;
import com.mygdx.game.entity.*;
import com.mygdx.game.entity.attributes.Skill;
import com.mygdx.game.entity.engimon.Charmander;
import com.mygdx.game.entity.engimon.Eevee;
import com.mygdx.game.entity.engimon.Spheal;
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
    private long lastPoll;
    private boolean isBattlePrompt;

    // Fonts
    private Label.LabelStyle titleLabelStyle;
    private Label.LabelStyle warningLabelStyle;
    private TextButton.TextButtonStyle menuButtonStyle;
    private NinePatchDrawable background3;

    // Inventories
    private EngimonInventory engimonInventory;
    private SkillInventory skillInventory;
    private ArrayList<Engimon> engimonList;
    private ArrayList<Skill> skillList;

    // Player
    private Player currentPlayer;


    public void getLegend() {}

    // Battle
    private Table tableMap;
    private Label battleTitle;
    private Label EnemyDescription;
    private Label EngimonStatus;
    private TextButton yesButton;
    private TextButton noButton;
    private Engimon enemy;

    private Label nameLabel;
    private Label elementLabel;
    private Label parentNameLabel;
    private Label parentSpeciesLabel;
    private Label levelLabel;
    private Label expLabel;
    private Label lifeLabel;
    private Label playerPowerLabel;
    private Label enemyPowerLabel;

    public void battleDialog() {
        final Battle battle = new Battle(currentPlayer.getCurrentEngimon(), enemy);
        battle.calculatePower();
        // Battle Labels and Buttons
        tableMap.clear();
        tableMap.clearChildren();
        battleTitle = new Label("Do Battle?", warningLabelStyle);
        tableMap.add(battleTitle).padBottom(15);
        tableMap.row();

        nameLabel = new Label("Name: " + enemy.engimonName(), titleLabelStyle);
        nameLabel.setWrap(true);
        nameLabel.setWidth(240);

        nameLabel.setAlignment(Align.center);
        tableMap.add(nameLabel).width(240).padTop(10).padBottom(10);
        tableMap.row();

        String elementString = "Element: " + enemy.getSpecies().getElementSet().toArray()[0].toString();
        if (enemy.getSpecies().getElementSet().size() == 2)
            elementString = elementString + ", " + enemy.getSpecies().getElementSet().toArray()[1].toString();

        elementLabel = new Label(elementString, titleLabelStyle);
        elementLabel.setWrap(true);
        elementLabel.setWidth(240);

        elementLabel.setAlignment(Align.center);
        tableMap.add(elementLabel).width(240).padTop(10).padBottom(10);
        tableMap.row();

        levelLabel = new Label("Level: " + String.valueOf(enemy.level()), titleLabelStyle);
        levelLabel.setWrap(true);
        levelLabel.setWidth(240);

        levelLabel.setAlignment(Align.center);
        tableMap.add(levelLabel).width(240).padTop(10).padBottom(10);
        tableMap.row();

        expLabel = new Label("Experience: " + String.valueOf(enemy.getExperience()), titleLabelStyle);
        expLabel.setWrap(true);
        expLabel.setWidth(240);

        expLabel.setAlignment(Align.center);
        tableMap.add(expLabel).width(240).padTop(10).padBottom(10);
        tableMap.row();

        lifeLabel = new Label("Life Count: " + String.valueOf(enemy.lifeCount()), titleLabelStyle);
        lifeLabel.setWrap(true);
        lifeLabel.setWidth(240);

        lifeLabel.setAlignment(Align.center);
        tableMap.add(lifeLabel).width(240).padTop(10).padBottom(10);
        tableMap.row();

        playerPowerLabel = new Label("Your Power: " + String.valueOf(battle.getCalculatedPower1()), titleLabelStyle);
        playerPowerLabel.setWrap(true);
        playerPowerLabel.setWidth(240);

        playerPowerLabel.setAlignment(Align.center);
        tableMap.add(playerPowerLabel).width(240).padTop(10).padBottom(10);
        tableMap.row();

        enemyPowerLabel = new Label("Enemy Power: " + String.valueOf(battle.getCalculatedPower2()), titleLabelStyle);
        enemyPowerLabel.setWrap(true);
        enemyPowerLabel.setWidth(240);

        enemyPowerLabel.setAlignment(Align.center);
        tableMap.add(enemyPowerLabel).width(240).padTop(10).padBottom(10);
        tableMap.row();

        Table optionTable = new Table();
        Table yesTable = new Table();
        Table noTable = new Table();
        yesTable.setBackground(background3);
        noTable.setBackground(background3);
        yesButton = new TextButton("Yes", menuButtonStyle);
        noButton = new TextButton("No", menuButtonStyle);
        yesTable.add(yesButton).width(60);
        yesTable.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nameLabel = new Label("", titleLabelStyle);
                nameLabel.setWrap(true);
                nameLabel.setWidth(240);
                nameLabel.setAlignment(Align.center);

                tableMap.clear();
                tableMap.clearChildren();

                Engimon currentEngimon = currentPlayer.getCurrentEngimon();
                if(battle.getWinner() == 1) {
                    // Add Exp
                    int exp = 1/currentPlayer.getCurrentEngimon().level() * 200;
                    currentPlayer.getCurrentEngimon().xpGain(exp);

                    if (currentPlayer.getEngimonItem().size() >= 2 && currentEngimon.isOverLeveled()) {
                        currentPlayer.changeEngimon(currentPlayer.getEngimonItem().get(1));
                        currentPlayer.deleteItem(currentEngimon);
                    }
                    else {
                        game.setScreen(new GameOverScreen(game));
                    }
                    // Drop Skill
                    currentPlayer.addItem(enemy.getSkillArray().get(0));
                    nameLabel.setText("Battle Won");
                    tableMap.add(nameLabel).width(240).padTop(10).padBottom(10);
                }
                else {

                    currentEngimon.reduceLife();
                    nameLabel.setText("Battle Lost");
                    tableMap.add(nameLabel).width(240).padTop(10).padBottom(10);

                    // Deal With 0 Life Count
                    if(currentEngimon.lifeCount() == 0) {
                        if(currentPlayer.getEngimonItem().size() >= 2) {
                            currentPlayer.changeEngimon(currentPlayer.getEngimonItem().get(1));
                            currentPlayer.deleteItem(currentEngimon);
                        }
                        else {
                            // Game Over
                            game.setScreen(new GameOverScreen(game));
                        }
                    }
                }
                isBattlePrompt = false;

            }
        });
        noTable.add(noButton).width(60);
        noTable.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isBattlePrompt = false;
                tableMap.clear();
                tableMap.clearChildren();
            }
        });
        optionTable.add(yesTable);
        optionTable.add(noTable);
        tableMap.add(optionTable).width(240).padTop(10).padBottom(10);
    }

    public MainGameScreen(Game aGame, final Player currentPlayer) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());
        lastPoll = System.currentTimeMillis();
        isBattlePrompt = false;
        enemy = new Engimon(new Species(new Charmander()), false);

        mainScreenReference = this;

        this.currentPlayer = currentPlayer;
        currentPlayer.setEntityTileSize(tileWidth, tileHeight);
        currentPlayer.setTexture(new Texture(Gdx.files.internal("./sprites/player/idle_right.png")));
        currentPlayer.setSprite(new Sprite(currentPlayer.getTexture()));

        int row_height = Gdx.graphics.getWidth() / 12;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        // Style untuk Label
        titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont();
        titleLabelStyle.fontColor = Color.BLACK;

        warningLabelStyle = new Label.LabelStyle();
        warningLabelStyle.font = new BitmapFont();
        warningLabelStyle.fontColor = Color.RED;

        // Title Label
        titleLabel = new Label("Engimon Factory", titleLabelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
        titleLabel.setPosition(0,Gdx.graphics.getHeight()-row_height*1);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);

        // Style untuk TextButtons
        menuButtonStyle = new TextButton.TextButtonStyle();
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
        background3 = new NinePatchDrawable(patch3);


        // Tables untuk menyusun TextButtons
        table = new Table();
        table.setPosition(310,-30);

        tableMap = new Table();
        tableMap.setBackground(background);

        Label legendTitle = new Label("Legend", titleLabelStyle);
        legendTitle.setSize(Gdx.graphics.getWidth(),row_height);
        legendTitle.setPosition(0,Gdx.graphics.getHeight()-row_height*1);
        legendTitle.setAlignment(Align.center);
        tableMap.add(legendTitle).spaceBottom(20);
        tableMap.row();

        Texture electricTexture = new Texture(Gdx.files.internal("sprites/electric/left/move/1.png"));
        Table electricTable = new Table();
        Image electricImage = new Image(electricTexture);
        electricTable.add(electricImage).width(45).height(45).padRight(2);
        Label electricLabel = new Label("Electric", titleLabelStyle);
        electricTable.add(electricLabel).width(40).height(40);
        tableMap.add(electricTable);
        tableMap.row();

        Texture fireTexture = new Texture(Gdx.files.internal("sprites/fire/left/move/1.png"));
        Table fireTable = new Table();
        Image fireImage = new Image(fireTexture);
        fireTable.add(fireImage).width(45).height(45).padRight(2);
        Label fireLabel = new Label("Fire", titleLabelStyle);
        fireTable.add(fireLabel).width(40).height(40);
        tableMap.add(fireTable);
        tableMap.row();

        Texture groundTexture = new Texture(Gdx.files.internal("sprites/ground/left/move/1.png"));
        Table groundTable = new Table();
        Image groundImage = new Image(groundTexture);
        groundTable.add(groundImage).width(55).height(55).padRight(-3);
        Label groundLabel = new Label("Ground", titleLabelStyle);
        groundTable.add(groundLabel).width(40).height(40);
        tableMap.add(groundTable);
        tableMap.row();

        Texture iceTexture = new Texture(Gdx.files.internal("sprites/ice/32bit-cuttlefish1.png"));
        Table iceTable = new Table();
        Image iceImage = new Image(iceTexture);
        iceTable.add(iceImage).width(30).height(30).padRight(20);
        Label iceLabel = new Label("Ice", titleLabelStyle);
        iceTable.add(iceLabel).width(40).height(40);
        tableMap.add(iceTable);
        tableMap.row();

        Texture waterTexture = new Texture(Gdx.files.internal("sprites/water/left/move/1.png"));
        Table waterTable = new Table();
        Image waterImage = new Image(waterTexture);
        waterTable.add(waterImage).width(65).height(65).padRight(-5);
        Label waterLabel = new Label("Water", titleLabelStyle);
        waterTable.add(waterLabel).width(40).height(40);
        tableMap.add(waterTable);
        tableMap.row();

        Texture fireElectricTexture = new Texture(Gdx.files.internal("sprites/fire electric/left/move/1.png"));
        Table fireElectricTable = new Table();
        Image fireElectricImage = new Image(fireElectricTexture);
        fireElectricTable.add(fireElectricImage).width(20).height(30).padRight(10);
        Label fireElectricLabel = new Label("Fire Electric", titleLabelStyle);
        fireElectricTable.add(fireElectricLabel).width(40).height(40);
        tableMap.add(fireElectricTable);
        tableMap.row();

        Texture groundWaterTexture = new Texture(Gdx.files.internal("sprites/ground water/left/move/1.png"));
        Table groundWaterTable = new Table();
        Image groundWaterImage = new Image(groundWaterTexture);
        groundWaterTable.add(groundWaterImage).width(30).height(30).padRight(20);
        Label groundWaterLabel = new Label("Ground Water", titleLabelStyle);
        groundWaterTable.add(groundWaterLabel).width(40).height(40);
        tableMap.add(groundWaterTable);
        tableMap.row();

        Texture iceWaterTexture = new Texture(Gdx.files.internal("sprites/ice water/32bit-cuttlefish1.png"));
        Table iceWaterTable = new Table();
        Image iceWaterImage = new Image(iceWaterTexture);
        iceWaterTable.add(iceWaterImage).width(30).height(30).padRight(20);
        Label iceWaterLabel = new Label("Ice Water", titleLabelStyle);
        iceWaterTable.add(iceWaterLabel).width(40).height(40);
        tableMap.add(iceWaterTable);
        tableMap.row();

        table.add(tableMap).width(300).height(450).right().center();

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
        mainGameLogic = new GameLogic(currentPlayer, renderer, map);
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

        ArrayList<Entity> currentState = mainGameLogic.getEntities();
        for (Entity ent : currentState) {
            if (ent instanceof Engimon) {
                float targetSize = 10*((Engimon) ent).level();
                if (targetSize > 40)
                    ent.getSprite().setSize(targetSize, targetSize);
                else
                    ent.getSprite().setSize(40, 40);
            }
            ent.getSprite().setPosition(tileWidth*ent.getPosition().x, tileHeight*ent.getPosition().y);
        }

        renderer.addSprite(currentPlayer.getSprite());
        renderer.setView(camera);
        renderer.render();

        batch.begin();
//        sprite.draw(batch);
        batch.end();

        if (!isBattlePrompt) {
            String keydata = playerKeyboardInput.getKeypress();
            if (keydata != null) {
                Entity collidedEntity = mainGameLogic.playerInput(keydata);
                if (collidedEntity != null) {
                    isBattlePrompt = true;
                    battleDialog();
                }
                lastPoll = System.currentTimeMillis();
            }
        }

        if (System.currentTimeMillis() - lastPoll > 250) {
            mainGameLogic.tickUpdate();
            lastPoll = System.currentTimeMillis();
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
