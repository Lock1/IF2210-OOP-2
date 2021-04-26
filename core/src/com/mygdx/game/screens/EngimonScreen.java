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
    private Engimon selectedEngimon = null;
    private Label nameLabel;
    private Label elementLabel;
    private Label parentNameLabel;
    private Label parentSpeciesLabel;
    private Label levelLabel;
    private Label expLabel;
    private Label lifeLabel;
//    private Label skillLabel;
    private ArrayList<Engimon> engimonList;
    private Player currentPlayer;
    private TextButton focusButton;
    private TextButton previousButton;
    private TextButton.TextButtonStyle menuButtonStyle;
    private TextButton.TextButtonStyle selectedButtonStyle;
    private MainGameScreen parentMain;

    public void getEngimonList() {
        engimonList = currentPlayer.getEngimonItem();
    }

    public void addEngimon(Engimon e) {
        currentPlayer.addItem(e);
    }

    public void deleteEngimon(Engimon e) {
        currentPlayer.deleteItem(e);
    }

    public void setCurrentEngimon(Engimon e) {
        currentPlayer.changeEngimon(selectedEngimon);
    }

    public EngimonScreen(Game aGame, final Player currentPlayer, MainGameScreen mainparent) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());
        this.currentPlayer = currentPlayer;
        getEngimonList();

        parentMain = mainparent;

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
        menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont();
        menuButtonStyle.fontColor = Color.BLACK;

        selectedButtonStyle = new TextButton.TextButtonStyle();
        selectedButtonStyle.font = new BitmapFont();
        selectedButtonStyle.fontColor = Color.RED;

        // Definisi dan Implementasi TextButtons
        TextButton engimonButton = new TextButton("Your Engimon", menuButtonStyle);
        TextButton statButton = new TextButton("Stats", menuButtonStyle);
        TextButton backButton = new TextButton("<< Back", menuButtonStyle);
        TextButton selectButton = new TextButton("Select", menuButtonStyle);
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

        NinePatch patch3 = new NinePatch(new Texture(Gdx.files.internal("background-button.png")),
                1, 1, 1, 1);
        NinePatchDrawable background3 = new NinePatchDrawable(patch3);


        // Tables untuk menyusun TextButtons
        table = new Table();

        table.add(engimonButton).width(400).spaceRight(40).padTop(80);
        table.add(statButton).width(200).padTop(80);
        table.row();

        Table tableEngimon = new Table();
        tableEngimon.setBackground(background);
        tableEngimon.top().padTop(20);
        for (final Engimon engimon : engimonList) {
            final TextButton itemButton;
            if(currentPlayer.getCurrentEngimon().equals(engimon)) {
                itemButton = new TextButton(engimon.engimonName(), selectedButtonStyle);
                previousButton = itemButton;
            }
            else {
                itemButton = new TextButton(engimon.engimonName(), menuButtonStyle);
            }
            tableEngimon.add(itemButton).padTop(10).padBottom(10);
            itemButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    focusButton = itemButton;
                    selectedEngimon = engimon;
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            tableEngimon.row();
        }
        table.add(tableEngimon).width(400).height(500).spaceRight(40);

        Table tableRight = new Table();

        Table tableStats = new Table();
        tableStats.top().padTop(20).setBackground(background);

        nameLabel = new Label("", titleLabelStyle);
        nameLabel.setWrap(true);
        nameLabel.setWidth(240);
        nameLabel.setSize(Gdx.graphics.getWidth(),row_height);
        nameLabel.setAlignment(Align.center);
        tableStats.add(nameLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        elementLabel = new Label("", titleLabelStyle);
        elementLabel.setWrap(true);
        elementLabel.setWidth(240);
        elementLabel.setSize(Gdx.graphics.getWidth(),row_height);
        elementLabel.setAlignment(Align.center);
        tableStats.add(elementLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        levelLabel = new Label("", titleLabelStyle);
        levelLabel.setWrap(true);
        levelLabel.setWidth(240);
        levelLabel.setSize(Gdx.graphics.getWidth(),row_height);
        levelLabel.setAlignment(Align.center);
        tableStats.add(levelLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        expLabel = new Label("", titleLabelStyle);
        expLabel.setWrap(true);
        expLabel.setWidth(240);
        expLabel.setSize(Gdx.graphics.getWidth(),row_height);
        expLabel.setAlignment(Align.center);
        tableStats.add(expLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        lifeLabel = new Label("", titleLabelStyle);
        lifeLabel.setWrap(true);
        lifeLabel.setWidth(240);
        lifeLabel.setSize(Gdx.graphics.getWidth(),row_height);
        lifeLabel.setAlignment(Align.center);
        tableStats.add(lifeLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        parentNameLabel = new Label("", titleLabelStyle);
        parentNameLabel.setWrap(true);
        parentNameLabel.setWidth(240);
        parentNameLabel.setSize(Gdx.graphics.getWidth(),row_height);
        parentNameLabel.setAlignment(Align.center);
        tableStats.add(parentNameLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        parentSpeciesLabel = new Label("", titleLabelStyle);
        parentSpeciesLabel.setWrap(true);
        parentSpeciesLabel.setWidth(240);
        parentSpeciesLabel.setSize(Gdx.graphics.getWidth(),row_height);
        parentSpeciesLabel.setAlignment(Align.center);
        tableStats.add(parentNameLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

//        skillLabel = new Label("", titleLabelStyle);
//        skillLabel.setWrap(true);
//        skillLabel.setWidth(240);
//        skillLabel.setSize(Gdx.graphics.getWidth(),row_height);
//        skillLabel.setAlignment(Align.center);
//        tableStats.add(skillLabel).width(240).padTop(10).padBottom(10);
//        tableStats.row();

        tableRight.add(tableStats);
        tableRight.row();

        Table tableSelect = new Table();
        tableSelect.add(selectButton);
        tableSelect.setBackground(background3);
        tableSelect.setTouchable(Touchable.enabled);
        tableSelect.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(selectedEngimon != null) {
                    previousButton.setStyle(menuButtonStyle);
                    previousButton = focusButton;
                    setCurrentEngimon(selectedEngimon);
                    focusButton.setStyle(selectedButtonStyle);
                }
            }
        });
        tableRight.add(tableSelect).width(100).height(70).spaceTop(10).spaceBottom(10);

        table.add(tableRight).width(300).height(500);

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

        // Rerender Labels
        if(selectedEngimon != null) {
            nameLabel.setText("Name\n" + selectedEngimon.engimonName());
            String elementString = "Element\n" + selectedEngimon.getSpecies().getElementSet().toArray()[0].toString();
            if (selectedEngimon.getSpecies().getElementSet().size() == 2)
                elementString = elementString + ", " + selectedEngimon.getSpecies().getElementSet().toArray()[1].toString();

            elementLabel.setText(elementString);
            levelLabel.setText("Level\n" + String.valueOf(selectedEngimon.level()));
            expLabel.setText("Experience\n" + String.valueOf(selectedEngimon.getExperience()));
            lifeLabel.setText("Life Count\n" + String.valueOf(selectedEngimon.lifeCount()));

            if (selectedEngimon.getParentName() != null) {
                parentNameLabel.setText("Parents\n" + selectedEngimon.getParentName()[0] + ", " + selectedEngimon.getParentName()[1]);
                parentSpeciesLabel.setText(selectedEngimon.getParentSpecies()[0].speciesName() + ", " + selectedEngimon.getParentSpecies()[1].speciesName());
            }
            else {
                parentNameLabel.setText("");
                parentSpeciesLabel.setText("");
            }
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
