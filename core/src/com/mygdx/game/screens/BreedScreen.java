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
import com.mygdx.game.SpeciesDatabase;
import com.mygdx.game.action.Breed;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.Player;

import java.util.ArrayList;

public class BreedScreen implements Screen {
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
    private Engimon currentParentOne;
    private Engimon currentParentTwo;
    private Engimon resultChild;
    private Label breedOneLabel;
    private Label breedTwoLabel;
    private Label resultLabel;
    private Player currentPlayer;
    private MainGameScreen parentMain;


    

    private TextButton.TextButtonStyle menuButtonStyle;
    private Label.LabelStyle selectedButtonStyle;

    private Breed breed;

    private ArrayList<Engimon> parentList;

    public void initParents() {
        parentList = currentPlayer.getEngimonByMinLevel(4);
    }

    public BreedScreen(Game aGame, final Player currentPlayer, MainGameScreen mainparent) {

        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());
        this.currentPlayer = currentPlayer;
        initParents();

        int row_height = Gdx.graphics.getWidth() / 12;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        parentMain = mainparent;

        // Style untuk Label
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont();
        titleLabelStyle.fontColor = Color.BLACK;

        // Title Label
        titleLabel = new Label("Breed", titleLabelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
        titleLabel.setPosition(0,Gdx.graphics.getHeight()-row_height*1);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);

        // Result TextButtons
        resultLabel = new Label("", titleLabelStyle);
        resultLabel.setWrap(true);
        resultLabel.setAlignment(Align.center);
        resultLabel.setWidth(150);

        // Style untuk TextButtons dan Label
        menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont();
        menuButtonStyle.fontColor = Color.BLACK;

        selectedButtonStyle = new Label.LabelStyle();
        selectedButtonStyle.font = new BitmapFont();
        selectedButtonStyle.fontColor = Color.RED;

        // Definisi dan Implementasi TextButtons
        TextButton engimonButton = new TextButton("Your Engimon", menuButtonStyle);
        TextButton engimonOneButton = new TextButton("Engimon 1", menuButtonStyle);
        TextButton engimonTwoButton = new TextButton("Engimon 2", menuButtonStyle);
        TextButton resultButton = new TextButton("Result", menuButtonStyle);
        TextButton breedButton = new TextButton("Breed", menuButtonStyle);
        TextButton backButton = new TextButton("<< Back", menuButtonStyle);
        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(parentMain);
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

        NinePatch patch2 = new NinePatch(new Texture(Gdx.files.internal("background.png")),
                1, 1, 1, 1);
        NinePatchDrawable background2 = new NinePatchDrawable(patch2);

        NinePatch patch3 = new NinePatch(new Texture(Gdx.files.internal("background-button.png")),
                1, 1, 1, 1);
        NinePatchDrawable background3 = new NinePatchDrawable(patch3);


        // Tables untuk menyusun TextButtons
        table = new Table();

        table.add(engimonButton).width(300).spaceRight(30).padTop(30);
        // table.add(statButton).width(200);
        table.row();

        Table tableEngimon = new Table();
        tableEngimon.setBackground(background);
        tableEngimon.top().padTop(20);
        for(final Engimon parent : parentList) {
            TextButton itemButton = new TextButton(parent.engimonName(), menuButtonStyle);
            tableEngimon.add(itemButton).padTop(10).padBottom(10);
            itemButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if(currentParentOne == null) {
                        currentParentOne = parent;
                    }
                    else if (currentParentTwo  == null) {
                        currentParentTwo = parent;
                    }
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            tableEngimon.row();
        }
        table.add(tableEngimon).width(300).height(500).spaceRight(30);

        Table tableBreed = new Table();
        tableBreed.padTop(30);

        Table tableEngimonOne = new Table();
        tableEngimonOne.setBackground(background2);
        breedOneLabel = new Label("", titleLabelStyle);
        breedOneLabel.setWrap(true);
        breedOneLabel.setWidth(160);
        breedOneLabel.setSize(Gdx.graphics.getWidth(),row_height);
        breedOneLabel.setAlignment(Align.center);
        breedOneLabel.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                currentParentOne = null;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        tableEngimonOne.add(breedOneLabel).width(140);

        Table tableEngimonTwo = new Table();
        tableEngimonTwo.setBackground(background2);
        breedTwoLabel = new Label("", titleLabelStyle);
        breedTwoLabel.setWrap(true);
        breedTwoLabel.setWidth(160);
        breedTwoLabel.setSize(Gdx.graphics.getWidth(),row_height);
        breedTwoLabel.setAlignment(Align.center);
        breedTwoLabel.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                currentParentTwo = null;
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        tableEngimonTwo.add(breedTwoLabel).width(140);

        Table tableBreedButton = new Table();
        tableBreedButton.add(breedButton);
        tableBreedButton.setBackground(background3);
        tableBreedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(currentParentOne == null || currentParentTwo == null) {
                    resultLabel.setText("Please Select Two Parent Engimons");
                    resultLabel.setStyle(selectedButtonStyle);
                }
                else {
                    breed = new Breed(currentParentOne, currentParentTwo);
                    resultChild = breed.startBreeding(new SpeciesDatabase());
                    resultLabel.setText(resultChild.engimonName());
                }
            }
        });

        tableBreed.add(engimonOneButton);
        tableBreed.row();
        tableBreed.add(tableEngimonOne).width(170).height(150);
        tableBreed.row();
        tableBreed.add(engimonTwoButton);
        tableBreed.row();
        tableBreed.add(tableEngimonTwo).width(170).height(150);
        tableBreed.row();
        tableBreed.add(tableBreedButton).width(100).height(70);

        table.add(tableBreed).width(170).height(300).spaceLeft(15).spaceRight(15);

        Table tableRightest = new Table();
        tableRightest.add(resultButton).spaceBottom(3);
        tableRightest.row();
        Table tableResult = new Table();
        tableResult.setBackground(background2);
        tableResult.add(resultLabel).width(150);

        tableRightest.add(tableResult);

        table.add(tableRightest).width(170).height(150).spaceLeft(30);

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
        if(currentParentOne != null) {
            breedOneLabel.setText(currentParentOne.engimonName());
        }
        if(currentParentTwo != null) {
            breedTwoLabel.setText(currentParentTwo.engimonName());
        }
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
