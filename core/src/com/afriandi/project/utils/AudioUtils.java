package com.afriandi.project.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioUtils 
{
	private static AudioUtils instance = new AudioUtils();
    private static Music apMusic;
    private static Music mainMenuMusic;
    private static Sound jumpSound;
    private static Sound hitSound;

    private static final String MUSIC_ON_PREFERENCE = "music_on";
    private static final String SOUND_ON_PREFERENCE = "sound_on";
    
    private AudioUtils()
    {
        apMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.GAMEPLAY_MUSIC));
        apMusic.setLooping(true);

        mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.MAIN_MENU_MUSIC));
        mainMenuMusic.setLooping(true);

        jumpSound = createSound(Constants.SFX_JUMP);
        hitSound = createSound(Constants.SFX_HIT);
    }
    
    public static AudioUtils getInstance()
    {
    	if(instance == null)
    		instance = new AudioUtils();
    	
    	return instance;
    }
    
    public Sound createSound(String soundFileName) 
    {
        return Gdx.audio.newSound(Gdx.files.internal(soundFileName));
    }
    
    public void playMusic(Music music)
    {
        boolean musicOn = getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true);
        if (musicOn) 
        {
        	music.play();
        }
    }
    
    public void stopMusic(Music music)
    {
        if (music.isPlaying())
        {
        	music.stop();
        }
    }
    
    public void playSound(Sound sound) 
    {
        boolean soundOn = getPreferences().getBoolean(SOUND_ON_PREFERENCE, true);
        if (soundOn)
        {
            sound.play();
        }
    }
    
    public void toggleMusic() 
    {
        saveBoolean(MUSIC_ON_PREFERENCE, !getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true));
    }

    public void toggleSound() 
    {
        saveBoolean(SOUND_ON_PREFERENCE, !getPreferences().getBoolean(SOUND_ON_PREFERENCE, true));
    }
    
    private void saveBoolean(String key, boolean value) 
    {
        Preferences preferences = getPreferences();
        preferences.putBoolean(key, value);
        preferences.flush();
    }
    
    public void pauseMusic() 
    {
        apMusic.pause();
    }
    
    public int getSoundRegionId() 
    {
        boolean soundOn = getPreferences().getBoolean(SOUND_ON_PREFERENCE, true);
        return soundOn ? Constants.BUTTON_SFX : Constants.BUTTON_SFX_OFF;
    }

    public int getMusicRegionId() 
    {
        boolean musicOn = getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true);
        return musicOn ? Constants.BUTTON_MUSIC : Constants.BUTTON_MUSIC_OFF;
    }

    public Music getMM_Music()
    {
    	return mainMenuMusic;
    }
    
    public Music getAP_Music()
    {
    	return apMusic;
    }
    
    public Sound getJumpSound() 
    {
        return jumpSound;
    }

    public Sound getHitSound() 
    {
        return hitSound;
    }
    
    private Preferences getPreferences() 
    {
        return Gdx.app.getPreferences(GameManager.PREFERENCES_NAME);
    }
    
    public static void dispose() 
    {
    	mainMenuMusic.dispose();
        apMusic.dispose();
        jumpSound.dispose();
        hitSound.dispose();
    }
}
