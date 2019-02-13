package api.arq.validator;

import api.arq.modelo.AbstractEntity;


public abstract class AbstractCRUDValidador<AbstractEntity> extends  AbstractValidator{
    public abstract void validarAntesDeSalvar(AbstractEntity T);
    public abstract void validarAntesDeRemover(AbstractEntity T);
}
