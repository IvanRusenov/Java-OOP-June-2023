package magicGame.models.magicians;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;

public abstract class MagicianImpl implements Magician {

    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;


    public MagicianImpl(String username, int health, int protection, Magic magic) {
        setUsername(username);
        setHealth(health);
        setProtection(protection);
        setMagic(magic);
        setAlive(true);
    }

    public void setUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_NAME);
        }

        this.username = username;
    }

    public void setHealth(int health) {

        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_HEALTH);
        }

        this.health = health;
    }

    public void setProtection(int protection) {

        if (protection < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_PROTECTION);
        }

        this.protection = protection;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setMagic(Magic magic) {

        if (magic == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC);
        }

        this.magic = magic;
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getProtection() {
        return this.protection;
    }

    @Override
    public Magic getMagic() {
        return this.magic;
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    @Override
    public void takeDamage(int points) {
        //TODO Transffer the rest of the damage to health
        int newProtection = this.protection - points;

        if (this.protection - points < 0) {
            this.protection = 0;
            this.health = health + newProtection;//newProtection is negative
        } else {
            this.protection -= points;
        }
        //TODO CHECK LOGIC
        if (this.health <= 0) {
            health = 0;
            setAlive(false);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s", this.getClass().getSimpleName(), this.getUsername()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Health: %d", this.getHealth())).append(System.lineSeparator());
        sb.append(String.format("Protection: %d", this.getProtection())).append(System.lineSeparator());
        sb.append(String.format("Magic: %s", this.getMagic().getName()));

        return sb.toString().trim();
    }
}
