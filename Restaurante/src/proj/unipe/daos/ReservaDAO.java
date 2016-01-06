package proj.unipe.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import proj.unipe.entities.Cliente;
import proj.unipe.entities.Mesa;
import proj.unipe.entities.Reserva;

@Repository
public class ReservaDAO extends AbstractDAO<Reserva> {

	private Reserva reserva;
	
	private Session session;
	
	public ReservaDAO() {
		
		super(Reserva.class);
		
		
	}
	// Buscando mesa por numero com CRITERIA(hibernate)
		public List<Reserva> buscaPorNumMesa(int numero) {
			this.session = (Session) manager.getDelegate();

			Criteria crit = session.createCriteria(Mesa.class);
			crit.add(Restrictions.eq("capacidade", numero));
			List<Reserva> results = crit.list();

			return results;
		}
		
		
		public List<Mesa> buscaPorMesaDeReserva(boolean b) {
			this.session = (Session) manager.getDelegate();

			Criteria crit = session.createCriteria(Mesa.class);
			crit.add(Restrictions.eq("deReserva", b));
			List results = crit.list();

			return results;
		}
		
		@SuppressWarnings("unchecked")
		public List<Reserva> buscarReservaPorMesa(Reserva filtro) {
			String sql = "select p from Reserva p where p.mesa.id = :idMesa";
			Query query = manager.createQuery(sql);
			query.setParameter("idMesa", filtro.getMesa().getId());
			return query.getResultList();
		}
		
		
		public List<Reserva> buscarPorNomeResponsavel(String filtro) {
			String sql = "select r from Reserva r where upper(r.nome_responsavel) like upper(:nome_responsavel)";
			Query query = manager.createQuery(sql);
			query.setParameter("nome_responsavel", "%"+filtro+"%");
			return query.getResultList();
		}
	
	public List<Reserva> buscarReserva(Reserva filtro) {
		String sql = "select r from Reserva r where 1=1";
		if(filtro.getDataInicial() != null && filtro.getDataInicial().equals("")){
			sql += "and r.dataInicial =" + filtro.getDataInicial();
		}
		if(filtro.getDataFinal() != null && filtro.getDataFinal().equals("")){
			sql += "and r.dataFinal =" + filtro.getDataFinal();
		}
		if(filtro.getMesa().getId() != null && filtro.getMesa().getId().equals("")){
			sql += "and r.mesa.id =" + filtro.getMesa().getId();
		}
		if(filtro.getNome_responsavel() != null && filtro.getNome_responsavel().equals("")){
			sql += "and r.nome_Responsavel =" + filtro.getNome_responsavel();
		}
		Query query = manager.createQuery(sql);
		return query.getResultList();
	}

}
