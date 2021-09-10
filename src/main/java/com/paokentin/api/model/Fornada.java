package com.paokentin.api.model;

import java.time.LocalDateTime;

public class Fornada {

	private int codigo;

	private LocalDateTime prontoEm;

	private Pao pao;

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the prontoEm
	 */
	public LocalDateTime getProntoEm() {
		return prontoEm;
	}

	/**
	 * @param prontoEm the prontoEm to set
	 */
	public void setProntoEm(LocalDateTime prontoEm) {
		this.prontoEm = prontoEm;
	}

	/**
	 * @return the pao
	 */
	public Pao getPao() {
		return pao;
	}

	/**
	 * @param pao the pao to set
	 */
	public void setPao(Pao pao) {
		this.pao = pao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fornada [codigo=");
		builder.append(codigo);
		builder.append(", prontoEm=");
		builder.append(prontoEm);
		builder.append(", pao=");
		builder.append(pao);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Fornada)) {
			return false;
		}
		Fornada other = (Fornada) obj;
		if (codigo != other.codigo) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

}
