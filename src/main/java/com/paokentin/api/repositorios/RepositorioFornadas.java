package com.paokentin.api.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.paokentin.api.banco.ConnectionManager;
import com.paokentin.api.model.Fornada;
import com.paokentin.api.model.Pao;

public class RepositorioFornadas {

	private static RepositorioFornadas myself = null;

	private RepositorioFornadas() {}

	public static RepositorioFornadas getCurrentInstance() {

		if (myself == null) {
			myself = new RepositorioFornadas();
		}

		return myself;
	}

	public boolean cadastrar(Fornada fornada) throws SQLException {
		String sql = "INSERT INTO fornadas(prontoEm, paoCodigo) VALUES (?,?)";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		fornada.setProntoEm(LocalDateTime.now().plusMinutes(fornada.getPao().getMinutos()));

		pstm.setObject(1, fornada.getProntoEm());

		pstm.setInt(2, fornada.getPao().getCodigo());

		pstm.execute();

		return true;
	}

	public boolean remover(int codigo) throws SQLException {
		String sql = "DELETE FROM fornadas WHERE codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setInt(1, codigo);

		pstm.execute();

		return true;
	}

	public boolean editar(Fornada fornada) throws SQLException {
		String sql = "UPDATE FORNADAS SET prontoEm=?, paoCodigo=? WHERE codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setObject(1, fornada.getProntoEm());

		pstm.setInt(2, fornada.getPao().getCodigo());

		pstm.setInt(3, fornada.getCodigo());

		pstm.executeUpdate();

		return true;
	}

	public Fornada ler(int codigo) throws SQLException {
		String sql = "SELECT * FROM fornadas AS f JOIN paes AS p ON (f.paoCodigo = p.codigo) WHERE f.codigo = ?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setInt(1, codigo);

		pstm.executeQuery();

		ResultSet result = pstm.getResultSet();

		if (result.next()) {
			Fornada fornada = new Fornada();

			fornada.setCodigo(codigo);

			fornada.setProntoEm(result.getObject("prontoEm", LocalDateTime.class));

			Pao pao = new Pao();

			pao.setCodigo(result.getInt("p.codigo"));

			pao.setNome(result.getString("nome"));

			pao.setDesc(result.getString("desc"));

			pao.setMinutos(result.getInt("minutos"));

			fornada.setPao(pao);

			return fornada;
		}

		return null;
	}

	public List<Fornada> listar() throws SQLException {
		String sql = "SELECT * FROM fornadas AS f JOIN paes AS p ON (f.paoCodigo = p.codigo)";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.executeQuery();

		ResultSet result = pstm.getResultSet();

		List<Fornada> fornadas = new ArrayList<>();

		while (result.next()) {
			Fornada fornada = new Fornada();

			fornada.setCodigo(result.getInt("f.codigo"));

			fornada.setProntoEm(result.getObject("prontoEm", LocalDateTime.class));

			Pao pao = new Pao();

			fornada.setPao(pao);

			pao.setCodigo(result.getInt("p.codigo"));

			pao.setNome(result.getString("nome"));

			pao.setDesc(result.getString("desc"));

			pao.setMinutos(result.getInt("minutos"));

			fornadas.add(fornada);
		}

		return fornadas;
	}
	
	public Fornada lerUltimaPao(Pao pao) throws SQLException {
		String sql = "SELECT * FROM fornadas WHERE paoCodigo = ? ORDER BY prontoEm DESC LIMIT 1;";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setInt(1, pao.getCodigo());

		pstm.executeQuery();

		ResultSet result = pstm.getResultSet();

		if (result.next()) {
			Fornada fornada = new Fornada();

			fornada.setCodigo(result.getInt("codigo"));

			fornada.setProntoEm(result.getObject("prontoEm", LocalDateTime.class));

			fornada.setPao(pao);

			return fornada;
		}
		return null;
	}

}
