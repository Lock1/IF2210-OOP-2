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
import com.mygdx.game.styles.BackgroundColor;

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
    private String currentDescription = "";
    private Label descriptionLabel;

    public InventoryScreen(Game aGame) {
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
        titleLabel = new Label("Inventory", titleLabelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(),row_height);
        titleLabel.setPosition(0,Gdx.graphics.getHeight()-row_height*1);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);

        // Style untuk TextButtons
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.font = new BitmapFont();
        menuButtonStyle.fontColor = Color.BLACK;

        // Definisi dan Implementasi TextButtons
        TextButton inventoryButton = new TextButton("Your Items", menuButtonStyle);
        TextButton statButton = new TextButton("Description", menuButtonStyle);
        TextButton backButton = new TextButton("<< Back", menuButtonStyle);
        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainGameScreen(game));
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

        table.add(inventoryButton).width(400).spaceRight(60);
        table.add(statButton).width(200);
        table.row();

        Table tableInventory = new Table();
        tableInventory.setBackground(background);
        tableInventory.top().padTop(20);
        for(int i=0;i<inventoryDummy.length;i++) {
            TextButton itemButton = new TextButton(inventoryDummy[i], menuButtonStyle);
            tableInventory.add(itemButton).padTop(10).padBottom(10);
            final int j = i;
            itemButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    currentDescription = descriptionDummy[j];
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            tableInventory.row();
        }
        table.add(tableInventory).width(400).height(300).spaceRight(60);

        Table tableDescription = new Table();
        tableDescription.setBackground(background);
        descriptionLabel = new Label(currentDescription, titleLabelStyle);
        descriptionLabel.setWrap(true);
        descriptionLabel.setWidth(160);
        descriptionLabel.setSize(Gdx.graphics.getWidth(),row_height);
        descriptionLabel.setAlignment(Align.center);
        tableDescription.add(descriptionLabel).width(160);
//        BackgroundColor backgroundColor = new BackgroundColor("white_color_texture.png");
//        backgroundColor.setColor(2, 179, 228, 255); // r, g, b, a
//        tableDescription.setBackground(backgroundColor);
        table.add(tableDescription).width(200).height(300);

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
        descriptionLabel.setText(currentDescription);
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
