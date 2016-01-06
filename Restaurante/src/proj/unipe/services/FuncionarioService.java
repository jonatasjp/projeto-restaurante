package proj.unipe.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proj.unipe.bd.JPAUtil;
import proj.unipe.daos.FuncionarioDAO;
import proj.unipe.entities.Funcionario;

@Service
@Transactional
public class FuncionarioService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;

	public void inserir(Funcionario funcionario) {

		funcionarioDAO.inserir(funcionario);

	}

	public void atualizar(Funcionario funcionario) {

		funcionarioDAO.atualizar(funcionario);

	}

	public void excluir(Funcionario funcionario) {

		funcionarioDAO.excluir(funcionario);

	}

	public Funcionario buscarPorId(Long id) {

		Funcionario funcionario = null;

		return funcionario = (Funcionario) funcionarioDAO.buscarPorID(id);

	}

	public List<Funcionario> listar() {

		List<Funcionario> funcionarios = null;

		funcionarios = funcionarioDAO.listar();

		return funcionarios;

	}

	// método para realizar a busca por nome com CRITERIA
	public List<Funcionario> buscaPorNome(String nome) throws Exception {

		List<Funcionario> funcionarios = null;

		return funcionarios = funcionarioDAO.buscaPorNome(nome);

	}

}
