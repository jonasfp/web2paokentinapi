package com.paokentin.api.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.paokentin.api.banco.ConnectionManager;
import com.paokentin.api.model.Pao;

public class RepositorioPaes {

	private static RepositorioPaes myself = null;

	private RepositorioPaes() {}

	public static RepositorioPaes getCurrentInstance() {

		if (myself == null) {
			myself = new RepositorioPaes();
		}

		return myself;
	}

	public boolean cadastrar(Pao p) throws SQLException {
		String sql = "INSERT INTO paes(nome, `desc`, minutos) VALUES (?,?,?)";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setString(1, p.getNome());

		pstm.setString(2, p.getDesc());

		pstm.setInt(3, p.getMinutos());

		pstm.execute();

		return true;
	}

	public boolean remover(int cod) throws SQLException {
		String sql = "DELETE FROM paes WHERE codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setInt(1, cod);

		pstm.execute();

		return true;
	}

	public boolean editar(Pao pao) throws SQLException {
		String sql = "UPDATE paes SET nome=?, `desc`=?, minutos=? WHERE codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setString(1, pao.getNome());

		pstm.setString(2, pao.getDesc());

		pstm.setInt(3, pao.getMinutos());

		pstm.setInt(4, pao.getCodigo());

		pstm.executeUpdate();

		return true;
	}

	public Pao ler(int codigo) throws SQLException {
		String sql = "SELECT * FROM paes WHERE codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setInt(1, codigo);

		pstm.executeQuery();

		ResultSet res = pstm.getResultSet();

		if (res.next()) {

			Pao pao = new Pao();

			pao.setNome(res.getString("nome"));

			pao.setDesc(res.getString("desc"));

			pao.setMinutos(res.getInt("minutos"));

			pao.setCodigo(codigo);

			return pao;
		}

		return null;
	}

	public List<Pao> listar() throws SQLException {
		ArrayList<Pao> paes = new ArrayList<>();

		String sql = "SELECT * FROM paes";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.executeQuery();

		ResultSet result = pstm.getResultSet();

		while (result.next()) {
			Pao pao = new Pao();

			pao.setNome(result.getString("nome"));

			pao.setDesc(result.getString("desc"));

			pao.setMinutos(result.getInt("minutos"));

			pao.setCodigo(result.getInt("codigo"));

			paes.add(pao);
		}

		return paes;
	}

}
