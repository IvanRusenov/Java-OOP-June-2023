package magicGame.repositories.interfaces;

import magicGame.common.ExceptionMessages;
import magicGame.models.magics.Magic;

import java.util.ArrayList;
import java.util.Collection;

public class MagicRepositoryImpl implements MagicRepository<Magic>{

    private Collection<Magic> data;

    public MagicRepositoryImpl() {
        this.data = new ArrayList<>();
    }

    @Override
    public Collection<Magic> getData() {
        return this.data;
    }

    @Override
    public void addMagic(Magic magic) {
        if (magic == null){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_REPOSITORY);
        }

        this.data.add(magic);
    }

    @Override
    public boolean removeMagic(Magic magic) {
      return data.remove(magic);
    }

    @Override
    public Magic findByName(String name) {
       return data.stream().filter(m -> m.getName().equals(name)).findFirst().orElse(null);
    }
}
