package me.projeto.socket;

import me.projeto.erro.CustomErro;

/**
 * Classe de interface de Socket
 * 
 * @author UnixCF
 * @since 2018-11-14
 *
 */

public interface InterSocket {

	/**
	 * Método para preparar o sistemas Socket, aqui setamos a porta do servidor
	 * Socket. Observação: Este método também é utilizado para conectar em um
	 * servidor socket.
	 * 
	 * @param endereco informar o endereço IP do Socket.
	 * @param porta    informar a porta de conexão ao Socket.
	 * @throws CustomErro informa os tipo de erro que estão dando
	 */
	public abstract void PrepararSocket(String endereco, int porta) throws CustomErro;

	/**
	 * Método para criação do servidor/conexão
	 */
	public abstract void CarregarSocket();

	/**
	 * Método para ouvir retorno da porta do socket
	 * 
	 * @throws CustomErro informa os tipo de erro que estão dando
	 */
	public abstract void OuvirSocket() throws CustomErro;

}
