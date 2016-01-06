package proj.unipe.entities;

//classe abstrata, onde todos os beans irão herdar dela para serem obrigados a implementar o método setId e getId
public abstract class AbstractEntity {
	//metodos abstratos que irão ser implementados nas classes beans que irão extender essa classe
	public abstract Long getId();
	public abstract void setId(Long id);
	
	@Override
	public boolean equals(Object obj) {
		AbstractEntity entidade = (AbstractEntity)  obj;
		if(this.temIdValido() && entidade.temIdValido() && this.getId().equals(entidade.getId())){
			return true;
		}
		return false;
	}
	
	public boolean temIdValido(){
		return getId() != null && getId() != 0;
	}	
	
	
}
