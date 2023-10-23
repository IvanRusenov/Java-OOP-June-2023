package magicGame.models.magics;

import magicGame.common.ExceptionMessages;
import magicGame.common.OutputMessages;

public abstract class MagicImpl implements Magic{

    private String name;
    private int bulletsCount;

    public MagicImpl(String name, int bulletsCount) {
        setName(name);
        setBulletsCount(bulletsCount);
    }


    public void setName(String name) {

        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_NAME);
        }

        this.name = name;
    }

    public void setBulletsCount(int bulletsCount) {

        if (bulletsCount < 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_BULLETS_COUNT);
        }

        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

}
