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
import com.mygdx.game.entity.SkillInventory;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.Skill;
import com.mygdx.game.styles.BackgroundColor;

import java.util.ArrayList;

public class InventoryScreen implements Screen {
    private Stage stage;
    private Game game;
    private SpriteBatch batch;
    private Label titleLabel;
    private Table table;
    private String[] inventoryDummy = {"Powerball", "Health Potion", "Mana Potion", "Grenade"};
    private String[] descriptionDummy =
            {"An item that will increase your Engimon Strength",
            "Increase your health by 20 points",
            "Increase your mana by 20 points",
            "Damage your enemy by 25 points"};
    private Skill selectedSkill = null;
    private Label descriptionLabel;
    private ArrayList<Skill> skillList;
    private Player currentPlayer;

    TextButton.TextButtonStyle menuButtonStyle;
    TextButton.TextButtonStyle selectedButtonStyle;

    private Label nameLabel;
    private Label elementLabel;
    private Label powerLabel;
    private Label masteryLabel;
    private Label countLabel;

    private TextButton selectButton;
    private TextButton focusButton;

    private Table tableInventory;
    private Table tableLearnedInventory;

    public void getSkillList() {
        skillList = new ArrayList<Skill>();

        for(Element element : currentPlayer.getCurrentEngimon().getSpecies().getElementSet()) {
            skillList.addAll(currentPlayer.getSkillByElement(element));
        }
    }

    public ArrayList<Skill> getLearntSkillList() {
        return currentPlayer.getCurrentEngimon().getSkillArray();
    }

    public void addSkill(Skill e) {
        currentPlayer.addItem(e);
    }

    public void deleteSkill(Skill e) {
        currentPlayer.deleteItem(e);
    }

    public void learnSkill(Skill e) {
        currentPlayer.getCurrentEngimon().addSkill(selectedSkill);
        currentPlayer.deleteItem(selectedSkill);
    }

    public void forgetSkill(Skill e) {
        currentPlayer.getCurrentEngimon().deleteSkill(e.skillName());
    }

    public boolean isSkillLearnt(Skill e) {
        return getLearntSkillList().contains(e);
    }

    public InventoryScreen(Game aGame, final Player currentPlayer) {
        // Setup Stage
        game = aGame;
        stage = new Stage(new ScreenViewport());
        this.currentPlayer = currentPlayer;
        getSkillList();

        // Default
        selectedSkill = currentPlayer.getCurrentEngimon().getSkillArray().get(0);

        int row_height = Gdx.graphics.getWidth() / 12;
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        // Style untuk Label
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont();
        titleLabelStyle.fontColor = Color.BLACK;

        // Title Label
        titleLabel = new Label("Skill", titleLabelStyle);
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
        TextButton inventoryButton = new TextButton("Unlearnt Skills", menuButtonStyle);
        TextButton learnedInventoryButton = new TextButton("Learnt Skills", menuButtonStyle);
        TextButton statButton = new TextButton("Description", menuButtonStyle);
        selectButton = new TextButton("Learn", menuButtonStyle);
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

        NinePatch patch3 = new NinePatch(new Texture(Gdx.files.internal("background-button.png")),
                1, 1, 1, 1);
        NinePatchDrawable background3 = new NinePatchDrawable(patch3);


        // Tables untuk menyusun TextButtons
        table = new Table();

        table.add().width(300).spaceRight(0).padTop(80);
        table.add().width(200).padTop(80);
        table.row();

        Table tableLeft = new Table();
        tableLeft.padRight(30);

        tableInventory = new Table();
        tableInventory.setBackground(background);

        tableLeft.add(inventoryButton);
        tableLeft.row();
        tableLeft.add(tableInventory).width(300).height(240);
        tableLeft.row();
        tableLeft.add(learnedInventoryButton).spaceTop(20);
        tableLeft.row();

        tableLearnedInventory = new Table();
        tableLearnedInventory.setBackground(background);

        tableLeft.add(tableLearnedInventory).width(300).height(240);
        table.add(tableLeft);

        Table tableRight = new Table();
        tableRight.top();
        tableRight.add(statButton).top();
        tableRight.row();

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

        powerLabel = new Label("", titleLabelStyle);
        powerLabel.setWrap(true);
        powerLabel.setWidth(240);
        powerLabel.setSize(Gdx.graphics.getWidth(),row_height);
        powerLabel.setAlignment(Align.center);
        tableStats.add(powerLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        masteryLabel = new Label("", titleLabelStyle);
        masteryLabel.setWrap(true);
        masteryLabel.setWidth(240);
        masteryLabel.setSize(Gdx.graphics.getWidth(),row_height);
        masteryLabel.setAlignment(Align.center);
        tableStats.add(masteryLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        countLabel = new Label("", titleLabelStyle);
        countLabel.setWrap(true);
        countLabel.setWidth(240);
        countLabel.setSize(Gdx.graphics.getWidth(),row_height);
        countLabel.setAlignment(Align.center);
        tableStats.add(countLabel).width(240).padTop(10).padBottom(10);
        tableStats.row();

        tableRight.add(tableStats);
        tableRight.row();

        Table tableSelect = new Table();
        tableSelect.add(selectButton);
        tableSelect.setBackground(background3);
        tableSelect.setTouchable(Touchable.enabled);
        tableSelect.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!isSkillLearnt(selectedSkill)) {
                    learnSkill(selectedSkill);
                    focusButton.setStyle(selectedButtonStyle);
                    getSkillList();
                }
                else {
                    forgetSkill(selectedSkill);
                    focusButton.setStyle(menuButtonStyle);
                }
            }
        });
        tableRight.add(tableSelect).width(100).height(70).spaceTop(10).spaceBottom(10);

        table.add(tableRight).width(300).height(500).top();

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

        tableInventory.clear();
        tableInventory.clearChildren();
        tableInventory.top().padTop(20);
        for(final Skill skill : skillList) {
            final TextButton itemButton;
            if(skill.itemCount() > 0) {
                if(skill.equals(selectedSkill)) {
                    itemButton = new TextButton(skill.skillName(), selectedButtonStyle);
                }
                else {
                    itemButton = new TextButton(skill.skillName(), menuButtonStyle);
                }
                itemButton.addListener(new InputListener() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        focusButton = itemButton;
                        selectedSkill = skill;
                    }

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                });
                tableInventory.add(itemButton).padTop(10).padBottom(10);
                tableInventory.row();
            }
        }

        tableLearnedInventory.clear();
        tableLearnedInventory.clearChildren();
        for(final Skill skill : getLearntSkillList()) {
            final TextButton itemButton;
            if(skill.equals(selectedSkill)) {
                itemButton = new TextButton(skill.skillName(), selectedButtonStyle);
            }
            else {
                itemButton = new TextButton(skill.skillName(), menuButtonStyle);
            }
            itemButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    focusButton = itemButton;
                    selectedSkill = skill;
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            tableLearnedInventory.add(itemButton).padTop(10).padBottom(10);
            tableLearnedInventory.row();
        }

        // Rerender Labels
        if(selectedSkill != null) {
            nameLabel.setText("Name\n" + selectedSkill.skillName());
            elementLabel.setText("Element\n" + selectedSkill.skillElement().toString());
            powerLabel.setText("Base Power\n" + String.valueOf(selectedSkill.basePower()));
            masteryLabel.setText("Mastery\n" + String.valueOf(selectedSkill.masteryLevel()));
            countLabel.setText("Available\n" + String.valueOf(selectedSkill.itemCount()));
        }

        if(isSkillLearnt(selectedSkill)) {
            selectButton.setText("Forget");
        }
        else {
            selectButton.setText("Learn");
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
