package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicid.bean.Aluno;
import br.edu.unicid.util.ConnectionFactory;

public class AlunoDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private Aluno aluno;
	
	public AlunoDAO() throws Exception {
		
		try {
			this.conn = ConnectionFactory.getConnection();
			
		}catch (Exception e) {
			throw new Exception("erro: \n" + e.getLocalizedMessage());
		}
}

//Salvar Aluno
public void salvar(Aluno aluno) throws Exception {
	if (aluno == null)
		throw new Exception ("O valor passado nao pode ser nulo");
	try {
		String SQL = "INSERT INTO tb_aluno (caAluno, nomeAluno, emailAluno, dtaNasc, idadeAluno, "
				+ "endAluno) values (?, ?, ?, ?, ?, ?)";
		conn = this.conn;
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, aluno.getCaAluno());
		ps.setString(2, aluno.getNomeAluno());
		ps.setString(3, aluno.getEmailAluno());
		ps.setDate(4, new java.sql.Date(aluno.getDtaNasc().getTime()));
		ps.setInt(5, aluno.getIdadeAluno());
		ps.setString(6, aluno.getEndAluno());
		ps.executeUpdate();
		
	}catch (SQLException sqle) {
		throw new Exception ("Erro ao inserir dados " + sqle);
	}finally {
		ConnectionFactory.closeConnection(conn, ps);
	}
  }

//Atualizar Aluno
public void atualizar(Aluno aluno) throws Exception {
	if (aluno == null)
		throw new Exception ("O valor passado nao pode ser nulo");
	try {
		String SQL = "UPDATE tb_aluno (caAluno, nomeAluno, emailAluno, dtaNasc, idadeAluno, "
				+ "endAluno) values (?, ?, ?, ?, ?, ?)";
		conn = this.conn;
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, aluno.getCaAluno());
		ps.setString(2, aluno.getNomeAluno());
		ps.setString(3, aluno.getEmailAluno());
		ps.setDate(4, new java.sql.Date(aluno.getDtaNasc().getTime()));
		ps.setInt(5, aluno.getIdadeAluno());
		ps.setString(6, aluno.getEndAluno());
		ps.executeUpdate();
	
	}catch (SQLException sqle) {
		throw new Exception ("Erro ao alterar dados" + sqle);
    }finally {
    	
    }
  }


//Excluir Aluno
public void excluir(Aluno aluno) throws Exception {
	if (aluno == null)
		throw new Exception ("O valor passado nao pode ser nulo");
	try {
		String SQL = "DELETE FROM tb_aluno WHERE caAluno = ?";
		conn = this.conn;
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, aluno.getCaAluno());
		ps.executeUpdate();
		
	}catch (SQLException sqle) {
		throw new Exception ("Erro ao excluir dados " + sqle);
	}finally {
		ConnectionFactory.closeConnection(conn, ps);
	}
	
}


//Procurar Aluno
public Aluno procurarAluno(int caAluno) throws Exception{
	
	try {
		String SQL = "Select * FROM tb_aluno WHERE caAluno=?";
		conn =this.conn;
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, caAluno);
		rs = ps.executeQuery();
		if (rs.next()) {
			int ca = rs.getInt(1);
			String nome = rs.getString(1);
			String email = rs.getString(2);
			Date nascimento = rs.getDate(4);
			int idade = rs.getInt(5);
			String endereco = rs.getString(6);
			aluno = new Aluno ();
		}
		return aluno;
	}catch (SQLException sqle) {
		throw new Exception(sqle);
	} finally {
		ConnectionFactory.closeConnection(conn, ps, rs);
	}
  }


//Listar todos os alunos

public List todosAlunos() throws Exception{
	try {
		conn = this.conn;
		ps = conn.prepareStatement("SELECT * FROM tb_aluno");
		rs = ps.executeQuery();
		List<Aluno> list = new ArrayList<Aluno>();
		while (rs.next()) {
			int ca = rs.getInt(1);
			String nome = rs.getString(2);
			String email = rs.getString(3);
			Date nascimento = rs.getDate(4);
			int idade = rs.getInt(5);
			String endereco = rs.getString(6);
			list.add(new Aluno());
		}
		return list;
	}catch (SQLException sqle) {
		throw new Exception(sqle);
	}finally {
		ConnectionFactory.closeConnection(conn, ps, rs);
	}
  } 
}