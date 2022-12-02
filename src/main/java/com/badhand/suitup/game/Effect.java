package com.badhand.suitup.game;

public class Effect {
    private Effects effect;
    private float value;

    public Effect(Effects effect) {
        this.effect = effect;
        switch(effect){
            case DAMAGE_MODIFIER:
                value = 1.1f;
                break;
            case BUST_PROOF:
                value = 0.0f;
                break;
            case HEAL:
                value = 1f;
                break;
            case INSTANT_DAMAGE:
                value = 1f;
                break;
            default:
                value = 1f;
                break;
        }
    }

    public void upgrade(){
        switch(effect){
            case DAMAGE_MODIFIER:
                value += 0.1f;
                break;
            case BUST_PROOF:
                break;
            case HEAL:
                value += 1;
                break;
            case INSTANT_DAMAGE:
                value += 1;
                break;
            default:
                break;
        }
    }

    public Effects getEffect() {
        return effect;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
}
